package ru.armd.pbk.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.armd.pbk.authtoken.EmployeeAuthorization;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.exceptions.PBKReportException;
import ru.armd.pbk.mappers.report.standard.So23Mapper;
import ru.armd.pbk.views.report.So23DaySummariesView;
import ru.armd.pbk.views.report.So23DaysTotalSummariesView;
import ru.armd.pbk.views.report.So23EmplSummariesView;
import ru.armd.pbk.views.report.So23EmployeeView;

/**
 * Репозиторий стандартного отчёта "Сводные данные по работе контролеров за период".
 */
@Repository
public class So23Repository
	extends SoRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So23Repository.class);

   @Autowired
   private So23Mapper So23Mapper;

	@Autowired
   So23Repository(So23Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO23, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить сводные данные по численности контролёров за период по филиалам.
	*
	* @param params параметры фильтрации
	* @return сводные данные
	*/
   @EmployeeAuthorization
   public List<So23EmplSummariesView> getEmployeeSummaries(BaseGridViewParams params) {
	  List<So23EmplSummariesView> EmployeesSummaries = new ArrayList<>();
	  try {
		 List<So23EmployeeView> employees = So23Mapper.getEmployees(params);
		 Date dateStart = So23Mapper.getDateStart(params);
		 Date dateEnd = So23Mapper.getDateEnd(params);
		 for (So23EmployeeView employee : employees) {
			Long employeesId = employee.getId();
			List<So23DaySummariesView> daysSummaries = So23Mapper.getDaysSummariesForEmployee(params, employeesId);
			So23DaysTotalSummariesView daysTotalSummaries = So23Mapper.getDaysTotalSummariesForEmployee(params, employeesId);
			
			So23EmployeeView personalNumber = null;
			So23EmplSummariesView EmployeeSummaries =
				new So23EmplSummariesView(employee, personalNumber, daysSummaries, daysTotalSummaries, dateStart, dateEnd);
			EmployeesSummaries.add(EmployeeSummaries);
		 }
	  } catch (Exception e) {
		 String message = "Упс, чёй-то не работает. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, ReportAuditType.REPORT_STANDARD_SO23, AuditObjOperation.SELECT, params, message, e);
		 throw new PBKReportException(message, e);
	  }
	  return EmployeesSummaries;
   }

   @SuppressWarnings("unchecked")
@Override
   @EmployeeAuthorization
   public <View extends BaseGridView> View getGridViewTotal(Map<String, Object> params) {
	  So23EmplSummariesView view = null;
	  try {
		 BaseGridViewParams param = new BaseGridViewParams(null, null, null, "asc", params);
		 Date dateStart = So23Mapper.getDateStart(param);
		 Date dateEnd = So23Mapper.getDateEnd(param);
	     List<So23DaySummariesView> daysSummaries = So23Mapper.getDaysSummariesForEmployee(param, null);
		 So23DaysTotalSummariesView daysTotalSummaries = So23Mapper.getDaysTotalSummariesForEmployee(param, null);
		  
		 view = new So23EmplSummariesView(null, null, daysSummaries, daysTotalSummaries, dateStart, dateEnd);
	  } catch (Exception e) {
		 String message = "Не удалось получить значение Итого. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return (View) view;
   }
   

}
