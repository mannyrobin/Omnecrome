package ru.armd.pbk.domain.viss.intervals;

import java.util.Date;

/**
 * Класс FhdOrder.
 */
public class FhdOrder {

   private String routeCode;
   private String moveCode;
   private Date timeFrom;
   private Date timeTo;

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

   public Date getTimeFrom() {
	  return timeFrom;
   }

   public void setTimeFrom(Date timeFrom) {
	  this.timeFrom = timeFrom;
   }

   public Date getTimeTo() {
	  return timeTo;
   }

   public void setTimeTo(Date timeTo) {
	  this.timeTo = timeTo;
   }
}
