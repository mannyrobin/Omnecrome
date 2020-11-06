package ru.armd.pbk.domain.nsi.askp;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

public class TicketCheckByType
	extends BaseDomain {

   private Date workDate;
   private Long ticketTypeId;
   private String askpRouteCode;
   private Integer askpChecksCount;
   private Long routeId;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Long getTicketTypeId() {
	  return ticketTypeId;
   }

   public void setTicketTypeId(Long ticketTypeId) {
	  this.ticketTypeId = ticketTypeId;
   }

   public String getAskpRouteCode() {
	  return askpRouteCode;
   }

   public void setAskpRouteCode(String askpRouteCode) {
	  this.askpRouteCode = askpRouteCode;
   }

   public Integer getAskpChecksCount() {
	  return askpChecksCount;
   }

   public void setAskpChecksCount(Integer askpChecksCount) {
	  this.askpChecksCount = askpChecksCount;
   }

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

}

