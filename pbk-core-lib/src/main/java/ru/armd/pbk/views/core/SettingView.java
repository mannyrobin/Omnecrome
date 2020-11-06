package ru.armd.pbk.views.core;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * Представление для настройки системы. Предполагает read only использование
 * после заполнения в mapper'e.
 */
public class SettingView
	extends BaseGridView {

   private Long id;

   private String code;

   private String name;

   private String value;

   private String description;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getValue() {
	  return value;
   }

   public void setValue(String value) {
	  this.value = value;
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
}
