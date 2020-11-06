package ru.armd.pbk.domain.plans.schedules;

import ru.armd.pbk.core.domain.BaseDomain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Домен смены для отображения в расписании плана отдела.
 */
public class ScheduleShift
	extends BaseDomain {

   private Long deptId;
   private Long employeeId;
   private Long shiftId;
   private String shiftDescription;
   private Date planDate;
   private Long shiftModeId;
   private Boolean isReserve;
   private Boolean isForPlanUse;
   private BigDecimal workFactHours;

   public ScheduleShift() {

   }

   public ScheduleShift(Long id, BigDecimal workFactHours, Long employeeId) {
	  super();
	  this.setId(id);
	  this.setWorkFactHours(workFactHours);
	  this.employeeId = employeeId;

   }

   public Long getEmployeeId() {
	  return employeeId;
   }

   public void setEmployeeId(Long employeeId) {
	  this.employeeId = employeeId;
   }

   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }

   public Date getPlanDate() {
	  return planDate;
   }

   public void setPlanDate(Date planDate) {
	  this.planDate = planDate;
   }

   public Boolean getIsReserve() {
	  return isReserve;
   }

   public void setIsReserve(Boolean isReserve) {
	  this.isReserve = isReserve;
   }

   public Long getShiftModeId() {
	  return shiftModeId;
   }

   public void setShiftModeId(Long shiftModeId) {
	  this.shiftModeId = shiftModeId;
   }

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }

   public Boolean getIsForPlanUse() {
	  return isForPlanUse;
   }

   public void setIsForPlanUse(Boolean isForPlanUse) {
	  this.isForPlanUse = isForPlanUse;
   }

   public BigDecimal getWorkFactHours() {
	  return workFactHours;
   }

   public void setWorkFactHours(BigDecimal workFactHours) {
	  this.workFactHours = workFactHours;
   }

   public String getShiftDescription() {
	  return shiftDescription;
   }

   public void setShiftDescription(String shiftDescription) {
	  this.shiftDescription = shiftDescription;
   }
}