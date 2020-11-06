package ru.armd.pbk.components.viss.jobs;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.IVisExecutor;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.domain.viss.intervals.StopInterval;
import ru.armd.pbk.domain.viss.intervals.StopPoint;
import ru.armd.pbk.domain.viss.intervals.TasksAndStops;
import ru.armd.pbk.domain.viss.intervals.Telematic;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.Settings;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.nsi.StopIntervalsStatsMapper;
import ru.armd.pbk.mappers.nsi.askp.AskpChecksByStopsMapper;
import ru.armd.pbk.mappers.viss.intervals.StopIntervalsMapper;
import ru.armd.pbk.repositories.core.SettingsRepository;
import ru.armd.pbk.utils.WktGeomUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Джоб расчета интервалов между остановками.
 */
@Component
public class StopIntervalsJob
	extends BaseComponent {

   public static final Logger LOGGER = Logger.getLogger(StopIntervalsJob.class);

   static AuditType auditType = VisAuditType.VIS_ASDU_STOPINTERVALS;
   static AuditObjOperation auditObjOperation = AuditObjOperation.IMPORT;

   @Autowired
   private StopIntervalsMapper mapper;

   @Autowired
   private StopIntervalsStatsMapper statsMapper;

   @Autowired
   private AskpChecksByStopsMapper askpChecksByStopsMapper;

   @Autowired
   private SettingsRepository settingsRepository;

   /**
	* В ручную запустить можно так (end необязательный параметр)
	* http://127.0.0.1:82/api/pbk/job?name=intervals&start=20160920&end=20160921.
	*
	* @param start начало периода расчета.
	* @param end   окончание периода расчета.
	*/
   @Async(IVisExecutor.PASSENGERS_EXECUTOR)
   public void calculate(Date start, Date end) {
	  if (end == null) {
		 end = start;
	  }
	  while (!start.after(end)) {
		 calculate(start);
		 start = DateUtils.addDays(start, 1);
	  }
   }

   private void calculate(Date date) {
	  int all = 0;
	  int processed = 0;
	  try {
		 date = DateUtils.truncate(date, Calendar.DATE);

		 logAudit(AuditLevel.INFO, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + " Начинаю подсчет межостановочных интервалов на дату " + date, null);

		 try {
			mapper.deleteIntervals(date);
		 } catch (Exception e) {
			logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
				Thread.currentThread().getName() + " Неизвестная ошибка при удалении интервалов", e);
		 }

		 // Запрашиваем все маршруты на день
		 List<String> routes = mapper.getPlanRoutes(date);

		 // Запрашиваем всю телематику на день
		 Map<Integer, List<Telematic>> telematics = new HashMap<Integer, List<Telematic>>();

		 List<Telematic> list = mapper.getAllTelematics(date);
		 if (list.size() == 0) {
			logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
				Thread.currentThread().getName() + " Телематика на дату " + date + " не найдена", null);
			return;
		 }
		 List<Telematic> subList = null;
		 Telematic last = null;
		 for (Telematic telematic : list) {
			if (last != null && telematic.getEquipmentId().equals(last.getEquipmentId())) {
			   subList.add(telematic);
			} else {
			   if (last != null) {
				  telematics.put(last.getEquipmentId(), subList);
			   }
			   last = telematic;
			   subList = new ArrayList<Telematic>();
			}
		 }
		 telematics.put(last.getEquipmentId(), subList);
		 list = null;

		 int portionSize = 5;
		 for (int from = 0; from < routes.size(); from += portionSize) {

			// Запрашиваем рейсы в связке с расписанием и остановками порциями
			// Результат отсортирован по routeCode, moveCode, shiftNum, routeNum
			int to = from + portionSize > routes.size() ? routes.size() : from + portionSize;
			List<String> portion = routes.subList(from, to);
			if (portion.size() == 0) {
			   break;
			}

			List<TasksAndStops> tasksAndStops = mapper.getTasksAndStops(date, portion);

			TasksAndStops current = null;
			List<StopPoint> points1 = null;
			List<StopPoint> points2 = null;
			List<StopPoint> pointsList = null;
			boolean pointsFull = false;
			for (TasksAndStops it : tasksAndStops) {

			   if (current == null
				   || !it.getEquipmentId().equals(current.getEquipmentId())
				   || !it.getRouteId().equals(current.getRouteId())
				   || !it.getGrafic().equals(current.getGrafic())
				   || !it.getServiceId().equals(current.getServiceId())) {
				  if (current != null) {
					 ++all;

					 List<Telematic> tels = new ArrayList<Telematic>();
					 if (telematics.containsKey(current.getEquipmentId())) {
						tels.addAll(telematics.get(current.getEquipmentId()));
					 }

					 if (points2 != null && points2.equals(points1)) {
						points2 = null;
					 }
					 if (processData(date, current, points1, points2, tels)) {
						++processed;
					 }
				  }
				  current = it;
				  pointsList = points1 = new ArrayList<StopPoint>();
				  points2 = null;
				  pointsFull = false;
			   }

			   if (pointsFull) {
				  continue;
			   }

			   if (!it.getTripNum().equals(current.getTripNum())) {
				  if (points2 != null) {
					 pointsFull = true;
					 continue;
				  }
				  current = it;
				  pointsList = points2 = new ArrayList<StopPoint>();
			   }

			   pointsList.add(new StopPoint(
				   it.getStopId(),
				   it.getLatitude() > it.getLongitude() ? it.getLatitude() : it.getLongitude(),
				   it.getLatitude() > it.getLongitude() ? it.getLongitude() : it.getLatitude(),
				   it.getTripNum(),
				   it.getTripId(),
				   it.getStopNum()));
			}

			if (current != null) {
			   if (points2 != null && points2.equals(points1)) {
				  points2 = null;
			   }
			   if (processData(date, current, points1, points2,
				   telematics.containsKey(current.getEquipmentId())
					   ? telematics.get(current.getEquipmentId()) : null)) {
				  ++processed;
			   }
			}
		 }

		 try {
			statsMapper.deleteByWorkDate(date);
			statsMapper.insertStatsByWorkDate(date);
			statsMapper.updateHasIntervalByWorkDate(date);
		 } catch (Exception e) {
			logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
				Thread.currentThread().getName() + " Ошибка обновления статистики для пассажиропотока на дату " + date, e);
		 }

		 try {
			mapper.updateStopNames(date);
		 } catch (Exception e) {
			logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
				Thread.currentThread().getName() + " Ошибка получения остановок для пассажиропотока на дату " + date, e);
		 }

		 try {
			mapper.updateDepots(date);
		 } catch (Exception e) {
			logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
				Thread.currentThread().getName() + " Ошибка получения парков для пассажиропотока на дату " + date, e);
		 }

		 try {
			Integer d = Integer.parseInt(settingsRepository.getById(Settings.VIS_STOP_INTERVALS_D.getId()).getValue());
			askpChecksByStopsMapper.updateAskpCounts(date, date, d);
			askpChecksByStopsMapper.updateAskpCountsPaid(date, date, d);
		 } catch (Exception e) {
			logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
				Thread.currentThread().getName() + " Ошибка расчета пассажиропотока по данным АСКП на дату " + date, e);
		 }
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + " Неизвестная ошибка", e);
	  } finally {
		 logAudit(AuditLevel.INFO, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + " Межостановочные интервалы на дату " + date
				 + " расчитаны для " + processed + " рейсов из " + all, null);
	  }
   }

   protected boolean processData(Date date, TasksAndStops item,
								 List<StopPoint> points1, List<StopPoint> points2, List<Telematic> telematics) {

	  Date start = DateUtils.addMinutes(date, item.getStartTime());
	  Date end = DateUtils.addMinutes(date, item.getEndTime());

	  if (points1.size() == 0) {
		 logAudit(AuditLevel.DEBUG, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + " Остановочные пункты для БО "
				 + item.getEquipmentId() + " на дату " + date + " не найдены", null);
		 return false;
	  }
	  if (telematics == null || telematics.size() == 0) {
		 logAudit(AuditLevel.DEBUG, auditType, auditObjOperation, null,
			 Thread.currentThread().getName() + " Телематика для БО "
				 + item.getEquipmentId() + " на дату " + date + " не найдена", null);
		 return false;
	  }
	  Map<List<StopPoint>, List<Telematic>> telematicsMap = new HashMap<List<StopPoint>, List<Telematic>>();
	  Set<List<StopPoint>> pointsSet = new HashSet<List<StopPoint>>();
	  pointsSet.add(points1);
	  if (points2 != null) {
		 pointsSet.add(points2);
	  }
	  for (List<StopPoint> points : pointsSet) {
		 List<Telematic> telematicsTmp = new ArrayList<Telematic>();
		 for (Telematic tel : telematics) {
			if (tel.getTime().after(start) && tel.getTime().before(end)) {
			   telematicsTmp.add(tel.copy());
			}
		 }
		 telematicsMap.put(points, telematicsTmp);

		 List<Telematic> removeList = new ArrayList<Telematic>();
		 Telematic last = null;
		 for (Telematic tel : telematicsTmp) {
			for (StopPoint point : points) {
			   try {
				  Double dist = WktGeomUtils.distance(
					  tel.getLatitude(), tel.getLongitude(), point.getLatitude(), point.getLongitude());
				  if (tel.getDistance() == null || tel.getDistance() > dist) {
					 tel.setDistance(dist);
					 tel.setNearestStop(point);
				  }
			   } catch (Exception e) {
				  logAudit(AuditLevel.ERROR, auditType, auditObjOperation, null,
					  Thread.currentThread().getName() + " Ошибка определения расстояния", e);
			   }
			}
			if (last != null && last.getNearestStop() == tel.getNearestStop()) {
			   if (last.getDistance() > tel.getDistance()) {
				  removeList.add(last);
				  last = tel;
			   } else {
				  removeList.add(tel);
			   }
			} else {
			   last = tel;
			}
		 }
		 telematicsTmp.removeAll(removeList);
	  }
	  List<Telematic> result = new ArrayList<Telematic>();
	  if (points2 == null || points2.size() == 0) {
		 result = telematicsMap.get(points1);
	  } else {
		 List<List<Telematic>> tels = new ArrayList<List<Telematic>>();
		 tels.add(telematicsMap.get(points1));
		 tels.add(telematicsMap.get(points2));
		 int directionWeight = 0;
		 Date dateIt = start;
		 int idx[] = new int[] {0, 0};
		 int listIt = 0;
		 List<Telematic> list = tels.get(listIt);
		 while (true) {
			++idx[listIt];
			if (idx[listIt] >= list.size()) {
			   break;
			}
			if (list.get(idx[listIt]).getNearestStop().getStopNum()
				< list.get(idx[listIt] - 1).getNearestStop().getStopNum()) {
			   --directionWeight;
			} else {
			   ++directionWeight;
			}
			if (directionWeight > 0) {
			   directionWeight = 0;
			}
			if (directionWeight < -2) {
			   if (result.size() > 2) {
				  result.remove(result.size() - 1);
				  result.remove(result.size() - 1);
				  idx[listIt] -= 2;
			   }
			   directionWeight = 1;
			   dateIt = result.get(result.size() - 1).getTime();
			   listIt = (listIt + 1) % 2;
			   list = tels.get(listIt);
			   while (idx[listIt] < list.size() && list.get(idx[listIt]).getTime().before(dateIt)) {
				  ++idx[listIt];
			   }
			}
			if (idx[listIt] < list.size()) {
			   result.add(list.get(idx[listIt]));
			}
		 }
	  }
	  if (result.size() == 0) {
		 return true;
	  }
	  List<StopInterval> intervals = new LinkedList<StopInterval>();
	  Integer lastTripVariant = -1;
	  Integer tripNum = 0;
	  for (Telematic tel : result) {
		 if (lastTripVariant != tel.getNearestStop().getTripNum()) {
			lastTripVariant = tel.getNearestStop().getTripNum();
			++tripNum;
		 }
		 StopInterval interval = new StopInterval();
		 interval.setWorkDate(date);
		 interval.setAsduRouteId(item.getRouteId());
		 interval.setGrafic(item.getGrafic());
		 interval.setTripId(tel.getNearestStop().getTripId());
		 interval.setServiceId(item.getServiceId());
		 //interval.setShiftNum(item.getShiftNum());
		 interval.setVehicleId(item.getVehicleId());
		 interval.setTripVariant(tel.getNearestStop().getTripNum());
		 interval.setTripNum(tripNum);
		 interval.setOrderNum(tel.getNearestStop().getStopNum());
		 interval.setStopId(tel.getNearestStop().getStopId());
		 interval.setStopArrivalTime(tel.getTime());

		 intervals.add(interval);
	  }

	  for (int from = 0; from < intervals.size(); from += 100) {
		 int to = from + 100 > intervals.size() ? intervals.size() : from + 100;
		 List<StopInterval> chunk = intervals.subList(from, to);
		 if (chunk.size() == 0) {
			break;
		 }
		 mapper.insertBulk(chunk);
	  }

	  return true;
   }

}
