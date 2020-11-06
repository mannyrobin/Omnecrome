package ru.armd.pbk.views.nsi.venue;

import ru.armd.pbk.core.views.BaseVersionView;

import java.util.Date;

/**
 * Представлении истории места встречи.
 */
public class VenueHistoryView
	extends BaseVersionView {

   private String name;
   private String cod;
   private String districtName;
   private String countyName;

   private String wktGeom;
   private String description;
   private Long districtId;

   private int shiftI;
   private int shiftII;
   private int shiftIII;
   private int shiftDay;
   private int shiftNight;

   private Date venueVersionEndDate;
   private boolean deleted = false;

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getCod() {
	  return cod;
   }

   public void setCod(String cod) {
	  this.cod = cod;
   }

   public String getWktGeom() {
	  return wktGeom;
   }

   public void setWktGeom(String wktGeom) {
	  this.wktGeom = wktGeom;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Long getDistrictId() {
	  return districtId;
   }

   public void setDistrictId(Long districtId) {
	  this.districtId = districtId;
   }

   public int getShiftI() {
	  return shiftI;
   }

   public void setShiftI(int shiftI) {
	  this.shiftI = shiftI;
   }

   public int getShiftII() {
	  return shiftII;
   }

   public void setShiftII(int shiftII) {
	  this.shiftII = shiftII;
   }

    public int getShiftIII() {
        return shiftIII;
    }

    public void setShiftIII(int shiftIII) {
        this.shiftIII = shiftIII;
    }

    public int getShiftDay() {
	  return shiftDay;
   }

   public void setShiftDay(int shiftDay) {
	  this.shiftDay = shiftDay;
   }


   public String getDistrictName() {
	  return districtName;
   }

   public void setDistrictName(String districtName) {
	  this.districtName = districtName;
   }

   public String getCountyName() {
	  return countyName;
   }

   public void setCountyName(String countyName) {
	  this.countyName = countyName;
   }

   public Date getVenueVersionEndDate() {
	  return venueVersionEndDate;
   }

   public void setVenueVersionEndDate(Date venueVersionEndDate) {
	  this.venueVersionEndDate = venueVersionEndDate;
   }

   @Override
   public Date getVersionEndDate() {
	  Date d = super.getVersionEndDate();
	  if (venueVersionEndDate != null && venueVersionEndDate.before(d)) {
		 return venueVersionEndDate;
	  }
	  return d;
   }

   public boolean isDeleted() {
	  return deleted;
   }

   public void setDeleted(boolean deleted) {
	  this.deleted = deleted;
   }

   public int getShiftNight() {
	  return shiftNight;
   }

   public void setShiftNight(int shiftNight) {
	  this.shiftNight = shiftNight;
   }

}