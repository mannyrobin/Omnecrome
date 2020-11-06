package ru.armd.pbk.views.nsi;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View модели ТС.
 */
public class TSModelView
	extends BaseGridView {

   private Long id;
   private String make;
   private String model;
   private String tsTypeName;
   private String tsCapacities;
   private Integer passengerCountMax;
   private Integer seatCount;
   private Integer length;
   private Integer width;
   private Integer height;
   private boolean isDelete;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
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

   public Integer getPassengerCountMax() {
	  return passengerCountMax;
   }

   public void setPassengerCountMax(Integer passengerCountMax) {
	  this.passengerCountMax = passengerCountMax;
   }

   public Integer getSeatCount() {
	  return seatCount;
   }

   public void setSeatCount(Integer seatCount) {
	  this.seatCount = seatCount;
   }

   public Integer getLength() {
	  return length;
   }

   public void setLength(Integer length) {
	  this.length = length;
   }

   public Integer getWidth() {
	  return width;
   }

   public void setWidth(Integer width) {
	  this.width = width;
   }

   public Integer getHeight() {
	  return height;
   }

   public void setHeight(Integer height) {
	  this.height = height;
   }

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean isDelete) {
	  this.isDelete = isDelete;
   }
}
