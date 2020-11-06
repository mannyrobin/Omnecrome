package ru.armd.pbk.domain.tasks;

import java.util.Date;

public class CheckRoute {
   private Long routeId;
   private Date lastCheckDate;
   private Double routeRatio;
   private int monthCheckCount;
   private int tsCount;
   private Long districtId;
   private Long countyId;

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public Date getLastCheckDate() {
	  return lastCheckDate;
   }

   public void setLastCheckDate(Date lastCheckDate) {
	  this.lastCheckDate = lastCheckDate;
   }

   public Double getRouteRatio() {
	  return routeRatio;
   }

   public void setRouteRatio(Double routeRatio) {
	  this.routeRatio = routeRatio;
   }

   public int getMonthCheckCount() {
	  return monthCheckCount;
   }

   public void setMonthCheckCount(int monthCheckCount) {
	  this.monthCheckCount = monthCheckCount;
   }

   public int getTsCount() {
	  return tsCount;
   }

   public void setTsCount(int tsCount) {
	  this.tsCount = tsCount;
   }

   public Long getDistrictId() {
	  return districtId;
   }

   public void setDistrictId(Long districtId) {
	  this.districtId = districtId;
   }

   public Long getCountyId() {
	  return countyId;
   }

   public void setCountyId(Long countyId) {
	  this.countyId = countyId;
   }
}