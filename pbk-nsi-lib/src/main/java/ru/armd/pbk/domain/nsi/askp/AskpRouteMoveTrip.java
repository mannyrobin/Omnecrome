package ru.armd.pbk.domain.nsi.askp;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

public class AskpRouteMoveTrip
	extends BaseDomain {

   private Date date;
   private String routeCode;
   private String moveCode;
   private Long shiftId;

   public Date getDate() {
	  return date;
   }

   public void setDate(Date date) {
	  this.date = date;
   }

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

   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }

}
