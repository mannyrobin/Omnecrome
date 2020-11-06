package ru.armd.pbk.views.nsi.equipment;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View бортового оборудования.
 */
public class EquipmentView
	extends BaseGridView {

   private Long id;
   private String asduEquipmentId;
   private String asduVenicleId;
   private String brand;
   private String model;
   private String firmwareVersion;
   private String cellNumber;
   private boolean isDelete;
   private String tsView;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
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

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean isDelete) {
	  this.isDelete = isDelete;
   }

   public String getTsView() {
	  return tsView;
   }

   public void setTsView(String tsView) {
	  this.tsView = tsView;
   }
}
