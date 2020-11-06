package ru.armd.pbk.views.nsi.park;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * View истории парка.
 */
public class ParkHistoryView
	extends BaseVersionView {

   private String shortName;
   private String name;
   private Integer parkNumber;
   private Long tsKindId;
   private Long gmParkId;
   private String asduDepotId;

   public String getShortName() {
	  return shortName;
   }

   public void setShortName(String shortName) {
	  this.shortName = shortName;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Integer getParkNumber() {
	  return parkNumber;
   }

   public void setParkNumber(Integer parkNumber) {
	  this.parkNumber = parkNumber;
   }

   public Long getTsKindId() {
	  return tsKindId;
   }

   public void setTsKindId(Long tsKindId) {
	  this.tsKindId = tsKindId;
   }

   public Long getGmParkId() {
	  return gmParkId;
   }

   public void setGmParkId(Long gmParkId) {
	  this.gmParkId = gmParkId;
   }

   public String getAsduDepotId() {
	  return asduDepotId;
   }

   public void setAsduDepotId(String asduDepotId) {
	  this.asduDepotId = asduDepotId;
   }
}
