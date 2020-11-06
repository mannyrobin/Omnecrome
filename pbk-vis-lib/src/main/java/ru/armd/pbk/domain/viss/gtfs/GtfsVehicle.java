package ru.armd.pbk.domain.viss.gtfs;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домент ТС.
 */
public class GtfsVehicle
	extends BaseDomain {

   private Date workDate;
   private Long vehicleId;
   private Integer depotId;
   private Integer depotNumber;
   private String routeType;
   private Integer isDeleted;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Long getVehicleId() {
	  return vehicleId;
   }

   public void setVehicleId(Long vehicleId) {
	  this.vehicleId = vehicleId;
   }

   public Integer getDepotId() {
	  return depotId;
   }

   public void setDepotId(Integer depotId) {
	  this.depotId = depotId;
   }

   public Integer getDepotNumber() {
	  return depotNumber;
   }

   public void setDepotNumber(Integer depotNumber) {
	  this.depotNumber = depotNumber;
   }

   public String getRouteType() {
	  return routeType;
   }

   public void setRouteType(String routeType) {
	  this.routeType = routeType;
   }

   public Integer getIsDeleted() {
	  return isDeleted;
   }

   public void setIsDeleted(Integer isDeleted) {
	  this.isDeleted = isDeleted;
   }

}
