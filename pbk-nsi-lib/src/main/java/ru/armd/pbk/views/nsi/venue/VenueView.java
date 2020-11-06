package ru.armd.pbk.views.nsi.venue;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * Представление места встречи.
 */
public class VenueView
	extends BaseGridView {

   private Long id;
   private String cod;
   private String name;
   private String description;
   private boolean isDelete;
   private String county;
   private String district;
   private String department;
   private boolean inPlan;

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

   public String getCounty() {
	  return county;
   }

   public void setCounty(String county) {
	  this.county = county;
   }

   public String getDistrict() {
	  return district;
   }

   public void setDistrict(String district) {
	  this.district = district;
   }

   public String getDepartment() {
	  return department;
   }

   public void setDepartment(String department) {
	  this.department = department;
   }

   public boolean isInPlan() {
	  return inPlan;
   }

   public void setInPlan(boolean inPlan) {
	  this.inPlan = inPlan;
   }
}