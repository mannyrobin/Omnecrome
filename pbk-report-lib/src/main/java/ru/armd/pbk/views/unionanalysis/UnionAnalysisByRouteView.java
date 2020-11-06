package ru.armd.pbk.views.unionanalysis;

import ru.armd.pbk.core.views.BaseGridView;

public class UnionAnalysisByRouteView
	extends BaseGridView {

   private Long routeId;
   private String routeName;
   private Long workdayAsmppPassengersIncludedCount;
   private Long workdayAsmppPassengersLeftCount;
   private Long workdayAsmppPassengersTransportedCount;
   private Long workdayAskpPassengersCount;
   private Long holidayAsmppPassengersIncludedCount;
   private Long holidayAsmppPassengersLeftCount;
   private Long holidayAsmppPassengersTransportedCount;
   private Long holidayAskpPassengersCount;
   private String routeFilterName;

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public String getRouteName() {
	  return routeName;
   }

   public void setRouteName(String routeName) {
	  this.routeName = routeName;
   }

   public Long getWorkdayAsmppPassengersIncludedCount() {
	  return workdayAsmppPassengersIncludedCount;
   }

   public void setWorkdayAsmppPassengersIncludedCount(Long workdayAsmppPassengersIncludedCount) {
	  this.workdayAsmppPassengersIncludedCount = workdayAsmppPassengersIncludedCount;
   }

   public Long getWorkdayAsmppPassengersLeftCount() {
	  return workdayAsmppPassengersLeftCount;
   }

   public void setWorkdayAsmppPassengersLeftCount(Long workdayAsmppPassengersLeftCount) {
	  this.workdayAsmppPassengersLeftCount = workdayAsmppPassengersLeftCount;
   }

   public Long getWorkdayAsmppPassengersTransportedCount() {
	  return workdayAsmppPassengersTransportedCount;
   }

   public void setWorkdayAsmppPassengersTransportedCount(Long workdayAsmppPassengersTransportedCount) {
	  this.workdayAsmppPassengersTransportedCount = workdayAsmppPassengersTransportedCount;
   }

   public Long getWorkdayAskpPassengersCount() {
	  return workdayAskpPassengersCount;
   }

   public void setWorkdayAskpPassengersCount(Long workdayAskpPassengersCount) {
	  this.workdayAskpPassengersCount = workdayAskpPassengersCount;
   }

   public Long getHolidayAsmppPassengersIncludedCount() {
	  return holidayAsmppPassengersIncludedCount;
   }

   public void setHolidayAsmppPassengersIncludedCount(Long holidayAsmppPassengersIncludedCount) {
	  this.holidayAsmppPassengersIncludedCount = holidayAsmppPassengersIncludedCount;
   }

   public Long getHolidayAsmppPassengersLeftCount() {
	  return holidayAsmppPassengersLeftCount;
   }

   public void setHolidayAsmppPassengersLeftCount(Long holidayAsmppPassengersLeftCount) {
	  this.holidayAsmppPassengersLeftCount = holidayAsmppPassengersLeftCount;
   }

   public Long getHolidayAsmppPassengersTransportedCount() {
	  return holidayAsmppPassengersTransportedCount;
   }

   public void setHolidayAsmppPassengersTransportedCount(Long holidayAsmppPassengersTransportedCount) {
	  this.holidayAsmppPassengersTransportedCount = holidayAsmppPassengersTransportedCount;
   }

   public Long getHolidayAskpPassengersCount() {
	  return holidayAskpPassengersCount;
   }

   public void setHolidayAskpPassengersCount(Long holidayAskpPassengersCount) {
	  this.holidayAskpPassengersCount = holidayAskpPassengersCount;
   }

   public String getRouteFilterName() {
	  return routeFilterName;
   }

   public void setRouteFilterName(String routeFilterName) {
	  this.routeFilterName = routeFilterName;
   }

}
