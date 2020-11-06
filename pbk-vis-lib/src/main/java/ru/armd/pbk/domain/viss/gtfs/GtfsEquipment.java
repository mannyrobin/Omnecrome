package ru.armd.pbk.domain.viss.gtfs;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен бортового оборудования.
 */
public class GtfsEquipment
	extends BaseDomain {

   private Date workDate;
   private Integer equipmentId;
   private Integer identificator;
   private Integer isDeleted;
   private Integer vehicleId;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getEquipmentId() {
	  return equipmentId;
   }

   public void setEquipmentId(Integer equipmentId) {
	  this.equipmentId = equipmentId;
   }

   public Integer getIdentificator() {
	  return identificator;
   }

   public void setIdentificator(Integer identificator) {
	  this.identificator = identificator;
   }

   public Integer getIsDeleted() {
	  return isDeleted;
   }

   public void setIsDeleted(Integer isDeleted) {
	  this.isDeleted = isDeleted;
   }

   public Integer getVehicleId() {
	  return vehicleId;
   }

   public void setVehicleId(Integer vehicleId) {
	  this.vehicleId = vehicleId;
   }

}
