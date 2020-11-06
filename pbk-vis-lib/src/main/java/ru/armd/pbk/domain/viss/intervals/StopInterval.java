package ru.armd.pbk.domain.viss.intervals;

import java.util.Date;

/**
 * Класс StopInterval.
 */
public class StopInterval {

   private Date workDate;
   private Integer asduRouteId;
   private Integer grafic;
   private Integer tripId;
   private Integer serviceId;
   private Integer shiftNum;
   private Integer tripNum;
   private Integer tripVariant;
   private Integer orderNum;
   private Integer stopId;
   private Date stopArrivalTime;
   private Integer vehicleId;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getAsduRouteId() {
	  return asduRouteId;
   }

   public void setAsduRouteId(Integer asduRouteId) {
	  this.asduRouteId = asduRouteId;
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

   public Integer getTripNum() {
	  return tripNum;
   }

   public void setTripNum(Integer tripNum) {
	  this.tripNum = tripNum;
   }

   public Integer getTripVariant() {
	  return tripVariant;
   }

   public void setTripVariant(Integer tripVariant) {
	  this.tripVariant = tripVariant;
   }

   public Integer getOrderNum() {
	  return orderNum;
   }

   public void setOrderNum(Integer orderNum) {
	  this.orderNum = orderNum;
   }

   public Integer getStopId() {
	  return stopId;
   }

   public void setStopId(Integer stopId) {
	  this.stopId = stopId;
   }

   public Date getStopArrivalTime() {
	  return stopArrivalTime;
   }

   public void setStopArrivalTime(Date stopArrivalTime) {
	  this.stopArrivalTime = stopArrivalTime;
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

   public Integer getVehicleId() {
	  return vehicleId;
   }

   public void setVehicleId(Integer vehicleId) {
	  this.vehicleId = vehicleId;
   }
}
