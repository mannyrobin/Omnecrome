package ru.armd.pbk.repositories.nsi.askp;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.StopIntervalStat;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.mappers.nsi.RouteMapper;
import ru.armd.pbk.mappers.nsi.StopIntervalsStatsMapper;
import ru.armd.pbk.mappers.nsi.askp.AskpChecksByStopsMapper;
import ru.armd.pbk.views.nsi.askp.AskpChecksByStopsView;
import ru.armd.pbk.views.nsi.askp.AskpStopIntervalsByHourView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Репозиторий проверок АСКП по остановкам.
 */
@Repository
public class AskpChecksByStopsRepository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(AskpChecksByStopsRepository.class);

   @Autowired
   private StopIntervalsStatsMapper stopIntervalsStatsMapper;

   @Autowired
   private RouteMapper routeMapper;

   @Autowired
   AskpChecksByStopsRepository(AskpChecksByStopsMapper mapper) {
	  super(NsiAuditType.NSI_CHECK_BY_STOPS, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @SuppressWarnings("unchecked")
   @Override
   public List<AskpChecksByStopsView> getGridViews(BaseGridViewParams params) {
	  List<AskpChecksByStopsView> result = new LinkedList<AskpChecksByStopsView>();
	  if (!params.getFilter().containsKey("route")) {
		 return result;
	  }

	  List<AskpStopIntervalsByHourView> views = super.getGridViews(params);
	  if (views.size() == 0) {
		 processError(params.getFilter());
		 return result;
	  }

	  int totalSum = 0;
	  int totalPaidSum = 0;
	  int tripNum = 1;
	  Map<Integer, MutablePair<Integer, Integer>> hours = new TreeMap<Integer, MutablePair<Integer, Integer>>();
	  for (int i = 3; i < 27; ++i) {
		 hours.put(i, new MutablePair<Integer, Integer>(0, 0));
	  }

	  AskpChecksByStopsView current = createResultView(views.get(0), tripNum);
	  result.add(current);

	  for (AskpStopIntervalsByHourView view : views) {
		 if (!view.getStopId().equals(current.getStopId())
			 || !view.getOrderNum().equals(current.getOrderNum())
			 || !view.getTripKind().equals(current.getTripKind())
			 || !view.getTripId().equals(current.getTripId())) {
			if (!view.getTripId().equals(current.getTripId())) {
			   ++tripNum;
			}
			current = createResultView(view, tripNum);
			result.add(current);
		 }

		 current.setSum(current.getSum() + view.getChecks());
		 current.setSumPaid(current.getSumPaid() + view.getChecksPaid());
		 current.getExtcols().put(view.getHour(), String.valueOf(view.getChecks()) + "/" + String.valueOf(view.getChecksPaid()));
		 MutablePair<Integer, Integer> count = hours.containsKey(view.getHour()) ? hours.get(view.getHour()) : new MutablePair<Integer, Integer>(0, 0);
		 count.setLeft(count.getLeft() + view.getChecks());
		 count.setRight(count.getRight() + view.getChecksPaid());
		 hours.put(view.getHour(), count);
		 totalSum += view.getChecks();
		 totalPaidSum += view.getChecksPaid();
	  }

	  AskpChecksByStopsView total = new AskpChecksByStopsView();
	  total.setSum(totalSum);
	  total.setSumPaid(totalPaidSum);
	  total.setExtcols(new HashMap<Integer, String>());
	  for (Entry<Integer, MutablePair<Integer, Integer>> h : hours.entrySet()) {
		 total.getExtcols().put(h.getKey(), String.valueOf(String.valueOf(h.getValue().getLeft())
			 + "/" + String.valueOf(h.getValue().getRight())));
	  }
	  total.setHours(new ArrayList<Integer>());
	  total.getHours().addAll(hours.keySet());
	  result.add(0, total);

	  for (AskpChecksByStopsView item : result) {
		 for (Integer hour : result.get(0).getHours()) {
			if (!item.getExtcols().containsKey(hour)) {
			   item.getExtcols().put(hour, "0/0");
			}
		 }
	  }

	  result.get(0).setCnt((long) result.size());
	  return result;
   }

   public AskpChecksByStopsView createResultView(AskpStopIntervalsByHourView view, int tripNum) {
	  AskpChecksByStopsView result = new AskpChecksByStopsView();
	  result.setCnt(view.getCnt());
	  result.setTripId(view.getTripId());
	  result.setTripNum(tripNum);
	  result.setTripKind(view.getTripKind());
	  result.setOrderNum(view.getOrderNum());
	  result.setStopName(view.getStopName());
	  result.setStopId(view.getStopId());
	  result.setSum(0);
	  result.setSumPaid(0);
	  result.setExtcols(new HashMap<Integer, String>());
	  return result;
   }

   @Override
   public <SelectItem extends ISelectItem, Params extends BaseSelectListParams> List<SelectItem> getSelectItems(
	   Params params) {
	  List<SelectItem> sList = null;
	  try {
		 sList = stopIntervalsStatsMapper.getSelectItems(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   private void processError(Map<String, Object> filter) {
	  DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	  List<StopIntervalStat> domains = stopIntervalsStatsMapper.getDomains(filter);
	  ISelectItem routeName = routeMapper.getRouteTsName((Long) filter.get("route"));
	  if (domains.size() == 0) {
		 throw new PBKValidationException("asdu", String.format("По маршруту %s за %s с номером выхода %s в АСДУ-НГПТ не найдены фактические рейсы",
			 routeName.getName(), df.format(filter.get("date")), filter.get("moveCode")));
	  }
	  String deportNumber = domains.get(0).getDepotNumber().toString();
	  for (int i = 1; i < domains.size(); i++) {
		 deportNumber += ", " + domains.get(i).getDepotNumber().toString();
	  }
	  throw new PBKValidationException("telematics", String.format("По маршруту %s за %s с номером выхода %s в АСДУ-НГПТ не найдена телематика. Бортовой номер ТС - %s",
		  routeName.getName(), df.format(filter.get("date")), filter.get("moveCode"), deportNumber));
   }
}
