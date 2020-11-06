package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Представление отчетов билетов.
 */
public class TicketReportView
	implements IReportView {

   private Long id;
   private String code;
   private String name;
   private String description;
   private String ticketTypeName;

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


   @Override
   public List<Object> getReportList() {
	  List<Object> result = new ArrayList<Object>();
	  result.add(id);
	  result.add(code);
	  result.add(name);
	  result.add(description);
	  result.add(ticketTypeName);
	  return result;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("№ п/п", "Код", "Название", "Описание", "Тип билета");
   }

}
