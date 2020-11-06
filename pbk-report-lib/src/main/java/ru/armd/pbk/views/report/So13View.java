package ru.armd.pbk.views.report;

/**
 * View для вида билета (для использования в отчёте "Маршруты и выходы").
 */
public class So13View {

   private String ticketTypeCode;
   private String ticketTypeName;
   private String countByHour;
   private String workDate;

   public String getTicketTypeCode() {
	  return ticketTypeCode;
   }

   public void setTicketTypeCode(String ticketTypeCode) {
	  this.ticketTypeCode = ticketTypeCode;
   }

   public String getTicketTypeName() {
	  return ticketTypeName;
   }

   public void setTicketTypeName(String ticketTypeName) {
	  this.ticketTypeName = ticketTypeName;
   }

   public String getCountByHour() {
	  return countByHour;
   }

   public void setCountByHour(String countByHour) {
	  this.countByHour = countByHour;
   }

   public String getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(String workDate) {
	  this.workDate = workDate;
   }

}
