package ru.armd.pbk.domain.plans.routes;

import java.util.Date;

public class ASKPTicketCkecks {
   private Long id;
   private Date workDate;
   private Long routeId;
   private String askpRouteCode;
   private String moveCode;
   private Long ticketId;
   private Date checkTime;
   private Long askpCheckId;

   private Long ticketCount;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

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

   public String getAskpRouteCode() {
	  return askpRouteCode;
   }

   public void setAskpRouteCode(String askpRouteCode) {
	  this.askpRouteCode = askpRouteCode;
   }

   public String getMoveCode() {
	  return moveCode;
   }

   public void setMoveCode(String moveCode) {
	  this.moveCode = moveCode;
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

   public Long getAskpCheckId() {
	  return askpCheckId;
   }

   public void setAskpCheckId(Long askpCheckId) {
	  this.askpCheckId = askpCheckId;
   }

   public Long getTicketCount() {
	  return ticketCount;
   }

   public void setTicketCount(Long ticketCount) {
	  this.ticketCount = ticketCount;
   }
}