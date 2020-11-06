package ru.armd.pbk.domain.plans.schedules;

import ru.armd.pbk.core.domain.BaseDomain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Домен для связки расписание ЕАСУ - плановый календарь.
 */
public class ScheduleWorkMode
	extends BaseDomain {

   private Date workDate;
   private Long employeeId;
   private Long workModeId;
   private BigDecimal workPlanHours;
   private Long psId;
   private Long shiftModeId;
   private Long shiftId;

   public Long getEmployeeId() {
	  return employeeId;
   }

   public void setEmployeeId(Long employeeId) {
	  this.employeeId = employeeId;
   }

   public Long getShiftModeId() {
	  return shiftModeId;
   }

   public void setShiftModeId(Long shiftModeId) {
	  this.shiftModeId = shiftModeId;
   }

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Long getWorkModeId() {
	  return workModeId;
   }

   public void setWorkModeId(Long workModeId) {
	  this.workModeId = workModeId;
   }

   public BigDecimal getWorkPlanHours() {
	  return workPlanHours;
   }

   public void setWorkPlanHours(BigDecimal workPlanHours) {
	  this.workPlanHours = workPlanHours;
   }

   public Long getPsId() {
	  return psId;
   }

   public void setPsId(Long psId) {
	  this.psId = psId;
   }

   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }
}