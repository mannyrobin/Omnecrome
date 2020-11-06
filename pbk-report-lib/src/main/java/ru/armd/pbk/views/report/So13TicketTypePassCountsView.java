package ru.armd.pbk.views.report;

import java.util.Map;

/**
 * View для связки вида билета и сведений о проходах по виду билета за сутки (для использования в отчёте "Маршруты и выходы").
 */
public class So13TicketTypePassCountsView
	extends SoView {

   private So13TicketTypeView ticketType;
   private Map<String, Integer> passCounts;

   /**
	* Конструктор по виду билета и сведениям о проходах по виду билета за сутки.
	*
	* @param ticketType вид билета
	* @param passCounts сведения о проходах по виду билета за сутки
	*/
   public So13TicketTypePassCountsView(So13TicketTypeView ticketType, Map<String, Integer> passCounts) {
	  this.setCnt(ticketType.getCnt());
	  this.setId(ticketType.getId());
	  this.ticketType = ticketType;
	  this.passCounts = passCounts;
   }

   public So13TicketTypeView getTicketType() {
	  return ticketType;
   }

   public void setTicketType(So13TicketTypeView ticketType) {
	  this.ticketType = ticketType;
   }

   public Map<String, Integer> getPassCounts() {
	  return passCounts;
   }

   public void setPassCounts(Map<String, Integer> passCounts) {
	  this.passCounts = passCounts;
   }
}
