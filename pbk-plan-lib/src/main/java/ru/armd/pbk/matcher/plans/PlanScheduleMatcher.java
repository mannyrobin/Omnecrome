package ru.armd.pbk.matcher.plans;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.plans.schedules.PlanScheduleView;
import ru.armd.pbk.domain.plans.schedules.ScheduleShift;
import ru.armd.pbk.dto.plans.schedules.ScheduleShiftDTO;
import ru.armd.pbk.enums.nsi.Shift;
import ru.armd.pbk.enums.nsi.ShiftModes;
import ru.armd.pbk.views.plans.schedules.EmployeePlanScheduleView;
import ru.armd.pbk.views.plans.schedules.SchedulesTableView;
import ru.armd.pbk.views.plans.schedules.ShiftInfoView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Преобразует входные данные для получения view расписания плана отдела.
 */
@Mapper
public abstract class PlanScheduleMatcher
	implements IDomainMatcher<ScheduleShift, ScheduleShiftDTO> {

   public static final PlanScheduleMatcher INSTANCE = Mappers.getMapper(PlanScheduleMatcher.class);

   /**
	* Получает из домена смены расписания плана отдела view.
	*
	* @param scheduleShift домен смены расписания плана отдела
	* @return view смены расписания плана отдела
	*/
   abstract ShiftInfoView getShiftView(ScheduleShift scheduleShift);

   /**
	* Преобразовать дто в домен.
	*
	* @param dto дто
	* @return домен
	*/
   public ScheduleShift toDomainSchedule(ScheduleShiftDTO dto) {
	  ScheduleShift domain = toDomain(dto);
	  if (dto.getShiftId().equals(Shift.DAY.getId()) || dto.getShiftId().equals(Shift.NIGHT.getId())) {
		 domain.setShiftModeId(ShiftModes.H_2_2.getId());
	  }
	  return domain;
   }

   /**
	* Получает из домена расписания плана отдела view.
	*
	* @param planSchedule домен расписания плана отдела
	* @return view расписания плана отдела
	*/
   public SchedulesTableView getPlanScheduleView(PlanScheduleView planSchedule) {
	  if (planSchedule != null) {
		 List<EmployeePlanScheduleView> planScheduleView = initEmployeeScheduleViewList(planSchedule.getEmployees());
		 SchedulesTableView schedulesTableView = new SchedulesTableView();
		 schedulesTableView.setAllEmpsCounts(planSchedule.getAllEmpsCounts());
		 if (planSchedule.getScheduleShifts() != null && planSchedule.getScheduleShifts().size() > 0) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			EmployeePlanScheduleView nightSchedule = null;
			EmployeePlanScheduleView saturdaySundaySchedule = null;
			EmployeePlanScheduleView sundayMondaySchedule = null;
			EmployeePlanScheduleView fridaySaturdaySchedule = null;
			for (EmployeePlanScheduleView view : planScheduleView) {
			   for (ScheduleShift scheduleShift : planSchedule.getScheduleShifts()) {
				  if (view.getId().equals(scheduleShift.getEmployeeId())) {
					 if (view.getFireDate() != null
						 && view.getFireDate().getTime() < scheduleShift.getPlanDate().getTime()) {
						continue;
					 }
					 for (Long reserveId : planSchedule.getReserves()) {
						if (scheduleShift.getId().equals(reserveId)) {
						   scheduleShift.setIsReserve(true);
						   break;
						}
					 }
					 switch (scheduleShift.getShiftModeId().intValue()) {
						case 0:
						   if (nightSchedule == null) {
							  nightSchedule = new EmployeePlanScheduleView(view);
						   }
						   nightSchedule.getShifts().put(df.format(scheduleShift.getPlanDate()),
							   getShiftView(scheduleShift));
						   break;
						case 1:
						   if (fridaySaturdaySchedule == null) {
							  fridaySaturdaySchedule = new EmployeePlanScheduleView(view);
						   }
						   fridaySaturdaySchedule.getShifts().put(df.format(scheduleShift.getPlanDate()),
							   getShiftView(scheduleShift));
						   break;
						case 2:
						   if (saturdaySundaySchedule == null) {
							  saturdaySundaySchedule = new EmployeePlanScheduleView(view);
						   }
						   saturdaySundaySchedule.getShifts().put(df.format(scheduleShift.getPlanDate()),
							   getShiftView(scheduleShift));
						   break;
						case 3:
						   if (sundayMondaySchedule == null) {
							  sundayMondaySchedule = new EmployeePlanScheduleView(view);
						   }
						   sundayMondaySchedule.getShifts().put(df.format(scheduleShift.getPlanDate()),
							   getShiftView(scheduleShift));
						   break;
						default:
						   break;
					 }
					 continue;
				  }
			   }
			   if (nightSchedule != null) {
				  schedulesTableView.getNightSchedules().add(nightSchedule);
			   }
			   if (fridaySaturdaySchedule != null) {
				  schedulesTableView.getFridaySaturdaySchedules().add(fridaySaturdaySchedule);
			   }
			   if (saturdaySundaySchedule != null) {
				  schedulesTableView.getSaturdaySundaySchedules().add(saturdaySundaySchedule);
			   }
			   if (sundayMondaySchedule != null) {
				  schedulesTableView.getSundayMondaySchedules().add(sundayMondaySchedule);
			   }
			   nightSchedule = null;
			   saturdaySundaySchedule = null;
			   sundayMondaySchedule = null;
			   fridaySaturdaySchedule = null;
			}
		 }
		 schedulesTableView.setCnt(new Long(schedulesTableView.getNightSchedules().size()
			 + schedulesTableView.getFridaySaturdaySchedules().size()
			 + schedulesTableView.getSaturdaySundaySchedules().size()
			 + schedulesTableView.getSundayMondaySchedules().size()));
		 return schedulesTableView;
	  }
	  return null;
   }

   /**
	* Начальная инициализация view расписания плана отдела.
	*
	* @param employees список views
	* @return список проинициализированных views
	*/
   private List<EmployeePlanScheduleView> initEmployeeScheduleViewList(List<EmployeePlanScheduleView> employees) {
	  if (employees == null) {
		 return null;
	  }
	  for (EmployeePlanScheduleView employee : employees) {
		 employee.setShifts(new HashMap<String, ShiftInfoView>());
	  }
	  return employees;
   }
}
