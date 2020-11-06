package ru.armd.pbk.views.nsi.driver;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View водителя.
 */
public class DriverView
	extends BaseGridView {

   private Long id;
   private String asduDriverId;
   private String asduDepotId;
   private String surname;
   private String name;
   private String patronumic;
   private String personalNumber;
   private boolean isDelete;
   private String parkName;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getAsduDriverId() {
	  return asduDriverId;
   }

   public void setAsduDriverId(String asduDriverId) {
	  this.asduDriverId = asduDriverId;
   }

   public String getAsduDepotId() {
	  return asduDepotId;
   }

   public void setAsduDepotId(String asduDepotId) {
	  this.asduDepotId = asduDepotId;
   }

   public String getSurname() {
	  return surname;
   }

   public void setSurname(String surname) {
	  this.surname = surname;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getPatronumic() {
	  return patronumic;
   }

   public void setPatronumic(String patronumic) {
	  this.patronumic = patronumic;
   }

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean isDelete) {
	  this.isDelete = isDelete;
   }

   public String getParkName() {
	  return parkName;
   }

   public void setParkName(String parkName) {
	  this.parkName = parkName;
   }

}
