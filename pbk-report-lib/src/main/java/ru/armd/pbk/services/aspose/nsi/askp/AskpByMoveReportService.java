package ru.armd.pbk.services.aspose.nsi.askp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.IReportView;
import ru.armd.pbk.domain.nsi.Calendar;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.askp.AskpByMoveReportMapper;
import ru.armd.pbk.repositories.nsi.calendar.CalendarRepository;
import ru.armd.pbk.views.nsi.askp.AskpByMoveReportView;
import ru.armd.pbk.views.nsi.askp.AskpChecksByMovesView;
import ru.armd.pbk.views.nsi.askp.AskpTicketChecksByHourView;
import ru.armd.pbk.views.nsi.askp.AskpTicketChecksByMoveDetailView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Сервис отчетов АСКП по выходам.
 */
@Service
public class AskpByMoveReportService
	extends AskpReportService {

   private AskpByMoveReportMapper mapper;

   @Autowired
   private CalendarRepository calendarRepository;

   /**
	* Конструктор.
	*
	* @param mapper - маппер
	*/
   @Autowired
   public AskpByMoveReportService(AskpByMoveReportMapper mapper) {
	  super("Количество перевезенных пассажиров по выходам");
	  this.mapper = mapper;
   }

   @SuppressWarnings("unchecked")
   @Override
   protected <View extends IReportView> List<View> prepareData(BaseGridViewParams params) {

	  List<AskpByMoveReportView> reportResult = new LinkedList<AskpByMoveReportView>();
	  if (!params.getFilter().containsKey("routes")) {
		 return (List<View>) reportResult;
	  }
	  List<AskpChecksByMovesView> result = new LinkedList<AskpChecksByMovesView>();
	  List<AskpTicketChecksByHourView> views = mapper.getGridViews(params);


	  Boolean isWorkDay = params.getFilter().containsKey("isWorkDate") ? (Long) params.getFilter().get("isWorkDate") != 0 : null;

	  if (views.size() == 0) {
		 return (List<View>) reportResult;
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
		 return (List<View>) reportResult;
	  }
	  views.get(0).setCnt(cnt);

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

	  List<Calendar> period = calendarRepository.getCalendarForPeriod((Date) params.getFilter().get("dateFrom"), (Date) params.getFilter().get("dateTo"));
	  AskpChecksByMovesView firstView = result.get(0);
	  firstView.setDays(new LinkedList<AskpChecksByMovesView.Day>());
	  for (Calendar date : period) {
		 String day = sdf.format(date.getWorkDate());
		 if (days.contains(day)) {
			if (isWorkDay == null || !isWorkDay.equals(!date.getWorkDayTypeId().equals(1))) {
			   firstView.getDays().add(new AskpChecksByMovesView.Day(day, !date.getWorkDayTypeId().equals(1)));
			}
		 }
	  }
	  List<String> headers = createHeaders(days);
	  for (AskpChecksByMovesView rView : result) {
		 AskpByMoveReportView view = new AskpByMoveReportView();
		 view.setHeaders(headers);
		 view.setExtcols(createView(rView));
		 reportResult.add(view);
	  }
	  return (List<View>) reportResult;
   }

   protected List<String> createHeaders(Set<String> hours) {
	  List<String> result = new LinkedList<String>();
	  List<String> ext = new LinkedList<String>(hours);
	  Collections.sort(ext);
	  result.add("Номер маршрута");
	  result.add("Номер выхода");
	  result.add("Сумма за период");
	  result.add("Количество дней");
	  result.add("Среднее за день");
	  result.add("Минимум");
	  result.add("Максимум");
	  result.addAll(ext);
	  return result;
   }

   protected Map<String, String> createView(AskpChecksByMovesView view) {
	  Map<String, String> result = new HashMap<String, String>();
	  result.put("Номер маршрута", view.getMoveCode() == null ? view.getRouteNumber() : null);
	  result.put("Номер выхода", view.getMoveCode());
	  result.put("Сумма за период", view.getSum() == null ? null : view.getSum().toString());
	  result.put("Количество дней", view.getCount() == null ? null : view.getCount().toString());
	  result.put("Среднее за день", view.getAvg() == null ? null : view.getAvg().toString());
	  result.put("Минимум", view.getMin() == null ? null : view.getMin().toString());
	  result.put("Максимум", view.getMax() == null ? null : view.getMax().toString());
	  for (Entry<String, Integer> entry : view.getExtcols().entrySet()) {
		 result.put(entry.getKey(), entry.getValue().toString());
	  }
	  return result;
   }

   protected <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getDetailedViews(Params params) {
	  List<Views> views = null;
	  try {
		 views = mapper.getDetailedViews(params);
	  } catch (Exception e) {
		 String message = "Не удалось получить список gridView. Ошибка: " + e.getMessage();
		 throw new PBKException(message, e);
	  }
	  return views;
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

}
