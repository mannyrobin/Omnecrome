package ru.armd.pbk.views.nsi.employee;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import java.util.Date;

/**
 * View сотрудника.
 */
public class EmployeeView
	extends BaseGridView {

   private Long id;

   private String name;

   private String patronumic;

   private String surname;

   private String departmentName;

   private String personalNumber;

   private String contCardId;

   private String puskId;

   private String offCardId;

   private String emplPost;

   private String login;

   private boolean isDelete;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date fireDate;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getDepartmentName() {
	  return departmentName;
   }

   public void setDepartmentName(String departmentName) {
	  this.departmentName = departmentName;
   }

   public String getContCardId() {
	  return contCardId;
   }

   public void setContCardId(String contCardId) {
	  this.contCardId = contCardId;
   }

   public String getPuskId() {
	  return puskId;
   }

   public void setPuskId(String puskId) {
	  this.puskId = puskId;
   }

   public String getOffCardId() {
	  return offCardId;
   }

   public void setOffCardId(String offCardId) {
	  this.offCardId = offCardId;
   }

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }

   public String getEmplPost() {
	  return emplPost;
   }

   public void setEmplPost(String emplPost) {
	  this.emplPost = emplPost;
   }

   public String getLogin() {
	  return login;
   }

   public void setLogin(String login) {
	  this.login = login;
   }

   public String getPatronumic() {
	  return patronumic;
   }

   public void setPatronumic(String patronumic) {
	  this.patronumic = patronumic;
   }

   public String getSurname() {
	  return surname;
   }

   public void setSurname(String surname) {
	  this.surname = surname;
   }

   public Date getFireDate() {
	  return fireDate;
   }

   public void setFireDate(Date fireDate) {
	  this.fireDate = fireDate;
   }

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean isDelete) {
	  this.isDelete = isDelete;
   }
}
