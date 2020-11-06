package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.IReportView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Представление отчетов БСК.
 */
public class ContactlessCardReportView
	implements IReportView {

   private Long id;
   private String uid;
   private String cardNumber;
   private String description;
   private String owner;
   private String dptName;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getUid() {
	  return uid;
   }

   public void setUid(String uid) {
	  this.uid = uid;
   }

   public String getCardNumber() {
	  return cardNumber;
   }

   public void setCardNumber(String cardNumber) {
	  this.cardNumber = cardNumber;
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
	  result.add(uid);
	  result.add(cardNumber);
	  result.add(description);
	  result.add(dptName);
	  result.add(owner);
	  return result;
   }

   @Override
   public List<String> getReportHeaders() {
	  return Arrays.asList("№", "UID", "Номер карты", "Описание", "Подразделение", "Владелец");
   }

}
