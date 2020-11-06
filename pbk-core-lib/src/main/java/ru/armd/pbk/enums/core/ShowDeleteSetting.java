package ru.armd.pbk.enums.core;

/**
 * Возможные значения настройки отображения удаленных записей.
 */
public enum ShowDeleteSetting {

   NO(1, "Нет"),
   YES(2, "Да");

   private Integer id;
   private String name;

   private ShowDeleteSetting(Integer id, String name) {
	  this.setId(id);
	  this.setName(name);
   }

   public Integer getId() {
	  return id;
   }

   private void setId(Integer id) {
	  this.id = id;
   }

   public String getName() {
	  return name;
   }

   private void setName(String name) {
	  this.name = name;
   }
}
