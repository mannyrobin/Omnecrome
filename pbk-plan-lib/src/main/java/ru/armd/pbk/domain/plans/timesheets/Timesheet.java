package ru.armd.pbk.domain.plans.timesheets;

import ru.armd.pbk.core.domain.BaseDomain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Домен табеля сотрудника на день.
 */
public class Timesheet
	extends BaseDomain {

   private Long employeeId;
   private Date workDate;
   private BigDecimal factHours;
   private int planHours;
   private boolean isReserve;
   private Long shiftId;
   private String shiftDescription;

   public Long getEmployeeId() {
	  return employeeId;
   }

   public void setEmployeeId(Long employeeId) {
	  this.employeeId = employeeId;
   }

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public BigDecimal getFactHours() {
	  return factHours;
   }

   public void setFactHours(BigDecimal factHours) {
	  this.factHours = factHours;
   }

   public int getPlanHours() {
	  return planHours;
   }

   public void setPlanHours(int planHours) {
	  this.planHours = planHours;
   }

   public boolean getIsReserve() {
	  return isReserve;
   }

   public void setIsReserve(boolean isReserve) {
	  this.isReserve = isReserve;
   }

   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }

   public String getShiftDescription() {
	  return shiftDescription;
   }

   public void setShiftDescription(String shiftDescription) {
	  this.shiftDescription = shiftDescription;
   }
}