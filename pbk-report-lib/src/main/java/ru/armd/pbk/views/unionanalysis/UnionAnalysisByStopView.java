package ru.armd.pbk.views.unionanalysis;

import ru.armd.pbk.core.views.BaseGridView;

public class UnionAnalysisByStopView
	extends BaseGridView {

   private Long routeVariant;
   private String direction;
   private String stopName;
   private Long orderNum;
   private PassengersCountByWorkModeView holiday;
   private PassengersCountByWorkModeView workday;

   public Long getRouteVariant() {
	  return routeVariant;
   }

   public void setRouteVariant(Long routeVariant) {
	  this.routeVariant = routeVariant;
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

   public Long getOrderNum() {
	  return orderNum;
   }

   public void setOrderNum(Long orderNum) {
	  this.orderNum = orderNum;
   }

   public PassengersCountByWorkModeView getHoliday() {
	  return holiday;
   }

   public void setHoliday(PassengersCountByWorkModeView holiday) {
	  this.holiday = holiday;
   }

   public PassengersCountByWorkModeView getWorkday() {
	  return workday;
   }

   public void setWorkday(PassengersCountByWorkModeView workday) {
	  this.workday = workday;
   }

}
