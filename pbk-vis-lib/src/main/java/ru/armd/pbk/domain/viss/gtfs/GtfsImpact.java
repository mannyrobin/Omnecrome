package ru.armd.pbk.domain.viss.gtfs;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен "Управляющие воздействия".
 */
public class GtfsImpact
	extends BaseDomain {

   private Date workDate;
   private Integer depotId;
   private Integer impactId;
   private Date impactTime;
   private Integer vehicleId;
   private Integer tripId;
   private Integer grafic;
   private Integer routeNum;
   private Integer shiftNum;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getDepotId() {
	  return depotId;
   }

   public void setDepotId(Integer depotId) {
	  this.depotId = depotId;
   }

   public Integer getImpactId() {
	  return impactId;
   }

   public void setImpactId(Integer impactId) {
	  this.impactId = impactId;
   }

   public Date getImpactTime() {
	  return impactTime;
   }

   public void setImpactTime(Date impactTime) {
	  this.impactTime = impactTime;
   }

   public Integer getVehicleId() {
	  return vehicleId;
   }

   public void setVehicleId(Integer vehicleId) {
	  this.vehicleId = vehicleId;
   }

   public Integer getTripId() {
	  return tripId;
   }

   public void setTripId(Integer tripId) {
	  this.tripId = tripId;
   }

   public Integer getGrafic() {
	  return grafic;
   }

   public void setGrafic(Integer grafic) {
	  this.grafic = grafic;
   }

   public Integer getRouteNum() {
	  return routeNum;
   }

   public void setRouteNum(Integer routeNum) {
	  this.routeNum = routeNum;
   }

   public Integer getShiftNum() {
	  return shiftNum;
   }

   public void setShiftNum(Integer shiftNum) {
	  this.shiftNum = shiftNum;
   }

}
