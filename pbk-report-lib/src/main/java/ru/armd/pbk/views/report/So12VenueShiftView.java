package ru.armd.pbk.views.report;

/**
 * View для связки места встречи и часов работы (для использования в отчёте "Совместный график по местам встреч").
 */
public class So12VenueShiftView
	extends SoView {

   private String county;
   private String district;
   private Long venueId;
   private String venue;
   private Long shiftId;
   private String shiftHours;

   public String getCounty() {
	  return county;
   }

   public void setCounty(String county) {
	  this.county = county;
   }

   public String getDistrict() {
	  return district;
   }

   public void setDistrict(String district) {
	  this.district = district;
   }

   public Long getVenueId() {
	  return venueId;
   }

   public void setVenueId(Long venueId) {
	  this.venueId = venueId;
   }

   public String getVenue() {
	  return venue;
   }

   public void setVenue(String venue) {
	  this.venue = venue;
   }

   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }

   public String getShiftHours() {
	  return shiftHours;
   }

   public void setShiftHours(String shiftHours) {
	  this.shiftHours = shiftHours;
   }
}
