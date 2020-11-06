package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

public class StopIntervalStat
	extends BaseDomain {

   private Date workDate;
   private Integer asduRouteId;
   private Integer grafic;
   private String asduRouteName;
   private Integer depotId;
   private String depotName;
   private Integer depotNumber;
   private Integer hasIntervals;
   private String asduRouteType;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getAsduRouteId() {
	  return asduRouteId;
   }

   public void setAsduRouteId(Integer asduRouteId) {
	  this.asduRouteId = asduRouteId;
   }

   public Integer getGrafic() {
	  return grafic;
   }

   public void setGrafic(Integer grafic) {
	  this.grafic = grafic;
   }

   public String getAsduRouteName() {
	  return asduRouteName;
   }

   public void setAsduRouteName(String asduRouteName) {
	  this.asduRouteName = asduRouteName;
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

   public Integer getDepotNumber() {
	  return depotNumber;
   }

   public void setDepotNumber(Integer depotNumber) {
	  this.depotNumber = depotNumber;
   }

   public Integer getHasIntervals() {
	  return hasIntervals;
   }


   public void setHasIntervals(Integer hasIntervals) {
	  this.hasIntervals = hasIntervals;
   }

   public String getAsduRouteType() {
	  return asduRouteType;
   }

   public void setAsduRouteType(String asduRouteType) {
	  this.asduRouteType = asduRouteType;
   }

}
