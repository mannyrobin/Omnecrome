package ru.armd.pbk.domain.viss.gtfs;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен изменения бортового оборудования на ТС.
 */
public class GtfsChangeEquipment
	extends BaseDomain {

   private Date workDate;
   private Integer equipmentId;
   private Integer identificator;
   private Integer vehicleId;
   private Date timeChange;

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

   public Integer getVehicleId() {
	  return vehicleId;
   }

   public void setVehicleId(Integer vehicleId) {
	  this.vehicleId = vehicleId;
   }

   public Date getTimeChange() {
	  return timeChange;
   }

   public void setTimeChange(Date timeChange) {
	  this.timeChange = timeChange;
   }

}
