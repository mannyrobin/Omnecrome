package ru.armd.pbk.dto.nsi;

import ru.armd.pbk.core.dto.BaseDTO;

/**
 * ДТО для поля отчета сотрудника.
 */
public class TaskReportFieldDTO
	extends BaseDTO {

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
