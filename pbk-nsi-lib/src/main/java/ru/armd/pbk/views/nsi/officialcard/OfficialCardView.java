package ru.armd.pbk.views.nsi.officialcard;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View ССК.
 */
public class OfficialCardView
	extends BaseGridView {

   private Long id;
   private boolean isDelete;
   private String cardNumber;
   private String owner;
   private String dptName;

   public String getDptName() {
	  return dptName;
   }

   public void setDptName(String dptName) {
	  this.dptName = dptName;
   }

   public String getCardNumber() {
	  return cardNumber;
   }

   public void setCardNumber(String cardNumber) {
	  this.cardNumber = cardNumber;
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
}
