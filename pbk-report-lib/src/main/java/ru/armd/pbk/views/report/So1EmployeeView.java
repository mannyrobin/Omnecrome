package ru.armd.pbk.views.report;

/**
 * View для контролёра (для использования в отчёте "График работы контролеров по территориальному подразделению").
 */
public class So1EmployeeView
	extends SoView {

   private String name;
   private String surname;
   private String patronumic;
   private String personalNumber;
   private String departmentName;

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getSurname() {
	  return surname;
   }

   public void setSurname(String surname) {
	  this.surname = surname;
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

   public String getDepartmentName() {
	  return departmentName;
   }

   public void setDepartmentName(String departmentName) {
	  this.departmentName = departmentName;
   }

}
