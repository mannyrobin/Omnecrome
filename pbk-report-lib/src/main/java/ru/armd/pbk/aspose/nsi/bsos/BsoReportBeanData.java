package ru.armd.pbk.aspose.nsi.bsos;

import ru.armd.pbk.aspose.core.IReportDataBean;

/**
 * Данные для размещения на печатной форме акта об изъятии.
 */
public class BsoReportBeanData
	implements IReportDataBean {

   private String actNumber;
   private String name;
   private String patronumic;
   private String surname;
   private String personalNumber;
   private Integer year;

   public String getActNumber() {
	  return actNumber;
   }

   public void setActNumber(String actNumber) {
	  this.actNumber = actNumber;
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

   public String getSurname() {
	  return surname;
   }

   public void setSurname(String surname) {
	  this.surname = surname;
   }

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }

   public Integer getYear() {
	  return year;
   }

   public void setYear(Integer year) {
	  this.year = year;
   }

}
