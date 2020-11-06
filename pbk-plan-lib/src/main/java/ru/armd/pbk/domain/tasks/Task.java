package ru.armd.pbk.domain.tasks;

import ru.armd.pbk.core.domain.BaseDomain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Домен заданий.
 */
public class Task
	extends BaseDomain {

   private Long deptId;
   private Long taskTypeId;
   private Long bsoId;
   private Long planScheduleId;
   private Long changePlanScheduleId;
   private Long stateId;
   private Long planVenueId;
   private Long countyId;
   private Date workStartTime;
   private Date workEndTime;
   private Date breakStartTime;
   private Date breakEndTime;
   private String specialMark;
   private String specialTask;
   private BigDecimal factHours;
   private String cancelReason;
   private Long shiftId;
   private Long districtId;

   public Long getBsoId() {
	  return bsoId;
   }

   public void setBsoId(Long bsoId) {
	  this.bsoId = bsoId;
   }

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }

   public Long getPlanScheduleId() {
	  return planScheduleId;
   }

   public void setPlanScheduleId(Long planScheduleId) {
	  this.planScheduleId = planScheduleId;
   }

   public Long getChangePlanScheduleId() {
	  return changePlanScheduleId;
   }

   public void setChangePlanScheduleId(Long changePlanScheduleId) {
	  this.changePlanScheduleId = changePlanScheduleId;
   }

   public Long getPlanVenueId() {
	  return planVenueId;
   }

   public void setPlanVenueId(Long planVenueId) {
	  this.planVenueId = planVenueId;
   }

   public Long getStateId() {
	  return stateId;
   }

   public void setStateId(Long stateId) {
	  this.stateId = stateId;
   }

   public Long getTaskTypeId() {
	  return taskTypeId;
   }

   public void setTaskTypeId(Long taskTypeId) {
	  this.taskTypeId = taskTypeId;
   }

   public Long getCountyId() {
	  return countyId;
   }

   public void setCountyId(Long countyId) {
	  this.countyId = countyId;
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

   public BigDecimal getFactHours() {
	  return factHours;
   }

   public void setFactHours(BigDecimal factHours) {
	  this.factHours = factHours;
   }

   public String getCancelReason() {
	  return cancelReason;
   }

   public void setCancelReason(String cancelReason) {
	  this.cancelReason = cancelReason;
   }

   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }

   public Long getDistrictId() {
	  return districtId;
   }

   public void setDistrictId(Long districtId) {
	  this.districtId = districtId;
   }

}