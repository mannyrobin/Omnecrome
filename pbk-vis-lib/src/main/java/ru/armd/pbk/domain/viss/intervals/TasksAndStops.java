package ru.armd.pbk.domain.viss.intervals;


/**
 * Класс TripScheduleStop.
 */
public class TasksAndStops {

   private Integer routeId;
   private Integer vehicleId;
   private Integer equipmentId;
   private Integer grafic;
   private Integer tripId;
   private Integer serviceId;
   private Integer shiftNum;
   private Integer startTime;
   private Integer endTime;
   private Integer tripNum;
   private Integer stopNum;
   private Integer stopId;
   private Double latitude;
   private Double longitude;

   public Integer getRouteId() {
	  return routeId;
   }

   public void setRouteId(Integer routeId) {
	  this.routeId = routeId;
   }

   public Integer getVehicleId() {
	  return vehicleId;
   }

   public void setVehicleId(Integer vehicleId) {
	  this.vehicleId = vehicleId;
   }

   public Integer getEquipmentId() {
	  return equipmentId;
   }

   public void setEquipmentId(Integer equipmentId) {
	  this.equipmentId = equipmentId;
   }

   public Integer getGrafic() {
	  return grafic;
   }

   public void setGrafic(Integer grafic) {
	  this.grafic = grafic;
   }

   public Integer getTripId() {
	  return tripId;
   }

   public void setTripId(Integer tripId) {
	  this.tripId = tripId;
   }

   public Integer getServiceId() {
	  return serviceId;
   }

   public void setServiceId(Integer serviceId) {
	  this.serviceId = serviceId;
   }

   public Integer getShiftNum() {
	  return shiftNum;
   }

   public void setShiftNum(Integer shiftNum) {
	  this.shiftNum = shiftNum;
   }

   public Integer getStartTime() {
	  return startTime;
   }

   public void setStartTime(Integer startTime) {
	  this.startTime = startTime;
   }

   public Integer getEndTime() {
	  return endTime;
   }

   public void setEndTime(Integer endTime) {
	  this.endTime = endTime;
   }

   public Integer getTripNum() {
	  return tripNum;
   }

   public void setTripNum(Integer tripNum) {
	  this.tripNum = tripNum;
   }

   public Integer getStopNum() {
	  return stopNum;
   }

   public void setStopNum(Integer stopNum) {
	  this.stopNum = stopNum;
   }

   public Integer getStopId() {
	  return stopId;
   }

   public void setStopId(Integer stopId) {
	  this.stopId = stopId;
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
}
