package ru.armd.pbk.views.tasks;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * Набор параметров отчёта по заданию для отображения в печатной форме.
 */
public class TaskReportReportView
	extends BaseGridView {

   private Long id;
   private String employeeSurname;
   private String employeeName;
   private String employeePatronumic;
   private String personalNumber;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getEmployeeSurname() {
	  return employeeSurname;
   }

   public void setEmployeeSurname(String employeeSurname) {
	  this.employeeSurname = employeeSurname;
   }

   public String getEmployeeName() {
	  return employeeName;
   }

   public void setEmployeeName(String employeeName) {
	  this.employeeName = employeeName;
   }

   public String getEmployeePatronumic() {
	  return employeePatronumic;
   }

   public void setEmployeePatronumic(String employeePatronumic) {
	  this.employeePatronumic = employeePatronumic;
   }

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }
}
