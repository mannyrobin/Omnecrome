package ru.armd.pbk.views.unionanalysis;

import ru.armd.pbk.core.views.BaseGridView;

public class UnionByStopView
	extends BaseGridView {

   private Integer workDayTypeId;
   private Long routeId;
   private Long tripId;
   private Long asmppPassengersIncludedCount;
   private Long asmppPassengersLeftCount;
   private Long asmppPassengersTransportedCount;
   private Long askpPassengersCount;
   private String direction;
   private String stopName;
   private Long stopSequence;
   private Long stopId;

   public Integer getWorkDayTypeId() {
	  return workDayTypeId;
   }

   public void setWorkDayTypeId(Integer workDayTypeId) {
	  this.workDayTypeId = workDayTypeId;
   }

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public Long getTripId() {
	  return tripId;
   }

   public void setTripId(Long tripId) {
	  this.tripId = tripId;
   }

   public Long getAsmppPassengersIncludedCount() {
	  return asmppPassengersIncludedCount;
   }

   public void setAsmppPassengersIncludedCount(Long asmppPassengersIncludedCount) {
	  this.asmppPassengersIncludedCount = asmppPassengersIncludedCount;
   }

   public Long getAsmppPassengersLeftCount() {
	  return asmppPassengersLeftCount;
   }

   public void setAsmppPassengersLeftCount(Long asmppPassengersLeftCount) {
	  this.asmppPassengersLeftCount = asmppPassengersLeftCount;
   }

   public Long getAsmppPassengersTransportedCount() {
	  return asmppPassengersTransportedCount;
   }

   public void setAsmppPassengersTransportedCount(
	   Long asmppPassengersTransportedCount) {
	  this.asmppPassengersTransportedCount = asmppPassengersTransportedCount;
   }

   public Long getAskpPassengersCount() {
	  return askpPassengersCount;
   }

   public void setAskpPassengersCount(Long askpPassengersCount) {
	  this.askpPassengersCount = askpPassengersCount;
   }

   public String getDirection() {
	  return direction;
   }

   public void setDirection(String direction) {
	  this.direction = direction;
   }

   public String getStopName() {
	  return stopName;
   }

   public void setStopName(String stopName) {
	  this.stopName = stopName;
   }

   public Long getStopSequence() {
	  return stopSequence;
   }

   public void setStopSequence(Long stopSequence) {
	  this.stopSequence = stopSequence;
   }

   public Long getStopId() {
	  return stopId;
   }

   public void setStopId(Long stopId) {
	  this.stopId = stopId;
   }

}
