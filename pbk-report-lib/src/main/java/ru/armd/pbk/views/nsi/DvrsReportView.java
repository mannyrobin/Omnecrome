package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Представление отчетов видеорегистраторов.
 */
public class DvrsReportView
	implements IReportView {

   private Long id;
   private String dvrNumber;
   private String dvrModel;
   private String owner;
   private String dptName;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getDvrNumber() {
	  return dvrNumber;
   }

   public void setDvrNumber(String dvrNumber) {
	  this.dvrNumber = dvrNumber;
   }

   public String getDvrModel() {
	  return dvrModel;
   }

   public void setDvrModel(String dvrModel) {
	  this.dvrModel = dvrModel;
   }

   public String getOwner() {
	  return owner;
   }

   public void setOwner(String owner) {
	  this.owner = owner;
   }

   public String getDptName() {
	  return dptName;
   }

   public void setDptName(String dptName) {
	  this.dptName = dptName;
   }

   @Override
   public List<Object> getReportList() {
	  List<Object> result = new ArrayList<Object>();
	  result.add(id);
	  result.add(dvrNumber);
	  result.add(dvrModel);
	  result.add(dptName);
	  result.add(owner);
	  return result;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("№", "Номер видеорегистратора", "Модель видеорегистратора", "Подразделение", "Владелец");
   }

}
