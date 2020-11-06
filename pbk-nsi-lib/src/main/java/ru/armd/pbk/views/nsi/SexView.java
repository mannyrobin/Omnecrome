package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * Представление "пола".
 *
 * @author nikita_chebotaryov
 */
public class SexView
	extends BaseGridView {

   private Long id;
   private String cod;
   private String name;
   private String description;

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

}
