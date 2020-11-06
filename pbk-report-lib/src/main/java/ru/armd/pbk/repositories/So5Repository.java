package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.authtoken.DepartmentAuthorization;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.nsi.Calendar;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.exceptions.PBKReportException;
import ru.armd.pbk.mappers.nsi.calendar.CalendarMapper;
import ru.armd.pbk.mappers.report.standard.So5Mapper;
import ru.armd.pbk.views.report.So5BranchEmplSummariesView;
import ru.armd.pbk.views.report.So5BranchView;
import ru.armd.pbk.views.report.So5DaySummariesView;
import ru.armd.pbk.views.report.So5DaysTotalSummariesView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий стандартного отчёта "Посменная численность контролёров ГУП "Мосгортранс" и среднее значение
 * численности за период".
 */
@Repository
public class So5Repository
	extends SoRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So5Repository.class);

   @Autowired
   private So5Mapper so5Mapper;

	@Autowired
	private CalendarMapper calendarMapper;

   @Autowired
   So5Repository(So5Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO5, mapper);
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
   @DepartmentAuthorization
   public List<So5BranchEmplSummariesView> getBranchEmployeeSummaries(BaseGridViewParams params) {
	  List<So5BranchEmplSummariesView> branchesEmployeeSummaries = new ArrayList<>();
	  try {
		 List<So5BranchView> branches = so5Mapper.getBranches(params);
		 Date dateStart = so5Mapper.getDateStart(params);
		 Date dateEnd = so5Mapper.getDateEnd(params);
		 for (So5BranchView branch : branches) {
			Long branchId = branch.getId();
			int planCount = so5Mapper.getPlanCountForBranch(params, branchId);
			int factCount = so5Mapper.getFactCountForBranch(params, branchId);
			List<So5DaySummariesView> daysSummaries = so5Mapper.getDaysSummariesForBranch(params, branchId);
			So5DaysTotalSummariesView daysTotalSummaries = so5Mapper.getDaysTotalSummariesForBranch(params, branchId);
			Integer workDaysTotal = 0;
			Integer workDaysCount = 0;
			for (So5DaySummariesView day : daysSummaries) {
				if (calendarMapper.getCalendarByDay(day.getDate()).getWorkDayTypeId().equals(1)) {
					workDaysTotal+=day.getTotal();
					workDaysCount++;
				}
			}
			Double average = 0d;
			if (workDaysCount > 0) {
			   average = (double) workDaysTotal / workDaysCount;
			}
			So5BranchEmplSummariesView branchEmployeeSummaries =
				new So5BranchEmplSummariesView(branch, planCount, factCount, daysSummaries, daysTotalSummaries, average, dateStart, dateEnd);
			branchesEmployeeSummaries.add(branchEmployeeSummaries);
		 }
	  } catch (Exception e) {
		 String message = "Не удалось получить список расписаний сотрудников. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, ReportAuditType.REPORT_STANDARD_SO5, AuditObjOperation.SELECT, params, message, e);
		 throw new PBKReportException(message, e);
	  }
	  return branchesEmployeeSummaries;
   }

   @Override
   @DepartmentAuthorization
   public <View extends BaseGridView> View getGridViewTotal(Map<String, Object> params) {
	  So5BranchEmplSummariesView view = null;
	  try {
		 BaseGridViewParams param = new BaseGridViewParams(null, null, null, "asc", params);
		 Date dateStart = so5Mapper.getDateStart(param);
		 Date dateEnd = so5Mapper.getDateEnd(param);
		 int planCount = so5Mapper.getPlanCountForBranch(param, null);
		 int factCount = so5Mapper.getFactCountForBranch(param, null);
		 List<So5DaySummariesView> daysSummaries = so5Mapper.getDaysSummariesForBranch(param, null);
		 So5DaysTotalSummariesView daysTotalSummaries = so5Mapper.getDaysTotalSummariesForBranch(param, null);
		  Integer workDaysTotal = 0;
		  Integer workDaysCount = 0;
		  for (So5DaySummariesView day : daysSummaries) {
			  if (calendarMapper.getCalendarByDay(day.getDate()).getWorkDayTypeId().equals(1)) {
				  workDaysTotal+=day.getTotal();
				  workDaysCount++;
			  }
		  }
		  Double average = 0d;
		  if (workDaysCount > 0) {
			  average = (double) workDaysTotal / workDaysCount;
		  }
		 view = new So5BranchEmplSummariesView(null, planCount, factCount, daysSummaries, daysTotalSummaries, average, dateStart, dateEnd);
	  } catch (Exception e) {
		 String message = "Не удалось получить значение Итого. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return (View) view;
   }
}
