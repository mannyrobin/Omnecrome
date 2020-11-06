package ru.armd.pbk.mappers.plans.timesheets;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.plans.timesheets.Timesheet;
import ru.armd.pbk.views.plans.timesheets.EmployeePlanTimesheetsView;

import java.util.List;

/**
 * Маппер табелей сотрудников.
 */
@IsMapper
public interface PlanTimesheetMapper
	extends IDomainMapper<Timesheet> {

   /**
	* Получает список сотрудников по параметрам.
	*
	* @param param параметры
	* @return список сотрудников
	*/
   List<EmployeePlanTimesheetsView> getEmployeeForPlanTimesheetViews(BaseGridViewParams param);

   /**
	* Получает список табелей для переданного списка параметров и списка
	* сотрудников.
	*
	* @param params    параметры
	* @param employees список сотрудников
	* @return список табелей
	*/
   List<Timesheet> getPlanTimesheetsForEmployees(@Param("params") BaseGridViewParams params,
												 @Param("employees") List<EmployeePlanTimesheetsView> employees);

   /**
	* Получить список смен расписания, находящихся в резерве по параметрам.
	*
	* @param params параметры
	* @return список смен расписания, находящихся в резерве
	*/
   List<Long> getReserveList(BaseGridViewParams params);
}