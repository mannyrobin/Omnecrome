package ru.armd.pbk.views.report;

import java.util.Map;

/**
 * View для связки контролёра с его отработанным временем за все дни (для использования
 * в отчёте "Табель фактически отработанного времени").
 */
public class So2EmployeeShiftHoursView
	extends SoView {

   private So2EmployeeView employee;
   private Map<String, So2ShiftHoursView> shifts;

   public So2EmployeeView getEmployee() {
	  return employee;
   }

   public void setEmployee(So2EmployeeView employee) {
	  this.employee = employee;
   }

   public Map<String, So2ShiftHoursView> getShifts() {
	  return shifts;
   }

   public void setShifts(Map<String, So2ShiftHoursView> shifts) {
	  this.shifts = shifts;
   }
}
