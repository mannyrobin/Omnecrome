package ru.armd.pbk.views.plans.schedules;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.HyphenSepratedDateSerializer;

import java.util.Date;

/**
 * View смены расписания плана отдела.
 */
public class ShiftInfoView {

   private Long id;
   @JsonSerialize(using = HyphenSepratedDateSerializer.class)
   private Date planDate;
   private Long shiftId;
   private String shiftDescription;
   private Boolean isReserve;

   public Date getPlanDate() {
	  return planDate;
   }

   public void setPlanDate(Date planDate) {
	  this.planDate = planDate;
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }

   public Boolean getIsReserve() {
	  return isReserve;
   }

   public void setIsReserve(Boolean isReserve) {
	  this.isReserve = isReserve;
   }

   public String getShiftDescription() {
	  return shiftDescription;
   }

   public void setShiftDescription(String shiftDescription) {
	  this.shiftDescription = shiftDescription;
   }
}
