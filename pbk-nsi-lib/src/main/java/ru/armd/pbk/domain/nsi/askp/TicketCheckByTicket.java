package ru.armd.pbk.domain.nsi.askp;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Агригация проверки билетов.
 */
public class TicketCheckByTicket
	extends BaseDomain {

   private Date workDate;
   private String askpRouteCode;
   private String moveCode;
   private Long routeId;
   private String ticketName;
   private String ticketCode;
   private String askpChecksCounties;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
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

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public String getAskpChecksCounties() {
	  return askpChecksCounties;
   }

   public void setAskpChecksCounties(String askpChecksCounties) {
	  this.askpChecksCounties = askpChecksCounties;
   }

   public String getTicketName() {
	  return ticketName;
   }

   public void setTicketName(String ticketName) {
	  this.ticketName = ticketName;
   }

   public String getTicketCode() {
	  return ticketCode;
   }

   public void setTicketCode(String ticketCode) {
	  this.ticketCode = ticketCode;
   }

}
