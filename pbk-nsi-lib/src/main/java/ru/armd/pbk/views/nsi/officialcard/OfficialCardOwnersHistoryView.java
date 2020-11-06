package ru.armd.pbk.views.nsi.officialcard;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSepratedDateTimeSerializer;

import java.util.Date;

/**
 * Представление для истории смены владельцев.
 */
public class OfficialCardOwnersHistoryView
	extends BaseGridView {
   private String surname;
   private String name;
   private String patronumic;
   private String pesonalNumber;
   @JsonSerialize(using = DotSepratedDateTimeSerializer.class)
   private Date updateDate;
   private String officialCardNumber;
   private String updateUser;
   private Boolean active;
   private String operation;

   public String getOperation() {
	  return operation;
   }

   public void setOperation(String operation) {
	  this.operation = operation;
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

   public String getPesonalNumber() {
	  return pesonalNumber;
   }

   public void setPesonalNumber(String pesonalNumber) {
	  this.pesonalNumber = pesonalNumber;
   }

   public Date getUpdateDate() {
	  return updateDate;
   }

   public void setUpdateDate(Date updateDate) {
	  this.updateDate = updateDate;
   }

   public String getOfficialCardNumber() {
	  return officialCardNumber;
   }

   public void setOfficialCardNumber(String officialCardNumber) {
	  this.officialCardNumber = officialCardNumber;
   }

   public String getUpdateUser() {
	  return updateUser;
   }

   public void setUpdateUser(String updateUser) {
	  this.updateUser = updateUser;
   }

   public Boolean getActive() {
	  return active;
   }

   public void setActive(Boolean active) {
	  this.active = active;
   }
}
