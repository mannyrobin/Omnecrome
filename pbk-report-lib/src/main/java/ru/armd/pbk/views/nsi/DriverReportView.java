package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Представление отчетов водителей.
 */
public class DriverReportView
	implements IReportView {

   private Long id;
   private String surname;
   private String name;
   private String patronumic;
   private String personalNumber;

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

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }

   @Override
   public List<Object> getReportList() {
	  StringBuilder bfio = new StringBuilder();
	  bfio.append(surname)
		  .append(' ')
		  .append(name)
		  .append(' ')
		  .append(patronumic);
	  List<Object> result = new ArrayList<Object>();
	  result.add(id);
	  result.add(bfio.toString());
	  result.add(personalNumber);
	  return result;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("№ п/п", "ФИО", "Табельный номер водителя");
   }

}
