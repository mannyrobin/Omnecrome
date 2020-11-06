package ru.armd.pbk.domain.viss.gtfs;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен "Справочник назначений ТС на маршрут".
 */
public class GtfsVehicleTask
	extends BaseDomain {

   private Date workDate;
   private Integer routeId;
   private Integer vehicleId;
   private Integer grafic;
   private Integer shiftNum;
   private Integer startTime;
   private Integer endTime;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getRouteId() {
	  return routeId;
   }

   public void setRouteId(Integer routeId) {
	  this.routeId = routeId;
   }

   public Integer getVehicleId() {
	  return vehicleId;
   }

   public void setVehicleId(Integer vehicleId) {
	  this.vehicleId = vehicleId;
   }

   public Integer getGrafic() {
	  return grafic;
   }

   public void setGrafic(Integer grafic) {
	  this.grafic = grafic;
   }

   public Integer getShiftNum() {
	  return shiftNum;
   }

   public void setShiftNum(Integer shiftNum) {
	  this.shiftNum = shiftNum;
   }

   public Integer getStartTime() {
	  return startTime;
   }

   public void setStartTime(Integer startTime) {
	  this.startTime = startTime;
   }

   public Integer getEndTime() {
	  return endTime;
   }

   public void setEndTime(Integer endTime) {
	  this.endTime = endTime;
   }

}
