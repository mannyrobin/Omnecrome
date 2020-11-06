package ru.armd.pbk.domain.viss.gtfs;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен "Замена ТС".
 */
public class GtfsReplaceVehicle
	extends BaseDomain {

   private Date workDate;
   private Integer grafic;
   private Integer time;
   private Integer vehicleId1;
   private Integer vehicleId2;
   private Integer shiftNum;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getGrafic() {
	  return grafic;
   }

   public void setGrafic(Integer grafic) {
	  this.grafic = grafic;
   }

   public Integer getTime() {
	  return time;
   }

   public void setTime(Integer time) {
	  this.time = time;
   }

   public Integer getVehicleId1() {
	  return vehicleId1;
   }

   public void setVehicleId1(Integer vehicleId1) {
	  this.vehicleId1 = vehicleId1;
   }

   public Integer getVehicleId2() {
	  return vehicleId2;
   }

   public void setVehicleId2(Integer vehicleId2) {
	  this.vehicleId2 = vehicleId2;
   }

   public Integer getShiftNum() {
	  return shiftNum;
   }

   public void setShiftNum(Integer shiftNum) {
	  this.shiftNum = shiftNum;
   }

}
