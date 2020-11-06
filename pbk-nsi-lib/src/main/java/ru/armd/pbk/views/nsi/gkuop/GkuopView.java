package ru.armd.pbk.views.nsi.gkuop;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View ГКУ ОП.
 */
public class GkuopView
	extends BaseGridView {

   private Long id;
   private String gkuopDeptName;
   private String surname;
   private String name;
   private String patronumic;
   private String positionName;
   private String personalNumber;
   private String description;
   private String visGkuopId;
   private boolean isDelete;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

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

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean isDelete) {
	  this.isDelete = isDelete;
   }

   public String getVisGkuopId() {
	  return visGkuopId;
   }

   public void setVisGkuopId(String visGkuopId) {
	  this.visGkuopId = visGkuopId;
   }
}
