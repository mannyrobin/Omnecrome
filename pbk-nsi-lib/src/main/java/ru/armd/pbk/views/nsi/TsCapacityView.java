package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * Представление вместимость ТС.
 */
public class TsCapacityView
	extends BaseGridView {

   private Long id;
   private String cod;
   private String name;
   private String description;
   private boolean isDelete;
   private String tsName;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getCod() {
	  return cod;
   }

   public void setCod(String cod) {
	  this.cod = cod;
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

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean isDelete) {
	  this.isDelete = isDelete;
   }

   public String getTsName() {
	  return tsName;
   }

   public void setTsName(String tsName) {
	  this.tsName = tsName;
   }

}
