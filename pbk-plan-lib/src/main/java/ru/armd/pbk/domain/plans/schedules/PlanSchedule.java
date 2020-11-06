package ru.armd.pbk.domain.plans.schedules;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Расписание плана.
 */
public class PlanSchedule
	extends BaseDomain {
   private Long deptId;
   private Date workDate;
   private Long employeeId;
   private Integer shiftModeId;
   private Long shiftId;
   private boolean isReserve;
   private Long taskId;
   private Long countyId;

   public PlanSchedule() {
	  super();
   }

   /**
	* Конструктор.
	*
	* @param shiftId  смена.
	* @param workDate рабочая дата.
	*/
   public PlanSchedule(Long shiftId, Date workDate) {
	  this();
	  setShiftId(shiftId);
	  setWorkDate(workDate);
   }

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
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

   public boolean isReserve() {
	  return isReserve;
   }

   public void setReserve(boolean isReserve) {
	  this.isReserve = isReserve;
   }

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }

   public Integer getShiftModeId() {
	  return shiftModeId;
   }

   public void setShiftModeId(Integer shiftModeId) {
	  this.shiftModeId = shiftModeId;
   }

   public Long getTaskId() {
	  return taskId;
   }

   public void setTaskId(Long taskId) {
	  this.taskId = taskId;
   }

   public Long getCountyId() {
	  return countyId;
   }

   public void setCountyId(Long countyId) {
	  this.countyId = countyId;
   }
}