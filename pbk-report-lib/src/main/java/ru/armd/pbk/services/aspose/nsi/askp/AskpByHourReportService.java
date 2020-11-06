package ru.armd.pbk.services.aspose.nsi.askp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.IReportView;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.askp.AskpByHourReportMapper;
import ru.armd.pbk.views.nsi.askp.AskpByHourReportView;
import ru.armd.pbk.views.nsi.askp.AskpChecksByHoursView;
import ru.armd.pbk.views.nsi.askp.AskpTicketChecksByHourView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Сервис отчетов АСКП по часам.
 */
@Service
public class AskpByHourReportService
	extends AskpReportService {

   private AskpByHourReportMapper reportMapper;

   /**
	* Конструктор.
	*
	* @param mapper - маппер
	*/
   @Autowired
   public AskpByHourReportService(AskpByHourReportMapper mapper) {
	  super("Количество перевезенных пассажиров по часам");
	  this.reportMapper = mapper;
   }

   @SuppressWarnings("unchecked")
   @Override
   protected <View extends IReportView> List<View> prepareData(BaseGridViewParams params) {
	  List<AskpByHourReportView> reportResult = new LinkedList<AskpByHourReportView>();
	  List<AskpChecksByHoursView> result = new LinkedList<AskpChecksByHoursView>();
	  if (!params.getFilter().containsKey("routes")) {
		 return (List<View>) reportResult;
	  }

	  List<AskpTicketChecksByHourView> views = reportMapper.getGridViews(params);
	  if (views.size() == 0) {
		 return (List<View>) reportResult;
	  }

	  SimpleDateFormat sdf = new SimpleDateFormat("yyy.MM.dd");
	  Long cnt = views.get(0).getCnt();
	  String where = "";
	  for (AskpTicketChecksByHourView view : views) {
		 if (where.length() > 0) {
			where += "or";
		 }
		 where += "(workDate='" + sdf.format(view.getWorkDate()) + "')";
	  }
	  params.getFilter().put("where", where);
	  views = getDetailedViews(params);
	  if (views.size() == 0) {
		 return (List<View>) reportResult;
	  }
	  views.get(0).setCnt(cnt);

	  Set<Integer> hours = new HashSet<Integer>();
	  for (int i = 3; i < 27; ++i) {
		 hours.add(i);
	  }
	  AskpChecksByHoursView avg = createResultView(views.get(0));
	  AskpChecksByHoursView current = createResultView(views.get(0));
	  result.add(current);

	  for (AskpTicketChecksByHourView view : views) {
		 if (!view.getWorkDate().equals(current.getWorkDate())) {
			current = createResultView(view);
			result.add(current);
		 }

		 current.setSum(current.getSum() + view.getChecks());
		 current.getExtcols().put(view.getHour(), view.getChecks());
		 hours.add(view.getHour());

		 avg.setSum(avg.getSum() + view.getChecks());
		 if (avg.getExtcols().containsKey(view.getHour())) {
			avg.getExtcols().put(view.getHour(), avg.getExtcols().get(view.getHour()) + view.getChecks());
		 } else {
			avg.getExtcols().put(view.getHour(), view.getChecks());
		 }
	  }

	  avg.setSum((int) Math.rint((double) avg.getSum() / (double) result.size()));
	  for (Integer hour : avg.getExtcols().keySet()) {
		 avg.getExtcols().put(hour, (int) Math.rint((double) avg.getExtcols().get(hour) / (double) result.size()));
	  }
	  avg.setWorkDate(null);
	  result.add(avg);

	  // Для корректного заголовка таблицы
	  result.get(0).setHours(new LinkedList<Integer>(hours));
	  Collections.sort(result.get(0).getHours());

	  for (AskpChecksByHoursView item : result) {
		 for (Integer hour : result.get(0).getHours()) {
			if (!item.getExtcols().containsKey(hour)) {
			   item.getExtcols().put(hour, new Integer(0));
			}
		 }
	  }

	  List<String> headers = createHeaders(result.get(0).getHours());
	  DateFormat df = new SimpleDateFormat("dd.MM.yy");
	  for (AskpChecksByHoursView rView : result) {
		 AskpByHourReportView view = new AskpByHourReportView();
		 view.setHeaders(headers);
		 view.setExtcols(createView(rView, df));
		 reportResult.add(view);
	  }
	  return (List<View>) reportResult;
   }

   protected List<String> createHeaders(List<Integer> hours) {
	  List<String> result = new LinkedList<String>();
	  result.add("Дата");
	  result.add("Сумма за сутки");
	  for (Integer hour : hours) {
		 result.add(getHourHeader(hour));
	  }
	  return result;
   }

   protected Map<String, String> createView(AskpChecksByHoursView view, DateFormat df) {
	  Map<String, String> result = new HashMap<String, String>();
	  result.put("Дата", view.getWorkDate() == null ? "Среднее" : df.format(view.getWorkDate()));
	  result.put("Сумма за сутки", view.getSum().toString());
	  for (Entry<Integer, Integer> entry : view.getExtcols().entrySet()) {
		 result.put(getHourHeader(entry.getKey()), entry.getValue().toString());
	  }
	  return result;
   }

   protected <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getDetailedViews(Params params) {
	  List<Views> views = null;
	  try {
		 views = reportMapper.getDetailedViews(params);
	  } catch (Exception e) {
		 String message = "Не удалось получить список gridView. Ошибка: " + e.getMessage();
		 throw new PBKException(message, e);
	  }
	  return views;
   }

   protected AskpChecksByHoursView createResultView(AskpTicketChecksByHourView view) {
	  AskpChecksByHoursView result = new AskpChecksByHoursView();
	  result.setCnt(view.getCnt());
	  result.setWorkDate(view.getWorkDate());
	  result.setSum(0);
	  result.setExtcols(new HashMap<Integer, Integer>());
	  return result;
   }

}
