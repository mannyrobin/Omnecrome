package ru.armd.pbk.views.nsi.gkuop;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * View истории ГКУ ОП.
 */
public class GkuopHistoryView
	extends BaseVersionView {

   private String gkuopDeptName;
   private String surname;
   private String name;
   private String patronumic;
   private String positionName;
   private String personalNumber;
   private String visGkuopId;
   private String description;

   public String getGkuopDeptName() {
	  return gkuopDeptName;
   }

   public void setGkuopDeptName(String gkuopDeptName) {
	  this.gkuopDeptName = gkuopDeptName;
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

   public String getPositionName() {
	  return positionName;
   }

   public void setPositionName(String positionName) {
	  this.positionName = positionName;
   }

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public String getVisGkuopId() {
	  return visGkuopId;
   }

   public void setVisGkuopId(String visGkuopId) {
	  this.visGkuopId = visGkuopId;
   }
}
