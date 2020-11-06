package ru.armd.pbk.repositories.nsi.askp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.nsi.Calendar;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.askp.AskpChecksByHoursMapper;
import ru.armd.pbk.repositories.nsi.calendar.CalendarRepository;
import ru.armd.pbk.views.nsi.askp.AskpChecksByHoursView;
import ru.armd.pbk.views.nsi.askp.AskpTicketChecksByHourView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Repository
public class AskpChecksByHoursRepository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(AskpChecksByHoursRepository.class);

   private AskpChecksByHoursMapper mapper;

   @Autowired
   private CalendarRepository calendarRepository;

   @Autowired
   AskpChecksByHoursRepository(AskpChecksByHoursMapper mapper) {
	  super(NsiAuditType.NSI_CHECKS_BY_HOURS, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @SuppressWarnings("unchecked")
   @Override
   public List<AskpChecksByHoursView> getGridViews(BaseGridViewParams params) {
	  List<AskpChecksByHoursView> result = new LinkedList<AskpChecksByHoursView>();
	  if (!params.getFilter().containsKey("routes")) {
		 return result;
	  }

	  List<AskpTicketChecksByHourView> views = super.getGridViews(params);
	  if (views.size() == 0) {
		 return result;
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
		 return result;
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

	  List<Calendar> period = calendarRepository.getCalendarForPeriod((Date) params.getFilter().get("dateFrom"), (Date) params.getFilter().get("dateTo"));
	  for (AskpChecksByHoursView view : result) {
		 if (view.getWorkDate() != null) {
			for (Calendar day : period) {
			   if (day.getWorkDate().compareTo(view.getWorkDate()) == 0) {
				  view.setHoliday(!day.getWorkDayTypeId().equals(1));
				  break;
			   }
			}
		 }
	  }

	  for (AskpChecksByHoursView item : result) {
		 for (Integer hour : result.get(0).getHours()) {
			if (!item.getExtcols().containsKey(hour)) {
			   item.getExtcols().put(hour, new Integer(0));
			}
		 }
	  }
	  return result;
   }

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

   public AskpChecksByHoursView createResultView(AskpTicketChecksByHourView view) {
	  AskpChecksByHoursView result = new AskpChecksByHoursView();
	  result.setCnt(view.getCnt());
	  result.setWorkDate(view.getWorkDate());
	  result.setSum(0);
	  result.setExtcols(new HashMap<Integer, Integer>());
	  return result;
   }
}
