package ru.armd.pbk.services.aspose.nsi.askp;

import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.IReportView;
import ru.armd.pbk.mappers.nsi.askp.AskpByStopReportMapper;
import ru.armd.pbk.views.nsi.askp.AskpByStopReportView;
import ru.armd.pbk.views.nsi.askp.AskpChecksByStopsView;
import ru.armd.pbk.views.nsi.askp.AskpStopIntervalsByHourView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Поостановочный сервис отчетов АСКП.
 */
@Service
public class AskpByStopReportService
	extends AskpReportService {

   private AskpByStopReportMapper mapper;

   /**
	* Конструктор.
	*
	* @param mapper - маппер
	*/
   @Autowired
   public AskpByStopReportService(AskpByStopReportMapper mapper) {
	  super("Количество перевезенных пассажиров поостановочно");
	  this.mapper = mapper;
   }

   @SuppressWarnings("unchecked")
   @Override
   protected <View extends IReportView> List<View> prepareData(BaseGridViewParams params) {
	  List<AskpByStopReportView> reportResult = new LinkedList<AskpByStopReportView>();
	  List<AskpChecksByStopsView> result = new LinkedList<AskpChecksByStopsView>();
	  if (!params.getFilter().containsKey("route")) {
		 return (List<View>) reportResult;
	  }

	  List<AskpStopIntervalsByHourView> views = mapper.getGridViews(params);
	  if (views.size() == 0) {
		 return (List<View>) reportResult;
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
	  List<String> headers = createHeaders(result.get(0).getHours());
	  for (AskpChecksByStopsView rView : result) {
		 AskpByStopReportView view = new AskpByStopReportView();
		 view.setHeaders(headers);
		 view.setExtcols(createView(rView));
		 reportResult.add(view);
	  }
	  return (List<View>) reportResult;
   }

   protected List<String> createHeaders(List<Integer> hours) {
	  List<String> result = new LinkedList<String>();
	  result.add("Вариант маршрута");
	  result.add("№");
	  result.add("Остановка");
	  result.add("Общая сумма");
	  result.add("Платные");
	  for (Integer hour : hours) {
		 result.add(getHourHeader(hour));
	  }

	  return result;
   }

   protected Map<String, String> createView(AskpChecksByStopsView view) {
	  Map<String, String> result = new HashMap<String, String>();
	  result.put("Вариант маршрута", view.getTripNum() == null ? "" : view.getTripNum().toString());
	  result.put("№", view.getOrderNum() == null ? "" : view.getOrderNum().toString());
	  result.put("Остановка", view.getStopName() == null ? "" : view.getStopName().toString());
	  result.put("Общая сумма", view.getSum() == null ? "" : view.getSum().toString());
	  result.put("Платные", view.getSumPaid() == null ? "" : view.getSumPaid().toString());
	  for (Entry<Integer, String> entry : view.getExtcols().entrySet()) {
		 result.put(getHourHeader(entry.getKey()), entry.getValue());
	  }
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

}
