package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен БСК.
 */
public class ContactlessCard
	extends BaseVersionDomain {

   private String uid;
   private String cardNumber;
   private String description;

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

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


   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("uid: ").append(StringUtils.nvl(getUid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("cardNumber: ").append(StringUtils.nvl(getCardNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(getDescription(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
