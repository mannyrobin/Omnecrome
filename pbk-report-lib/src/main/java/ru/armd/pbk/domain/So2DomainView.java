package ru.armd.pbk.domain;

import ru.armd.pbk.views.report.So2EmployeeView;

import java.util.Date;
import java.util.List;

/**
 * Домен стандартного отчета №2.
 */
public class So2DomainView {

   private List<So2EmployeeView> employees;
   private List<So2ShiftHours> shifts;
   private Date startDate;
   private Date endDate;

   public List<So2EmployeeView> getEmployees() {
	  return employees;
   }

   public void setEmployees(List<So2EmployeeView> employees) {
	  this.employees = employees;
   }

   public List<So2ShiftHours> getShifts() {
	  return shifts;
   }

   public void setShifts(List<So2ShiftHours> shifts) {
	  this.shifts = shifts;
   }

   public Date getStartDate() {
	  return startDate;
   }

   public void setStartDate(Date startDate) {
	  this.startDate = startDate;
   }

   public Date getEndDate() {
	  return endDate;
   }

   public void setEndDate(Date endDate) {
	  this.endDate = endDate;
   }
}
