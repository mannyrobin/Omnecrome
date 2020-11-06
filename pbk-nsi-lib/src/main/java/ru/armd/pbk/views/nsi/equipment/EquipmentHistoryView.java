package ru.armd.pbk.views.nsi.equipment;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * View истории бортового оборудования.
 */
public class EquipmentHistoryView
	extends BaseVersionView {

   private String brand;
   private String model;
   private Long venicleId;
   private String firmwareVersion;
   private String cellNumber;
   private String asduEquipmentId;
   private String asduVenicleId;

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

   public Long getVenicleId() {
	  return venicleId;
   }

   public void setVenicleId(Long venicleId) {
	  this.venicleId = venicleId;
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
}
