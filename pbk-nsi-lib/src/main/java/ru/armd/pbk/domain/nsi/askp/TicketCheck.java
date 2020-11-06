package ru.armd.pbk.domain.nsi.askp;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Проверка билета.
 */
public class TicketCheck
	extends BaseDomain {

   private Date workDate;
   private String moveCode;
   private String askpRouteCode;
   private Long routeId;
   private Long ticketId;
   private Date checkTime;
   private String askpCheckId;
   private String ticketCode;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public Long getTicketId() {
	  return ticketId;
   }

   public void setTicketId(Long ticketId) {
	  this.ticketId = ticketId;
   }

   public Date getCheckTime() {
	  return checkTime;
   }

   public void setCheckTime(Date checkTime) {
	  this.checkTime = checkTime;
   }

   public String getAskpCheckId() {
	  return askpCheckId;
   }

   public void setAskpCheckId(String askpCheckId) {
	  this.askpCheckId = askpCheckId;
   }

   public String getMoveCode() {
	  return moveCode;
   }

   public void setMoveCode(String moveCode) {
	  this.moveCode = moveCode;
   }

   public String getAskpRouteCode() {
	  return askpRouteCode;
   }

   public void setAskpRouteCode(String askpRouteCode) {
	  this.askpRouteCode = askpRouteCode;
   }

   public String getTicketCode() {
	  return ticketCode;
   }

   public void setTicketCode(String ticketCode) {
	  this.ticketCode = ticketCode;
   }

}
