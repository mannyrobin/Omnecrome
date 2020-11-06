package ru.armd.pbk.views.askp;

public class AskpByRouteView {

   private String routeNumber;
   private Integer ticketCount = 0;
   private Integer ticketCountPaid = 0;
   private Integer ticketCountFree = 0;

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
