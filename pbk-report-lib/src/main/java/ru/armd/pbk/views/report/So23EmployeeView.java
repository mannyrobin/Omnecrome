package ru.armd.pbk.views.report;

/**
 * View для контролера (для использования в отчёте "Сводные данные по работе контролеров за период").
 */
public class So23EmployeeView
	extends SoView {

   private String name;
   private String personalNumberId;
  

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }
   
   public String getPersonalNumberId() {
	  return personalNumberId;
   }

   public void setPersonalNumberId(String personalNumberId) {
		  this.personalNumberId = personalNumberId;
   }
   
 }
