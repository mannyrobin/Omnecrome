package ru.armd.pbk.domain.viss.intervals;


/**
 * Класс TripScheduleStop.
 */
public class TripScheduleStop {

   private String routeCode;
   private String moveCode;
   private String depotNumber;
   private Long shiftNum;
   private Long routeNum;
   private Long recordNum;
   private String time;
   private String endTime;
   private Long asduStopId;
   private Long ermStopId;
   private Long orderNum;
   private Long tripId;
   private Double latitude;
   private Double longitude;

   public String getRouteCode() {
	  return routeCode;
   }

   public void setRouteCode(String routeCode) {
	  this.routeCode = routeCode;
   }

   public String getMoveCode() {
	  return moveCode;
   }

   public void setMoveCode(String moveCode) {
	  this.moveCode = moveCode;
   }

   public String getDepotNumber() {
	  return depotNumber;
   }

   public void setDepotNumber(String depotNumber) {
	  this.depotNumber = depotNumber;
   }

   public Long getShiftNum() {
	  return shiftNum;
   }

   public void setShiftNum(Long shiftNum) {
	  this.shiftNum = shiftNum;
   }

   public Long getRouteNum() {
	  return routeNum;
   }

   public void setRouteNum(Long routeNum) {
	  this.routeNum = routeNum;
   }

   public Long getRecordNum() {
	  return recordNum;
   }

   public void setRecordNum(Long recordNum) {
	  this.recordNum = recordNum;
   }

   public String getTime() {
	  return time;
   }

   public void setTime(String time) {
	  this.time = time;
   }

   public String getEndTime() {
	  return endTime;
   }

   public void setEndTime(String endTime) {
	  this.endTime = endTime;
   }

   public Long getAsduStopId() {
	  return asduStopId;
   }

   public void setAsduStopId(Long asduStopId) {
	  this.asduStopId = asduStopId;
   }

   public Long getErmStopId() {
	  return ermStopId;
   }

   public void setErmStopId(Long ermStopId) {
	  this.ermStopId = ermStopId;
   }

   public Long getOrderNum() {
	  return orderNum;
   }

   public void setOrderNum(Long orderNum) {
	  this.orderNum = orderNum;
   }

   public Long getTripId() {
	  return tripId;
   }

   public void setTripId(Long tripId) {
	  this.tripId = tripId;
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
