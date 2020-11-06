package ru.armd.pbk.views.nsi.department;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * Представление департамента.
 */
public class DepartmentView
	extends BaseGridView {

   private Long id;
   private String easuFhdId;
   private String name;
   private String planCount;
   private String parentName;
   private String description;
   private boolean isDelete;

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
   
   public String getPlanCount() {
		  return planCount;
	   }

	   public void setPlanCount(String planCount) {
		  this.planCount = planCount;
	   }

   public String getParentName() {
	  return parentName;
   }

   public void setParentName(String parentName) {
	  this.parentName = parentName;
   }

   public String getEasuFhdId() {
	  return easuFhdId;
   }

   public void setEasuFhdId(String easuFhdId) {
	  this.easuFhdId = easuFhdId;
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
}
