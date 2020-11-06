package ru.armd.pbk.views.nsi.county;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * Представлении истории Округа.
 */
public class CountyHistoryView
	extends BaseVersionView {

   private String name;

   private String cod;
   private Long gmDistrictId;
   private String description;
   private Long departmentId;
   private String shortName;

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getCod() {
	  return cod;
   }

   public void setCod(String cod) {
	  this.cod = cod;
   }

   public Long getGmDistrictId() {
	  return gmDistrictId;
   }

   public void setGmDistrictId(Long gmDistrictId) {
	  this.gmDistrictId = gmDistrictId;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Long getDepartmentId() {
	  return departmentId;
   }

   public void setDepartmentId(Long departmentId) {
	  this.departmentId = departmentId;
   }

   public String getShortName() {
	  return shortName;
   }

   public void setShortName(String shortName) {
	  this.shortName = shortName;
   }
}
