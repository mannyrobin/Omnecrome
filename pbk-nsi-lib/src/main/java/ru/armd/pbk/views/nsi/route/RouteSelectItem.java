package ru.armd.pbk.views.nsi.route;

import ru.armd.pbk.core.views.SelectItem;

public class RouteSelectItem
	extends SelectItem {
   private Long countyId;
   private int entranceCount;
   private String countyName;

   public Long getCountyId() {
	  return countyId;
   }

   public void setCountyId(Long countyId) {
	  this.countyId = countyId;
   }

   public int getEntranceCount() {
	  return entranceCount;
   }

   public void setEntranceCount(int entranceCount) {
	  this.entranceCount = entranceCount;
   }

   public String getCountyName() {
	  return countyName;
   }

   public void setCountyName(String countyName) {
	  this.countyName = countyName;
   }
}