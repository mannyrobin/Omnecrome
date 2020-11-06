package ru.armd.pbk.views.nsi.pusk;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View ПУсКа.
 */
public class PuskView
	extends BaseGridView {

   private Long id;
   private String puskNumber;
   private boolean isDelete;
   private String puskModel;
   private String description;
   private String owner;
   private String dptName;

   public String getDptName() {
	  return dptName;
   }

   public void setDptName(String dptName) {
	  this.dptName = dptName;
   }

   public String getPuskModel() {
	  return puskModel;
   }

   public void setPuskModel(String puskModel) {
	  this.puskModel = puskModel;
   }

   public String getPuskNumber() {
	  return puskNumber;
   }

   public void setPuskNumber(String puskNumber) {
	  this.puskNumber = puskNumber;
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean isDelete) {
	  this.isDelete = isDelete;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public String getOwner() {
	  return owner;
   }

   public void setOwner(String owner) {
	  this.owner = owner;
   }

}
