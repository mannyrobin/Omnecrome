package ru.armd.pbk.repositories.nsi.askp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.nsi.Calendar;
import ru.armd.pbk.dto.nsi.askp.AskpChecksUpdateDTO;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.enums.core.Settings;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.askp.AskpChecksByMovesMapper;
import ru.armd.pbk.mappers.nsi.askp.AskpChecksByStopsMapper;
import ru.armd.pbk.mappers.nsi.askp.TicketCheckByHourMapper;
import ru.armd.pbk.repositories.core.SettingsRepository;
import ru.armd.pbk.repositories.nsi.calendar.CalendarRepository;
import ru.armd.pbk.views.nsi.askp.AskpChecksByMovesView;
import ru.armd.pbk.views.nsi.askp.AskpTicketChecksByHourView;
import ru.armd.pbk.views.nsi.askp.AskpTicketChecksByMoveDetailView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Репозиторий, для работы с агрегированными данными АСКП по выходам маршрутов.
 */
@Repository
public class AskpChecksByMovesRepository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(AskpChecksByMovesRepository.class);

   private AskpChecksByMovesMapper mapper;

   @Autowired
   private CalendarRepository calendarRepository;

   @Autowired
   private AskpChecksByStopsMapper askpChecksByStopsMapper;

   @Autowired
   private TicketCheckByHourMapper ticketCheckByHourMapper;

   @Autowired
   private SettingsRepository settingsRepository;

   @Autowired
   AskpChecksByMovesRepository(AskpChecksByMovesMapper mapper) {
	  super(NsiAuditType.NSI_CHECKS_BY_MOVES, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }


   @SuppressWarnings("unchecked")
   @Override
   public List<AskpChecksByMovesView> getGridViews(BaseGridViewParams params) {
	  List<AskpChecksByMovesView> result = new LinkedList<AskpChecksByMovesView>();

	  List<AskpTicketChecksByHourView> views = prepareViews(params);

	  if (views == null) {
		 return result;
	  }

	  SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");
	  Set<String> days = new HashSet<String>();
	  AskpChecksByMovesView currentRouteView = createResultView(views.get(0), true);
	  AskpChecksByMovesView currentMoveView = createResultView(views.get(0), false);
	  result.add(currentRouteView);
	  result.add(currentMoveView);
	  int routeDaysCount = 0;
	  int moveDaysCount = 0;

	  for (AskpTicketChecksByHourView view : views) {
		 if (!view.getRouteNumber().equals(currentRouteView.getRouteNumber())) {
			normalizeResultView(currentRouteView, routeDaysCount);
			currentRouteView = createResultView(view, true);
			result.add(currentRouteView);
			routeDaysCount = 0;
		 }
		 if (!view.getMoveCode().equals(currentMoveView.getMoveCode())) {
			normalizeResultView(currentMoveView, moveDaysCount);
			currentMoveView = createResultView(view, false);
			result.add(currentMoveView);
			moveDaysCount = 0;
		 }

		 ++moveDaysCount;
		 if (moveDaysCount > routeDaysCount) {
			routeDaysCount = moveDaysCount;
		 }

		 String day = sdf.format(view.getWorkDate());

		 if (currentRouteView.getExtcols().containsKey(day)) {
			currentRouteView.getExtcols().put(day, currentRouteView.getExtcols().get(day) + view.getChecks());
		 } else {
			currentRouteView.getExtcols().put(day, view.getChecks());
		 }

		 currentMoveView.getExtcols().put(day, view.getChecks());
		 currentMoveView.getExtcolDetails().put(day, createViewDetails(view));
		 days.add(day);
	  }
	  normalizeResultView(currentRouteView, routeDaysCount);
	  normalizeResultView(currentMoveView, moveDaysCount);

	  setWorkDays(params, result.get(0), days, sdf);

	  return result;
   }

   /**
	* Получить более подобрное представление
	* агрегированных данных АСКП по выходам маршрутов.
	* Например, время проверок АСКП и количество проверенных билетов в это время.	 *
	*
	* @param <Views>  тип представления.
	* @param <Params> тип параметров.
	* @param params   параметры.
	* @return
	*/
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getDetailedViews(Params params) {
	  List<Views> views = null;
	  try {
		 views = mapper.getDetailedViews(params);
	  } catch (Exception e) {
		 String message = "Не удалось получить список gridView. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }
	  return views;
   }

   /**
	* Обновить.
	*
	* @param dto ДТО.
	*/
   @Transactional(propagation = Propagation.NOT_SUPPORTED)
   public void update(AskpChecksUpdateDTO dto) {
	  try {
		 ticketCheckByHourMapper.deleteByWorkDate(dto.getStartDate(), dto.getEndDate());
		 ticketCheckByHourMapper.insertByWorkDate(dto.getStartDate(), dto.getEndDate());
		 ticketCheckByHourMapper.updateRoutesId(dto.getStartDate(), dto.getEndDate());
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, NsiAuditType.NSI_CHECKS_BY_MOVES, AuditObjOperation.UPDATE, null,
			 Thread.currentThread().getName() + " Ошибка обновления пассажиропотока по данным АСКП на даты c " + dto.getStartDate() + " по " + dto.getEndDate(), e);
	  }

	  try {
		 Integer d = Integer.parseInt(settingsRepository.getById(Settings.VIS_STOP_INTERVALS_D.getId()).getValue());
		 askpChecksByStopsMapper.updateAskpCounts(dto.getStartDate(), dto.getEndDate(), d);
		 askpChecksByStopsMapper.updateAskpCountsPaid(dto.getStartDate(), dto.getEndDate(), d);
	  } catch (Exception e) {
		 logAudit(AuditLevel.ERROR, NsiAuditType.NSI_CHECKS_BY_MOVES, AuditObjOperation.UPDATE, null,
			 Thread.currentThread().getName() + " Ошибка обновления пассажиропотока по данным АСКП на дату " + dto.getStartDate() + " по " + dto.getEndDate(), e);
	  }
   }

   protected AskpChecksByMovesView createResultView(AskpTicketChecksByHourView view, boolean isRoute) {
	  AskpChecksByMovesView result = new AskpChecksByMovesView();
	  if (isRoute) {
		 result.setRouteNumber(view.getRouteNumber());
	  } else {
		 result.setMoveCode(view.getMoveCode());
	  }
	  result.setRouteId(view.getRouteId());
	  result.setCnt(view.getCnt());
	  result.setSum(0);
	  result.setCount(0);
	  result.setAvg(0);
	  result.setMin(Integer.MAX_VALUE);
	  result.setMax(-1);
	  result.setExtcols(new HashMap<String, Integer>());
	  result.setExtcolDetails(new HashMap<String, AskpTicketChecksByMoveDetailView>());
	  result.setRouteName(view.getRouteName());
	  return result;
   }

   protected AskpTicketChecksByMoveDetailView createViewDetails(AskpTicketChecksByHourView view) {
	  AskpTicketChecksByMoveDetailView result = new AskpTicketChecksByMoveDetailView();
	  result.setIsHasAsdu(view.getIsHasAsdu());
	  result.setIsHasTelematics(view.getIsHasTelematics());
	  result.setDepotNumber(view.getDepotNumber());
	  return result;
   }

   protected void normalizeResultView(AskpChecksByMovesView view, int days) {
	  for (Integer value : view.getExtcols().values()) {
		 view.setSum(view.getSum() + value);
		 if (view.getMin() > value) {
			view.setMin(value);
		 }
		 if (view.getMax() < value) {
			view.setMax(value);
		 }
	  }

	  if (view.getMin() == Integer.MAX_VALUE) {
		 view.setMin(null);
	  }
	  if (view.getMax() == -1) {
		 view.setMax(null);
	  }

	  view.setCount(days);
	  if (days > 0) {
		 view.setAvg((int) Math.rint((double) view.getSum() / (double) days));
	  }
   }

   protected List<AskpTicketChecksByHourView> prepareViews(BaseGridViewParams params) {

	  if (!params.getFilter().containsKey("routes")) {
		 return null;
	  }

	  List<AskpTicketChecksByHourView> views = super.getGridViews(params);

	  if (views.size() == 0) {
		 return null;
	  }

	  Long cnt = views.get(0).getCnt();
	  String where = "";
	  for (AskpTicketChecksByHourView view : views) {
		 if (where.length() > 0) {
			where += "or";
		 }
		 where += "(routeId='" + view.getRouteId() + "'and moveCode='" + view.getMoveCode() + "')";
	  }
	  params.getFilter().put("where", where);
	  views = getDetailedViews(params);
	  if (views.size() == 0) {
		 return null;
	  }

	  views.get(0).setCnt(cnt);

	  return views;
   }

   protected void setWorkDays(BaseGridViewParams params, AskpChecksByMovesView firstView, Set<String> days, SimpleDateFormat sdf) {

	  Boolean isWorkDay = params.getFilter().containsKey("isWorkDate") ? (Long) params.getFilter().get("isWorkDate") != 0 : null;
	  List<Calendar> period = calendarRepository.getCalendarForPeriod((Date) params.getFilter().get("dateFrom"), (Date) params.getFilter().get("dateTo"));

	  firstView.setDays(new LinkedList<AskpChecksByMovesView.Day>());
	  for (Calendar date : period) {
		 String day = sdf.format(date.getWorkDate());
		 if (days.contains(day)) {
			if (isWorkDay == null || !isWorkDay.equals(!date.getWorkDayTypeId().equals(1))) {
			   firstView.getDays().add(new AskpChecksByMovesView.Day(day, !date.getWorkDayTypeId().equals(1)));
			}
		 }
	  }
   }
}
