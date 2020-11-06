package ru.armd.pbk.views.core;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View объект для отображения роли пользователя.
 */
public class RoleView
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
