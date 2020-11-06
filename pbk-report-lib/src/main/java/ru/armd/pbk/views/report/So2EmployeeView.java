package ru.armd.pbk.views.report;

import java.util.Date;
import java.util.Map;

/**
 * View для контролёра (для использования в отчёте "Табель фактически отработанного времени").
 */
public class So2EmployeeView
	extends SoView {

   private String name;
   private String surname;
   private String patronumic;
   private String personalNumber;
   private Map<String, So2ShiftHoursView> shifts;
   private Date startDate;
   private Date endDate;
   private String departmentName;

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getSurname() {
	  return surname;
   }

   public void setSurname(String surname) {
	  this.surname = surname;
   }

   public String getPatronumic() {
	  return patronumic;
   }

   public void setPatronumic(String patronumic) {
	  this.patronumic = patronumic;
   }

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }

   public Map<String, So2ShiftHoursView> getShifts() {
	  return shifts;
   }

   public void setShifts(Map<String, So2ShiftHoursView> shifts) {
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

   public String getDepartmentName() {
	  return departmentName;
   }

   public void setDepartmentName(String departmentName) {
	  this.departmentName = departmentName;
   }

}
