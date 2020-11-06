package ru.armd.pbk.views.plan;

import java.util.Date;

public class TimesheetReportView {

   private String name;
   private String patronumic;
   private String surname;
   private String personalNumber;
   private Double factHours;
   private Double planHours;
   private Long shiftId;
   private Date workDate;
   private Double sumFactHours;
   private Double sumPlanHours;
   private Double sumFactDays;
   private Double sumPlanDays;

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getPatronumic() {
	  return patronumic;
   }

   public void setPatronumic(String patronumic) {
	  this.patronumic = patronumic;
   }

   public String getSurname() {
	  return surname;
   }

   public void setSurname(String surname) {
	  this.surname = surname;
   }

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }

   public Double getFactHours() {
	  return factHours;
   }

   public void setFactHours(Double factHours) {
	  this.factHours = factHours;
   }

   public Double getPlanHours() {
	  return planHours;
   }

   public void setPlanHours(Double planHours) {
	  this.planHours = planHours;
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
   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

}
