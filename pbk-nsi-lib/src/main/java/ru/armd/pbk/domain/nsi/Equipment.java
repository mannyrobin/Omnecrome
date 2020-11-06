package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен бортового оборудования.
 */
public class Equipment
	extends BaseVersionDomain {

   private String asduEquipmentId;
   private String asduVenicleId;
   private String brand;
   private String model;
   private String firmwareVersion;
   private String cellNumber;
   private Long venicleId;

   public String getAsduEquipmentId() {
	  return asduEquipmentId;
   }

   public void setAsduEquipmentId(String asduEquipmentId) {
	  this.asduEquipmentId = asduEquipmentId;
   }

   public String getAsduVenicleId() {
	  return asduVenicleId;
   }

   public void setAsduVenicleId(String asduVenicleId) {
	  this.asduVenicleId = asduVenicleId;
   }

   public String getBrand() {
	  return brand;
   }

   public void setBrand(String brand) {
	  this.brand = brand;
   }

   public String getModel() {
	  return model;
   }

   public void setModel(String model) {
	  this.model = model;
   }

   public String getFirmwareVersion() {
	  return firmwareVersion;
   }

   public void setFirmwareVersion(String firmwareVersion) {
	  this.firmwareVersion = firmwareVersion;
   }

   public String getCellNumber() {
	  return cellNumber;
   }

   public void setCellNumber(String cellNumber) {
	  this.cellNumber = cellNumber;
   }

   public Long getVenicleId() {
	  return venicleId;
   }

   public void setVenicleId(Long venicleId) {
	  this.venicleId = venicleId;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("asduEquipmentId: ").append(StringUtils.nvl(getAsduEquipmentId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("asduVenicleId: ").append(StringUtils.nvl(getAsduVenicleId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("brand: ").append(StringUtils.nvl(getBrand(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("model: ").append(StringUtils.nvl(getModel(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("firmwareVersion: ").append(StringUtils.nvl(getFirmwareVersion(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("cellNumber: ").append(StringUtils.nvl(getCellNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("venicleId: ").append(StringUtils.nvl(getVenicleId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
