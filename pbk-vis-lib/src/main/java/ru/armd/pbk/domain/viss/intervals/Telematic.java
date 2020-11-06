package ru.armd.pbk.domain.viss.intervals;

import java.util.Date;

/**
 * Класс Telematic.
 */
public class Telematic {

   private Integer equipmentId;
   private Date time;
   private Double latitude;
   private Double longitude;
   private StopPoint nearestStop;
   private Double distance;

   public Telematic copy() {
	  Telematic newItem = new Telematic();
	  newItem.equipmentId = this.equipmentId;
	  newItem.time = this.time;
	  newItem.latitude = this.latitude;
	  newItem.longitude = this.longitude;

	  return newItem;
   }

   public Integer getEquipmentId() {
	  return equipmentId;
   }

   public void setEquipmentId(Integer equipmentId) {
	  this.equipmentId = equipmentId;
   }

   public Date getTime() {
	  return time;
   }

   public void setTime(Date time) {
	  this.time = time;
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

   public StopPoint getNearestStop() {
	  return nearestStop;
   }

   public void setNearestStop(StopPoint nearestStop) {
	  this.nearestStop = nearestStop;
   }

   public Double getDistance() {
	  return distance;
   }

   public void setDistance(Double distance) {
	  this.distance = distance;
   }
}
