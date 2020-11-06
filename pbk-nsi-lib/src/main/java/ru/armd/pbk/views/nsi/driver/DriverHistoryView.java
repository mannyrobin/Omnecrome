package ru.armd.pbk.views.nsi.driver;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * View истории водителя.
 */
public class DriverHistoryView
	extends BaseVersionView {

   private String surname;
   private String name;
   private String patronumic;
   private String asduDriverId;
   private String asduDepotId;
   private String personalNumber;
   private Long parkId;

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

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }

   public Long getParkId() {
	  return parkId;
   }

   public void setParkId(Long parkId) {
	  this.parkId = parkId;
   }

}
