package ru.armd.pbk.domain.viss.gtfs;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен "Поостановочного расписания".
 */
public class GtfsStopTimes
	extends BaseDomain {

   private Date workDate;
   private Integer tripId;
   private Integer arrivalTime;
   private Integer departureTime;
   private Integer stopId;
   private Integer stopSequence;
   private Integer tripNum;
   private Integer shiftNum;
   private Integer grafic;
   private Integer serviceId;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getTripId() {
	  return tripId;
   }

   public void setTripId(Integer tripId) {
	  this.tripId = tripId;
   }

   public Integer getArrivalTime() {
	  return arrivalTime;
   }

   public void setArrivalTime(Integer arrivalTime) {
	  this.arrivalTime = arrivalTime;
   }

   public Integer getDepartureTime() {
	  return departureTime;
   }

   public void setDepartureTime(Integer departureTime) {
	  this.departureTime = departureTime;
   }

   public Integer getStopId() {
	  return stopId;
   }

   public void setStopId(Integer stopId) {
	  this.stopId = stopId;
   }

   public Integer getStopSequence() {
	  return stopSequence;
   }

   public void setStopSequence(Integer stopSequence) {
	  this.stopSequence = stopSequence;
   }

   public Integer getTripNum() {
	  return tripNum;
   }

   public void setTripNum(Integer tripNum) {
	  this.tripNum = tripNum;
   }

   public Integer getShiftNum() {
	  return shiftNum;
   }

   public void setShiftNum(Integer shiftNum) {
	  this.shiftNum = shiftNum;
   }

   public Integer getGrafic() {
	  return grafic;
   }

   public void setGrafic(Integer grafic) {
	  this.grafic = grafic;
   }

   public Integer getServiceId() {
	  return serviceId;
   }

   public void setServiceId(Integer serviceId) {
	  this.serviceId = serviceId;
   }

}
