package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseDomain;

/**
 * Домен для поля отчета сотрудника.
 */
public class TaskReportField
	extends BaseDomain {

   private String name;
   private String description;

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
