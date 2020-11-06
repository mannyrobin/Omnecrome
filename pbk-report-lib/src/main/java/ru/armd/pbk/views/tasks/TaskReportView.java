package ru.armd.pbk.views.tasks;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Набор параметров задания для отображения в печатной форме.
 */
public class TaskReportView
	implements IReportView {

   private Date taskDate;
   private String bsoNumber;
   private String taskState;
   private String personalNumber;
   private String licenseDetails;
   private String employeeSurname;
   private String employeeName;
   private String employeePatronumic;
   private String employeeSignSurname;
   private String employeeSignName;
   private String employeeSignPatronumic;
   private String puskNumber;
   private Date workStartTime;
   private Date workEndTime;
   private Date breakStartTime;
   private Date breakEndTime;
   private String specialMark;
   private String specialTask;
   private String countyName;
   private Date licenseEndDate;

   public Date getTaskDate() {
	  return taskDate;
   }

   public void setTaskDate(Date taskDate) {
	  this.taskDate = taskDate;
   }

   public String getBsoNumber() {
	  return bsoNumber;
   }

   public void setBsoNumber(String bsoNumber) {
	  this.bsoNumber = bsoNumber;
   }

   public String getTaskState() {
	  return taskState;
   }

   public void setTaskState(String taskState) {
	  this.taskState = taskState;
   }

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }

   public String getLicenseDetails() {
	  return licenseDetails;
   }

   public void setLicenseDetails(String licenseDetails) {
	  this.licenseDetails = licenseDetails;
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

   public String getPuskNumber() {
	  return puskNumber;
   }

   public void setPuskNumber(String puskNumber) {
	  this.puskNumber = puskNumber;
   }

   public Date getWorkStartTime() {
	  return workStartTime;
   }

   public void setWorkStartTime(Date workStartTime) {
	  this.workStartTime = workStartTime;
   }

   public Date getWorkEndTime() {
	  return workEndTime;
   }

   public void setWorkEndTime(Date workEndTime) {
	  this.workEndTime = workEndTime;
   }

   public Date getBreakStartTime() {
	  return breakStartTime;
   }

   public void setBreakStartTime(Date breakStartTime) {
	  this.breakStartTime = breakStartTime;
   }

   public Date getBreakEndTime() {
	  return breakEndTime;
   }

   public void setBreakEndTime(Date breakEndTime) {
	  this.breakEndTime = breakEndTime;
   }

   @Override
   public List<Object> getReportList() {
	  return new ArrayList<>();
   }

   @Override
   public List<String> getReportHeaders() {
	  return new ArrayList<>();
   }

   public String getEmployeeSignSurname() {
	  return employeeSignSurname;
   }

   public void setEmployeeSignSurname(String employeeSignSurname) {
	  this.employeeSignSurname = employeeSignSurname;
   }

   public String getEmployeeSignName() {
	  return employeeSignName;
   }

   public void setEmployeeSignName(String employeeSignName) {
	  this.employeeSignName = employeeSignName;
   }

   public String getEmployeeSignPatronumic() {
	  return employeeSignPatronumic;
   }

   public void setEmployeeSignPatronumic(String employeeSignPatronumic) {
	  this.employeeSignPatronumic = employeeSignPatronumic;
   }

   public String getSpecialMark() {
	  return specialMark;
   }

   public void setSpecialMark(String specialMark) {
	  this.specialMark = specialMark;
   }

   public String getSpecialTask() {
	  return specialTask;
   }

   public void setSpecialTask(String specialTask) {
	  this.specialTask = specialTask;
   }

   public String getCountyName() {
	  return countyName;
   }

   public void setCountyName(String countyName) {
	  this.countyName = countyName;
   }

   public Date getLicenseEndDate() {
	  return licenseEndDate;
   }

   public void setLicenseEndDate(Date licenseEndDate) {
	  this.licenseEndDate = licenseEndDate;
   }

}
