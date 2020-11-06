package ru.armd.pbk.domain.viss.gtfs;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен "Справочник рейсов с последовательностями остановок из ЭРМ".
 */
public class GtfsTripsStops
	extends BaseDomain {

   private Date workDate;
   private Integer routeId;
   private String routeShortName;
   private String regNum;
   private String routeType;
   private Integer tripId;
   private String tripShortName;
   private Integer directionId;
   private Date startDate;
   private Date endDate;
   private Integer stopSequence;
   private Integer stopId;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getRouteId() {
	  return routeId;
   }

   public void setRouteId(Integer routeId) {
	  this.routeId = routeId;
   }

   public String getRouteShortName() {
	  return routeShortName;
   }

   public void setRouteShortName(String routeShortName) {
	  this.routeShortName = routeShortName;
   }

   public String getRegNum() {
	  return regNum;
   }

   public void setRegNum(String regNum) {
	  this.regNum = regNum;
   }

   public String getRouteType() {
	  return routeType;
   }

   public void setRouteType(String routeType) {
	  this.routeType = routeType;
   }

   public Integer getTripId() {
	  return tripId;
   }

   public void setTripId(Integer tripId) {
	  this.tripId = tripId;
   }

   public String getTripShortName() {
	  return tripShortName;
   }

   public void setTripShortName(String tripShortName) {
	  this.tripShortName = tripShortName;
   }

   public Integer getDirectionId() {
	  return directionId;
   }

   public void setDirectionId(Integer directionId) {
	  this.directionId = directionId;
   }

   public Date getStartDate() {
	  return startDate;
   }

   public void setStartDate(Date startDate) {
	  this.startDate = startDate;
   }

   public Date getEndDate() {
	  return endDate;
   }

   public void setEndDate(Date endDate) {
	  this.endDate = endDate;
   }

   public Integer getStopSequence() {
	  return stopSequence;
   }

   public void setStopSequence(Integer stopSequence) {
	  this.stopSequence = stopSequence;
   }

   public Integer getStopId() {
	  return stopId;
   }

   public void setStopId(Integer stopId) {
	  this.stopId = stopId;
   }

}
