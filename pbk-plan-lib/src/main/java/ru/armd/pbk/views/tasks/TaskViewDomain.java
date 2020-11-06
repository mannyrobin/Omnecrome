package ru.armd.pbk.views.tasks;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;

import java.util.Date;

/**
 * Первичный view для заданий.
 */
public class TaskViewDomain
	extends BaseGridView {

   private Long id;

   @JsonSerialize(using = TaskDateSerializer.class)
   private Date taskDate;
   private String bsoNumber;
   private String taskState;
   private String employeeName;
   private String changeEmployeeName;
   private String departmentName;
   private String shiftName;
   private String changeShiftName;
   private String planVenueName;
   private String countyName;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

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

   public String getEmployeeName() {
	  return employeeName;
   }

   public void setEmployeeName(String employeeName) {
	  this.employeeName = employeeName;
   }

   public String getDepartmentName() {
	  return departmentName;
   }

   public void setDepartmentName(String departmentName) {
	  this.departmentName = departmentName;
   }

   public String getChangeEmployeeName() {
	  return changeEmployeeName;
   }

   public void setChangeEmployeeName(String changeEmployeeName) {
	  this.changeEmployeeName = changeEmployeeName;
   }

   public String getShiftName() {
	  return shiftName;
   }

   public void setShiftName(String shiftName) {
	  this.shiftName = shiftName;
   }

   public String getChangeShiftName() {
	  return changeShiftName;
   }

   public void setChangeShiftName(String changeShiftName) {
	  this.changeShiftName = changeShiftName;
   }

   public String getPlanVenueName() {
	  return planVenueName;
   }

   public void setPlanVenueName(String planVenueName) {
	  this.planVenueName = planVenueName;
   }

   public String getCountyName() {
	  return countyName;
   }

   public void setCountyName(String countyName) {
	  this.countyName = countyName;
   }
}
