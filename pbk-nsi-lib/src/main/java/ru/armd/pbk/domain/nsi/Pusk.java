package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

public class Pusk
	extends BaseVersionDomain {

   private String puskNumber;
   private String puskModel;
   private String description;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("puskNumber: ").append(StringUtils.nvl(getPuskNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("puskModel: ").append(StringUtils.nvl(getPuskModel(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(getDescription(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
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

}
