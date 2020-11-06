package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Представление отчетов ПУсК.
 */
public class PuskReportView
	implements IReportView {

   private Long id;
   private String puskNumber;
   private String puskModel;
   private String description;
   private String owner;
   private String dptName;

   public Long getId() {
	  return id;
   }

   public String getPuskModel() {
	  return puskModel;
   }

   public void setPuskModel(String puskModel) {
	  this.puskModel = puskModel;
   }

   public String getPuskNumber() {
	  return puskNumber;
   }

   public void setPuskNumber(String puskNumber) {
	  this.puskNumber = puskNumber;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
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
	  result.add(puskNumber);
	  result.add(puskModel);
	  result.add(description);
	  result.add(dptName);
	  result.add(owner);
	  return result;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("№ п/п", "Номер ПУсК", "Модель ПУсК", "Описание", "Подразделение", "Владелец");
   }

}
