package ru.armd.pbk.views.plans.timesheets;

import ru.armd.pbk.core.views.BaseGridView;

import java.util.Map;

/**
 * View табелей сотрудника.
 */
public class EmployeePlanTimesheetsView
	extends BaseGridView {

   private Long id;
   private String name;
   private String surname;
   private String patronumic;
   private String personalNumber;
   private Double sumFactHours;
   private Double sumPlanHours;
   private Double sumFactDays;
   private Double sumPlanDays;
   private Map<String, TimesheetInfoView> timesheets;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }

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

   public Double getSumFactHours() {
	  return sumFactHours;
   }

   public void setSumFactHours(Double sumFactHours) {
	  this.sumFactHours = sumFactHours;
   }

   public Double getSumPlanHours() {
	  return sumPlanHours;
   }

   public void setSumPlanHours(Double sumPlanHours) {
	  this.sumPlanHours = sumPlanHours;
   }

   public Double getSumFactDays() {
	  return sumFactDays;
   }

   public void setSumFactDays(Double sumFactDays) {
	  this.sumFactDays = sumFactDays;
   }

   public Double getSumPlanDays() {
	  return sumPlanDays;
   }

   public void setSumPlanDays(Double sumPlanDays) {
	  this.sumPlanDays = sumPlanDays;
   }
   public Map<String, TimesheetInfoView> getTimesheets() {
	  return timesheets;
   }

   public void setTimesheets(Map<String, TimesheetInfoView> timesheets) {
	  this.timesheets = timesheets;
   }
}
