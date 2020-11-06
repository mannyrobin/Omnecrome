package ru.armd.pbk.views.nsi.park;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View парка.
 */
public class ParkView
	extends BaseGridView {

   private Long id;
   private String shortName;
   private String name;
   private Integer parkNumber;
   private String tsKind;
   private Long gmParkId;
   private String asduDepotId;
   private boolean isDelete;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

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

   public String getTsKind() {
	  return tsKind;
   }

   public void setTsKind(String tsKind) {
	  this.tsKind = tsKind;
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

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean isDelete) {
	  this.isDelete = isDelete;
   }
}
