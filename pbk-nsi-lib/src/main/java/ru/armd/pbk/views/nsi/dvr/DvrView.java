package ru.armd.pbk.views.nsi.dvr;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View видеорегистраторов.
 */
public class DvrView
	extends BaseGridView {

   private Long id;
   private boolean isDelete;
   private String dvrNumber;
   private String dvrModel;
   private String description;
   private String owner;
   private String dptName;

   public String getDptName() {
	  return dptName;
   }

   public void setDptName(String dptName) {
	  this.dptName = dptName;
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

   public String getDvrNumber() {
	  return dvrNumber;
   }

   public void setDvrNumber(String dvrNumber) {
	  this.dvrNumber = dvrNumber;
   }

   public String getDvrModel() {
	  return dvrModel;
   }

   public void setDvrModel(String dvrModel) {
	  this.dvrModel = dvrModel;
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
