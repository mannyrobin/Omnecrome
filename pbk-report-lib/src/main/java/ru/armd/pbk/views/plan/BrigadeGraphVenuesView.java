package ru.armd.pbk.views.plan;

/**
 * Список мест встечи.
 */
public class BrigadeGraphVenuesView {
   private String countyName;
   private String deptName;
   private Long venueId;
   private String venueName;
   private Long shiftId;
   private String shiftHours;
   private Long deptId;

   public Long getVenueId() {
	  return venueId;
   }

   public void setVenueId(Long venueId) {
	  this.venueId = venueId;
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

   public String getCountyName() {
	  return countyName;
   }

   public void setCountyName(String countyName) {
	  this.countyName = countyName;
   }

   public String getDeptName() {
	  return deptName;
   }

   public void setDeptName(String deptName) {
	  this.deptName = deptName;
   }

   public String getVenueName() {
	  return venueName;
   }

   public void setVenueName(String venueName) {
	  this.venueName = venueName;
   }

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }

   /**
	* Копировать место встречи.
	*
	* @return
	*/
   public BrigadeGraphVenuesView cloneVenue() {
	  BrigadeGraphVenuesView vn = new BrigadeGraphVenuesView();
	  vn.countyName = this.countyName;
	  vn.deptId = this.deptId;
	  vn.deptName = this.deptName;
	  vn.venueId = this.venueId;
	  vn.venueName = this.venueName;
	  vn.shiftId = this.shiftId;
	  vn.shiftId = this.shiftId;
	  vn.shiftHours = this.shiftHours;
	  vn.deptId = this.deptId;
	  return vn;
   }
}