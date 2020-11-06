package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * Представление для поля отчета сотрудника.
 */
public class TaskReportFieldView
	extends BaseGridView {

   private Long id;
   private String name;
   private String description;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
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

}
