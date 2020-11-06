package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Представление отчетов СКК.
 */
public class OfficialCardReportView
	implements IReportView {

   private Long id;
   private String cardNumber;
   private String description;
   private String owner;
   private String dptName;

   public String getCardNumber() {
	  return cardNumber;
   }

   public void setCardNumber(String cardNumber) {
	  this.cardNumber = cardNumber;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getDescription() {
	  return description;
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
	  result.add(cardNumber);
	  result.add(description);
	  result.add(dptName);
	  result.add(owner);
	  return result;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("№", "Номер карты", "Описание", "Подразделение", "Владелец");
   }

}
