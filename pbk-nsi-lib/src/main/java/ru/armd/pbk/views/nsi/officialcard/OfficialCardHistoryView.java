package ru.armd.pbk.views.nsi.officialcard;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * Представление для истории ССК.
 */
public class OfficialCardHistoryView
	extends BaseVersionView {

   private String cardNumber;

   public String getCardNumber() {
	  return cardNumber;
   }

   public void setCardNumber(String cardNumber) {
	  this.cardNumber = cardNumber;
   }

}
