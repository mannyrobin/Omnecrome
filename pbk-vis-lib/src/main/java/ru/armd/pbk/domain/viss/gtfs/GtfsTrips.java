package ru.armd.pbk.domain.viss.gtfs;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен "Рейсов".
 */
public class GtfsTrips
	extends BaseDomain {

   private Date workDate;
   private Integer routeId;
   private Integer tripId;
   private String tripShortName;
   private Integer directionId;
   private Integer tripType;

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

   public Integer getTripType() {
	  return tripType;
   }

   public void setTripType(Integer tripType) {
	  this.tripType = tripType;
   }

}
