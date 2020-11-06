package ru.armd.pbk.matcher.plans;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.plans.timesheets.PlanTimesheetView;
import ru.armd.pbk.domain.plans.timesheets.Timesheet;
import ru.armd.pbk.dto.plans.timesheets.TimesheetDTO;
import ru.armd.pbk.views.plans.timesheets.EmployeePlanTimesheetsView;
import ru.armd.pbk.views.plans.timesheets.TimesheetInfoView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Преобразует входные данные для получения view табелей сотрудников.
 */
@Mapper
public abstract class PlanTimesheetMatcher
	implements IDomainMatcher<Timesheet, TimesheetDTO> {

   public static final PlanTimesheetMatcher INSTANCE = Mappers.getMapper(PlanTimesheetMatcher.class);

   /**
	* Получает из домена табеля сотрудника view.
	*
	* @param timesheet домен табеля сотрудника
	* @return view табеля сотрудника
	*/
   abstract TimesheetInfoView getTimesheetView(Timesheet timesheet);

   /**
	* Получает из домена табелей отдела view.
	*
	* @param planTimesheet домен табелей отдела
	* @return view табелей отдела
	*/
   public List<EmployeePlanTimesheetsView> getPlanTimesheetView(PlanTimesheetView planTimesheet) {
	  if (planTimesheet != null) {
		 List<EmployeePlanTimesheetsView> planScheduleView = initEmployeeScheduleViewList(
			 planTimesheet.getEmployees());
		 if (planTimesheet.getTimesheets() != null && planTimesheet.getTimesheets().size() > 0) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			for (Timesheet timesheet : planTimesheet.getTimesheets()) {
			   for (Long reserveId : planTimesheet.getReserves()) {
				  if (timesheet.getId().equals(reserveId)) {
					 timesheet.setIsReserve(true);
					 break;
				  }
			   }
			   for (EmployeePlanTimesheetsView view : planScheduleView) {
				  if (view.getId().equals(timesheet.getEmployeeId())) {
					 view.getTimesheets().put(df.format(timesheet.getWorkDate()), getTimesheetView(timesheet));
					 continue;
				  }
			   }
			}
		 }
		 if (planScheduleView.isEmpty()) {
			planScheduleView.add(new EmployeePlanTimesheetsView());
			planScheduleView.get(0).setCnt(0L);
		 }
		 return planScheduleView;
	  }
	  return null;
   }

   /**
	* Начальная инициализация view табелей отдела.
	*
	* @param employees список views
	* @return список проинициализированных views
	*/
   private List<EmployeePlanTimesheetsView> initEmployeeScheduleViewList(List<EmployeePlanTimesheetsView> employees) {
	  if (employees == null) {
		 return null;
	  }
	  for (EmployeePlanTimesheetsView employee : employees) {
		 employee.setTimesheets(new HashMap<String, TimesheetInfoView>());
	  }
	  return employees;
   }
}
