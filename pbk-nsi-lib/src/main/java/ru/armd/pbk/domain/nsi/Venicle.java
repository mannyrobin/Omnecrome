package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен TC.
 */
public class Venicle
	extends BaseVersionDomain {

   private String asduVenicleId;
   private Long tsModelId;
   private String depoNumber;
   private String stateNumber;
   private String vinNumber;
   private String asduDepotId;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("asduVenicleId: ").append(StringUtils.nvl(getAsduVenicleId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("tsModelId: ").append(StringUtils.nvl(getTsModelId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("depoNumber: ").append(StringUtils.nvl(getDepoNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stateNumber: ").append(StringUtils.nvl(getStateNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("vinNumber: ").append(StringUtils.nvl(getVinNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("asduDepotId: ").append(StringUtils.nvl(getAsduDepotId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public String getAsduVenicleId() {
	  return asduVenicleId;
   }

   public void setAsduVenicleId(String asduVenicleId) {
	  this.asduVenicleId = asduVenicleId;
   }

   public Long getTsModelId() {
	  return tsModelId;
   }

   public void setTsModelId(Long tsModelId) {
	  this.tsModelId = tsModelId;
   }

   public String getDepoNumber() {
	  return depoNumber;
   }

   public void setDepoNumber(String depoNumber) {
	  this.depoNumber = depoNumber;
   }

   public String getStateNumber() {
	  return stateNumber;
   }

   public void setStateNumber(String stateNumber) {
	  this.stateNumber = stateNumber;
   }

   public String getVinNumber() {
	  return vinNumber;
   }

   public void setVinNumber(String vinNumber) {
	  this.vinNumber = vinNumber;
   }

   public String getAsduDepotId() {
	  return asduDepotId;
   }

   public void setAsduDepotId(String asduDepotId) {
	  this.asduDepotId = asduDepotId;
   }

}
