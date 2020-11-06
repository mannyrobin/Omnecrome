package ru.armd.pbk.views.nsi.contactlesscard;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * Представление для истории БСК.
 */
public class ContactlessCardHistoryView
	extends BaseVersionView {

   private String uid;
   private String cardNumber;
   private String description;

   public String getCardNumber() {
	  return cardNumber;
   }

   public void setCardNumber(String cardNumber) {
	  this.cardNumber = cardNumber;
   }

   public String getUid() {
	  return uid;
   }

   public void setUid(String uid) {
	  this.uid = uid;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }
}
