package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.exceptions.PBKReportException;
import ru.armd.pbk.mappers.report.standard.So13Mapper;
import ru.armd.pbk.views.report.So13TicketTypePassCountsView;
import ru.armd.pbk.views.report.So13TicketTypeView;
import ru.armd.pbk.views.report.So13View;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Репозиторий стандартного отчёта "Маршруты и выходы".
 */
@Repository
public class So13Repository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So13Repository.class);

   @Autowired
   private So13Mapper so13Mapper;

   @Autowired
   So13Repository(So13Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO13, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить список связок вида билета и сведений о проходов по виду билета за сутки.
	*
	* @param params параметры фильтрации
	* @return список связок
	*/
   public List<So13TicketTypePassCountsView> getTicketTypesPassCounts(BaseGridViewParams params) {
	  List<So13TicketTypePassCountsView> ticketTypesPassCounts = new ArrayList<>();
	  try {
		 List<So13View> views = so13Mapper.getTicketTypes(params);
		 //ticketTypeTotalPassCount
		 Map<String, Map<String, Integer>> ticketPassCount = new LinkedHashMap<String, Map<String, Integer>>();
		 for (So13View view : views) {
			String key = getKey(view);
			Map<String, Integer> passCount = ticketPassCount.containsKey(key) ? ticketPassCount.get(key) : new TreeMap<String, Integer>();
			Integer hour = 0;
			int sum = 0;
			for (String count : view.getCountByHour().split(",")) {
			   int pc = passCount.containsKey(hour.toString()) ? passCount.get(hour.toString()) : 0;
			   int c = Integer.parseInt(count);
			   sum += c;
			   passCount.put(hour.toString(), pc + c);
			   hour++;
			}
			int pc = passCount.containsKey("ticketTypeTotalPassCount") ? passCount.get("ticketTypeTotalPassCount") : 0;
			passCount.put("ticketTypeTotalPassCount", pc + sum);
			ticketPassCount.put(key, passCount);
		 }
		 for (Entry<String, Map<String, Integer>> entry : ticketPassCount.entrySet()) {
			So13TicketTypeView view = new So13TicketTypeView();
			String[] fields = entry.getKey().split(":");
			view.setWorkDate(fields[0]);
			view.setTicketTypeCode(fields[1]);
			view.setTicketTypeName(fields[2]);
			So13TicketTypePassCountsView so13TicketTypePassCountsView = new So13TicketTypePassCountsView(view, entry.getValue());
			ticketTypesPassCounts.add(so13TicketTypePassCountsView);
		 }
	  } catch (Exception e) {
		 String message = "Не удалось получить список количеств проходов по видам билетов. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, ReportAuditType.REPORT_STANDARD_SO13, AuditObjOperation.SELECT, params, message, e);
		 throw new PBKReportException(message, e);
	  }
	  if (ticketTypesPassCounts.size() > 0) {
		 ticketTypesPassCounts.get(0).setCnt(new Long(ticketTypesPassCounts.size()));
	  }
	  return ticketTypesPassCounts;
   }

   protected String getKey(So13View view) {
	  return String.format("%s:%s:%s", view.getWorkDate(), view.getTicketTypeCode(), view.getTicketTypeName());
   }
}
