package ru.armd.pbk.domain.viss.intervals;

/**
 * Класс StopPoint.
 */
public class StopPoint {

   private Integer stopId;
   private Double latitude;
   private Double longitude;
   private Integer tripNum;
   private Integer tripId;
   private Integer stopNum;

   public StopPoint() {
   }

   public StopPoint(Integer stopId, Double latitude, Double longitude, Integer tripNum, Integer tripId, Integer stopNum) {
	  this.stopId = stopId;
	  this.latitude = latitude;
	  this.longitude = longitude;
	  this.tripNum = tripNum;
	  this.tripId = tripId;
	  this.stopNum = stopNum;
   }

   @Override
   public boolean equals(Object obj) {
	  if (obj instanceof StopPoint) {
		 return ((StopPoint) obj).stopId.equals(this.stopId) && ((StopPoint) obj).stopNum.equals(this.stopNum);
	  }
	  return false;
   }

   @Override
   public int hashCode() {
	  return 31 * (31 + this.stopId) + this.stopNum;
   }

   public Double getLatitude() {
	  return latitude;
   }

   public void setLatitude(Double latitude) {
	  this.latitude = latitude;
   }

   public Double getLongitude() {
	  return longitude;
   }

   public void setLongitude(Double longitude) {
	  this.longitude = longitude;
   }

   public Integer getStopId() {
	  return stopId;
   }

   public void setStopId(Integer stopId) {
	  this.stopId = stopId;
   }

   public Integer getTripNum() {
	  return tripNum;
   }

   public void setTripNum(Integer tripNum) {
	  this.tripNum = tripNum;
   }

   public Integer getTripId() {
	  return tripId;
   }

   public void setTripId(Integer tripId) {
	  this.tripId = tripId;
   }

   public Integer getStopNum() {
	  return stopNum;
   }

   public void setStopNum(Integer stopNum) {
	  this.stopNum = stopNum;
   }
}
