package ru.armd.pbk.views.nsi.venicle;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View ТС.
 */
public class VenicleView
	extends BaseGridView {

   private Long id;
   private String tsTypeName;
   private String tsCapacities;
   private String make;
   private String model;
   private String asduVenicleId;
   private String depoNumber;
   private String stateNumber;
   private String vinNumber;
   private boolean isDelete;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getTsTypeName() {
	  return tsTypeName;
   }

   public void setTsTypeName(String tsTypeName) {
	  this.tsTypeName = tsTypeName;
   }

   public String getTsCapacities() {
	  return tsCapacities;
   }

   public void setTsCapacities(String tsCapacities) {
	  this.tsCapacities = tsCapacities;
   }

   public String getMake() {
	  return make;
   }

   public void setMake(String make) {
	  this.make = make;
   }

   public String getModel() {
	  return model;
   }

   public void setModel(String model) {
	  this.model = model;
   }

   public String getAsduVenicleId() {
	  return asduVenicleId;
   }

   public void setAsduVenicleId(String asduVenicleId) {
	  this.asduVenicleId = asduVenicleId;
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

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean isDelete) {
	  this.isDelete = isDelete;
   }
}
