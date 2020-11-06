package ru.armd.pbk.views.plans.schedules;

import ru.armd.pbk.core.views.BaseGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * View разбиения расписания, для отображения по группам в зависимости от
 * графика работы.
 */
public class SchedulesTableView
	extends BaseGridView {

   private List<EmployeePlanScheduleView> nightSchedules;
   private List<EmployeePlanScheduleView> saturdaySundaySchedules;
   private List<EmployeePlanScheduleView> sundayMondaySchedules;
   private List<EmployeePlanScheduleView> fridaySaturdaySchedules;
   private Map<String, String> allEmpsCounts = new HashMap<String, String>();

   public SchedulesTableView() {
	  this.nightSchedules = new ArrayList<>();
	  this.saturdaySundaySchedules = new ArrayList<>();
	  this.sundayMondaySchedules = new ArrayList<>();
	  this.fridaySaturdaySchedules = new ArrayList<>();
   }

   public List<EmployeePlanScheduleView> getNightSchedules() {
	  return nightSchedules;
   }

   public void setNightSchedules(List<EmployeePlanScheduleView> nightSchedules) {
	  this.nightSchedules = nightSchedules;
   }

   public List<EmployeePlanScheduleView> getSaturdaySundaySchedules() {
	  return saturdaySundaySchedules;
   }

   public void setSaturdaySundaySchedules(List<EmployeePlanScheduleView> saturdaySundaySchedules) {
	  this.saturdaySundaySchedules = saturdaySundaySchedules;
   }

   public List<EmployeePlanScheduleView> getSundayMondaySchedules() {
	  return sundayMondaySchedules;
   }

   public void setSundayMondaySchedules(List<EmployeePlanScheduleView> sundayMondaySchedules) {
	  this.sundayMondaySchedules = sundayMondaySchedules;
   }

   public List<EmployeePlanScheduleView> getFridaySaturdaySchedules() {
	  return fridaySaturdaySchedules;
   }

   public void setFridaySaturdaySchedules(List<EmployeePlanScheduleView> fridaySaturdaySchedules) {
	  this.fridaySaturdaySchedules = fridaySaturdaySchedules;
   }

   public Map<String, String> getAllEmpsCounts() {
	  return allEmpsCounts;
   }

   public void setAllEmpsCounts(Map<String, String> allEmpsCounts) {
	  this.allEmpsCounts = allEmpsCounts;
   }
}