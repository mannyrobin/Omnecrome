package ru.armd.pbk.domain.plans.timesheets;

import ru.armd.pbk.views.plans.timesheets.EmployeePlanTimesheetsView;

import java.util.List;

/**
 * Домен табелей отдела.
 */
public class PlanTimesheetView {

   private List<EmployeePlanTimesheetsView> employees;
   private List<Timesheet> timesheets;
   private List<Long> reserves;

   public List<EmployeePlanTimesheetsView> getEmployees() {
	  return employees;
   }

   public void setEmployees(List<EmployeePlanTimesheetsView> employees) {
	  this.employees = employees;
   }

   public List<Timesheet> getTimesheets() {
	  return timesheets;
   }

   public void setTimesheets(List<Timesheet> timesheets) {
	  this.timesheets = timesheets;
   }

   public List<Long> getReserves() {
	  return reserves;
   }

   public void setReserves(List<Long> reserves) {
	  this.reserves = reserves;
   }
}
