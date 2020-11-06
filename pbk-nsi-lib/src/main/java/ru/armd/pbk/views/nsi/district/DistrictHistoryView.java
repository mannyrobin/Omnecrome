package ru.armd.pbk.views.nsi.district;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * Представлении истории Района.
 */
public class DistrictHistoryView
	extends BaseVersionView {

   private String name;
   private String cod;
   private Long gmRegionId;
   private String description;
   private Long countyId;

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

   public Long getGmRegionId() {
	  return gmRegionId;
   }

   public void setGmRegionId(Long gmRegionId) {
	  this.gmRegionId = gmRegionId;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Long getCountyId() {
	  return countyId;
   }

   public void setCountyId(Long countyId) {
	  this.countyId = countyId;
   }
}
