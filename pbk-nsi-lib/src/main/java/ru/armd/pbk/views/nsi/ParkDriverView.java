package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * Представление водителя парка.
 */
public class ParkDriverView
	extends BaseVersionView {

   private String surname;
   private String name;
   private String patronumic;
   private String personalNumber;
   private Long parkId;
   private Long tsDriverId;

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

   public Long getParkId() {
	  return parkId;
   }

   public void setParkId(Long parkId) {
	  this.parkId = parkId;
   }

   public Long getTsDriverId() {
	  return tsDriverId;
   }

   public void setTsDriverId(Long tsDriverId) {
	  this.tsDriverId = tsDriverId;
   }

}
