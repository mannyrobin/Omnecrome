package ru.armd.pbk.domain.nsi.bso;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен БСО.
 */
public class Bso
	extends BaseDomain {

   private Long bsoNumberRuleId;
   private String bsoNumber;
   private Boolean isPrinted;
   private Boolean isTrashed;
   private Boolean isBound;
   private Boolean isUsed;

   public Long getBsoNumberRuleId() {
	  return bsoNumberRuleId;
   }

   public void setBsoNumberRuleId(Long bsoNumberRuleId) {
	  this.bsoNumberRuleId = bsoNumberRuleId;
   }

   public String getBsoNumber() {
	  return bsoNumber;
   }

   public void setBsoNumber(String bsoNumber) {
	  this.bsoNumber = bsoNumber;
   }

   public Boolean getIsPrinted() {
	  return isPrinted;
   }

   public void setIsPrinted(Boolean isPrinted) {
	  this.isPrinted = isPrinted;
   }

   public Boolean getIsTrashed() {
	  return isTrashed;
   }

   public void setIsTrashed(Boolean isTrashed) {
	  this.isTrashed = isTrashed;
   }

   public Boolean getIsBound() {
	  return isBound;
   }

   public void setIsBound(Boolean isBound) {
	  this.isBound = isBound;
   }

   public Boolean getIsUsed() {
	  return isUsed;
   }

   public void setIsUsed(Boolean isUsed) {
	  this.isUsed = isUsed;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("bsoNumberRuleId: ").append(StringUtils.nvl(getBsoNumberRuleId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("bsoNumber: ").append(StringUtils.nvl(getBsoNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("isPrinted: ").append(StringUtils.nvl(getIsPrinted(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("isTrashed: ").append(StringUtils.nvl(getIsTrashed(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("isBound: ").append(StringUtils.nvl(getIsUsed(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("isUsed: ").append(StringUtils.nvl(getIsUsed(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
