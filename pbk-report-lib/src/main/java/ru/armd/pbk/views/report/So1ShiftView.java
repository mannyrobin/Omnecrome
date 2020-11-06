package ru.armd.pbk.views.report;

/**
 * View для смены контролёра.
 */
public class So1ShiftView
	extends SoView {

   private String date;
   private Long shiftId;
   private String shiftDescription;
   private Long shiftMode;
   private Boolean isReserve;

   public String getDate() {
	  return date;
   }

   public void setDate(String date) {
	  this.date = date;
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

   public Long getShiftMode() {
	  return shiftMode;
   }

   public void setShiftMode(Long shiftMode) {
	  this.shiftMode = shiftMode;
   }

   public String getShiftDescription() {
	  return shiftDescription;
   }

   public void setShiftDescription(String shiftDescription) {
	  this.shiftDescription = shiftDescription;
   }
}
