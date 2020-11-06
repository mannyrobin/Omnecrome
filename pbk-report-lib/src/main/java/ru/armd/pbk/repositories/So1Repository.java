package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.authtoken.DepartmentAuthorization;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.exceptions.PBKReportException;
import ru.armd.pbk.mappers.report.standard.So1Mapper;
import ru.armd.pbk.views.report.So1EmployeeScheduleView;
import ru.armd.pbk.views.report.So1EmployeeSchedulesTableView;
import ru.armd.pbk.views.report.So1EmployeeView;
import ru.armd.pbk.views.report.So1ShiftView;

import java.util.Date;
import java.util.List;

/**
 * Репозиторий стандартного отчёта
 * "График работы контролеров по территориальному подразделению".
 */
@Repository
public class So1Repository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So1Repository.class);

   @Autowired
   private So1Mapper so1Mapper;

   @Autowired
   So1Repository(So1Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO1, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить список расписаний сотрудников.
	*
	* @param params параметры фильтрации
	* @return список связок
	*/
   @DepartmentAuthorization
   public So1EmployeeSchedulesTableView getEmployeesSchedules(BaseGridViewParams params) {
	  So1EmployeeSchedulesTableView reportResult = new So1EmployeeSchedulesTableView();
	  try {
		 List<So1EmployeeView> employees = so1Mapper.getEmployees(params);
		 long cnt = employees != null && !employees.isEmpty() ? employees.get(0).getCnt() : 0;
		 Date dateStart = so1Mapper.getDateStart(params);
		 Date dateEnd = so1Mapper.getDateEnd(params);
		 for (So1EmployeeView employee : employees) {
			List<So1ShiftView> schedule = so1Mapper.getScheduleForEmployee(params, employee.getId());
			So1EmployeeScheduleView employeeSchedule = new So1EmployeeScheduleView(employee, schedule, dateStart,
				dateEnd);
			if (schedule != null && !schedule.isEmpty() && schedule.get(0).getShiftMode() != null) {
			   switch (schedule.get(0).getShiftMode().intValue()) {
				  case 0:
					 reportResult.getNightSchedules().add(employeeSchedule);
					 employeeSchedule.setShiftMode(So1EmployeeScheduleView.ShiftModes.H_2_2);
					 break;
				  case 1:
					 reportResult.getFridaySaturdaySchedules().add(employeeSchedule);
					 employeeSchedule.setShiftMode(So1EmployeeScheduleView.ShiftModes.H_5_6);
					 break;
				  case 2:
					 reportResult.getSaturdaySundaySchedules().add(employeeSchedule);
					 employeeSchedule.setShiftMode(So1EmployeeScheduleView.ShiftModes.H_6_7);
					 break;
				  case 3:
					 reportResult.getSundayMondaySchedules().add(employeeSchedule);
					 employeeSchedule.setShiftMode(So1EmployeeScheduleView.ShiftModes.H_7_1);
					 break;
			   }
			}
		 }
		 reportResult.setCnt(cnt);
	  } catch (Exception e) {
		 String message = "Не удалось получить список расписаний сотрудников. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, ReportAuditType.REPORT_STANDARD_SO1, AuditObjOperation.SELECT, params, message,
			 e);
		 throw new PBKReportException(message, e);
	  }
	  return reportResult;
   }
}