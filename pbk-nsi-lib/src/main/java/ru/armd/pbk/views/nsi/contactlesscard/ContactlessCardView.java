package ru.armd.pbk.views.nsi.contactlesscard;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View БСК.
 */
public class ContactlessCardView
	extends BaseGridView {

   private Long id;
   private boolean isDelete;
   private String uid;
   private String cardNumber;
   private String owner;
   private String dptName;

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

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean isDelete) {
	  this.isDelete = isDelete;
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

}