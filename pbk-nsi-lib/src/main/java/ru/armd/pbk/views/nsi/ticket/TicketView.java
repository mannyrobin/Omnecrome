package ru.armd.pbk.views.nsi.ticket;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View билета.
 */
public class TicketView
	extends BaseGridView {

   private Long id;
   private String code;
   private String name;
   private String description;
   private String ticketTypeName;
   private String applicationCode;
   private boolean isDelete;
   private String useInAnalysisText;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

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

   public String getTicketTypeName() {
	  return ticketTypeName;
   }

   public void setTicketTypeName(String ticketTypeName) {
	  this.ticketTypeName = ticketTypeName;
   }

   public String getApplicationCode() {
	  return applicationCode;
   }

   public void setApplicationCode(String applicationCode) {
	  this.applicationCode = applicationCode;
   }

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean isDelete) {
	  this.isDelete = isDelete;
   }

   public String getUseInAnalysisText() {
	  return useInAnalysisText;
   }

   public void setUseInAnalysisText(String useInAnalysisText) {
	  this.useInAnalysisText = useInAnalysisText;
   }
}
