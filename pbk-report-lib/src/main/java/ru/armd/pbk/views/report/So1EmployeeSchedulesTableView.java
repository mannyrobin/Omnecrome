package ru.armd.pbk.views.report;

import ru.armd.pbk.core.views.BaseGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Представление таблицы отчёта. содержит расписание для каждого режима работы.
 */
public class So1EmployeeSchedulesTableView
	extends BaseGridView {

   private List<So1EmployeeScheduleView> nightSchedules = new ArrayList<>();
   private List<So1EmployeeScheduleView> saturdaySundaySchedules = new ArrayList<>();
   private List<So1EmployeeScheduleView> sundayMondaySchedules = new ArrayList<>();
   private List<So1EmployeeScheduleView> fridaySaturdaySchedules = new ArrayList<>();

   public List<So1EmployeeScheduleView> getNightSchedules() {
	  return nightSchedules;
   }

   public List<So1EmployeeScheduleView> getSaturdaySundaySchedules() {
	  return saturdaySundaySchedules;
   }

   public List<So1EmployeeScheduleView> getSundayMondaySchedules() {
	  return sundayMondaySchedules;
   }

   public List<So1EmployeeScheduleView> getFridaySaturdaySchedules() {
	  return fridaySaturdaySchedules;
   }

   /**
	* Получить значение ввиде листа.
	*
	* @return
	*/
   public List<So1EmployeeScheduleView> asSingleList() {
	  // трешак ради вывода в отчёте с сохранением разбиения по режиму работы.
	  List<So1EmployeeScheduleView> result = new ArrayList<>();
	  result.addAll(nightSchedules);
	  result.addAll(saturdaySundaySchedules);
	  result.addAll(sundayMondaySchedules);
	  result.addAll(fridaySaturdaySchedules);
	  return result;
   }
}
