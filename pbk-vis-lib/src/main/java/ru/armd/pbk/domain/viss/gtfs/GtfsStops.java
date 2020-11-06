package ru.armd.pbk.domain.viss.gtfs;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен "Остановок".
 */
public class GtfsStops
	extends BaseDomain {

   private Date workDate;
   private Integer stopId;
   private String stopName;
   private String stopLat;
   private String stopLon;
   private Integer isDeleted;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getStopId() {
	  return stopId;
   }

   public void setStopId(Integer stopId) {
	  this.stopId = stopId;
   }

   public String getStopName() {
	  return stopName;
   }

   public void setStopName(String stopName) {
	  this.stopName = stopName;
   }

   public String getStopLat() {
	  return stopLat;
   }

   public void setStopLat(String stopLat) {
	  this.stopLat = stopLat;
   }

   public String getStopLon() {
	  return stopLon;
   }

   public void setStopLon(String stopLon) {
	  this.stopLon = stopLon;
   }

   public Integer getIsDeleted() {
	  return isDeleted;
   }

   public void setIsDeleted(Integer isDeleted) {
	  this.isDeleted = isDeleted;
   }

}
