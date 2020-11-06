package ru.armd.pbk.domain.viss.gtfs;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен "автотранспортных предприятий".
 */
public class GtfsDepot
	extends BaseDomain {

   private Date workDate;
   private Integer agencyId;
   private Integer depotId;
   private String depotName;
   private Integer isDeleted;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getAgencyId() {
	  return agencyId;
   }

   public void setAgencyId(Integer agencyId) {
	  this.agencyId = agencyId;
   }

   public Integer getDepotId() {
	  return depotId;
   }

   public void setDepotId(Integer depotId) {
	  this.depotId = depotId;
   }

   public String getDepotName() {
	  return depotName;
   }

   public void setDepotName(String depotName) {
	  this.depotName = depotName;
   }

   public Integer getIsDeleted() {
	  return isDeleted;
   }

   public void setIsDeleted(Integer isDeleted) {
	  this.isDeleted = isDeleted;
   }

}
