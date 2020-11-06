package ru.armd.pbk.domain.plans.brigades;

import java.util.Date;

/**
 * Домен для сопоставления даты с кол-вом сотрудников В резерве.
 */
public class ReserveCount {

   private Date planDate;
   private Long shiftId;
   private int employeeCount;
   private int planVenuesCount;

   public Date getPlanDate() {
	  return planDate;
   }

   public void setPlanDate(Date planDate) {
	  this.planDate = planDate;
   }

   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }

   public int getEmployeeCount() {
	  return employeeCount;
   }

   public void setEmployeeCount(int employeeCount) {
	  this.employeeCount = employeeCount;
   }

   public int getPlanVenuesCount() {
	  return planVenuesCount;
   }

   public void setPlanVenuesCount(int planVenuesCount) {
	  this.planVenuesCount = planVenuesCount;
   }
}