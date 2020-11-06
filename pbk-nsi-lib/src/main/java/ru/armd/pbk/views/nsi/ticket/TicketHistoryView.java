package ru.armd.pbk.views.nsi.ticket;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * View истории ПУсКа.
 */
public class TicketHistoryView
	extends BaseVersionView {

   private String code;
   private String name;
   private String description;
   private Integer ticketTypeId;
   private String applicationCode;

   public String getCode() {
	  return code;
   }

   public void setCode(String code) {
	  this.code = code;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public String getApplicationCode() {
	  return applicationCode;
   }

   public void setApplicationCode(String applicationCode) {
	  this.applicationCode = applicationCode;
   }

   public Integer getTicketTypeId() {
	  return ticketTypeId;
   }

   public void setTicketTypeId(Integer ticketTypeId) {
	  this.ticketTypeId = ticketTypeId;
   }
}
