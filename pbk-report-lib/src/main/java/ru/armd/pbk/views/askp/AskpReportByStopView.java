package ru.armd.pbk.views.askp;

import ru.armd.pbk.core.views.BaseGridView;

public class AskpReportByStopView
	extends BaseGridView {

   private Long stopId;
   private String stopName;
   private String routeNumber;
   private Integer ticketCount;
   private Integer ticketCountPaid;
   private Integer ticketCountFree;

   public Long getStopId() {
	  return stopId;
   }

   public void setStopId(Long stopId) {
	  this.stopId = stopId;
   }

   public String getStopName() {
	  return stopName;
   }

   public void setStopName(String stopName) {
	  this.stopName = stopName;
   }

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public Integer getTicketCount() {
	  return ticketCount;
   }

   public void setTicketCount(Integer ticketCount) {
	  this.ticketCount = ticketCount;
   }

   public Integer getTicketCountPaid() {
	  return ticketCountPaid;
   }

   public void setTicketCountPaid(Integer ticketCountPaid) {
	  this.ticketCountPaid = ticketCountPaid;
   }

   public Integer getTicketCountFree() {
	  return ticketCountFree;
   }

   public void setTicketCountFree(Integer ticketCountFree) {
	  this.ticketCountFree = ticketCountFree;
   }

}
