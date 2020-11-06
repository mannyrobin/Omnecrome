package ru.armd.pbk.views.plans.timesheets;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.HyphenSepratedDateSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * View табеля сотрудника.
 */
public class TimesheetInfoView {

   private Long id;
   @JsonSerialize(using = HyphenSepratedDateSerializer.class)
   private Date workDate;
   private BigDecimal factHours;
   private int planHours;
   private boolean isReserve;
   private Long shiftId;
   private String shiftDescription;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
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

   public Boolean getIsReserve() {
	  return isReserve;
   }

   public void setIsReserve(Boolean isReserve) {
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
