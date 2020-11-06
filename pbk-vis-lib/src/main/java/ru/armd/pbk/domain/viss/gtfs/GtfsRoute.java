package ru.armd.pbk.domain.viss.gtfs;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домер маршрута.
 */
public class GtfsRoute
	extends BaseDomain {

   private Date workDate;
   private Integer routeId;
   private Integer agencyId;
   private String routeShortName;
   private String routeLongName;
   private String routeDesc;
   private String routeView;
   private String routeType;

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

   public Integer getAgencyId() {
	  return agencyId;
   }

   public void setAgencyId(Integer agencyId) {
	  this.agencyId = agencyId;
   }

   public String getRouteShortName() {
	  return routeShortName;
   }

   public void setRouteShortName(String routeShortName) {
	  this.routeShortName = routeShortName;
   }

   public String getRouteLongName() {
	  return routeLongName;
   }

   public void setRouteLongName(String routeLongName) {
	  this.routeLongName = routeLongName;
   }

   public String getRouteDesc() {
	  return routeDesc;
   }

   public void setRouteDesc(String routeDesc) {
	  this.routeDesc = routeDesc;
   }

   public String getRouteView() {
	  return routeView;
   }

   public void setRouteView(String routeView) {
	  this.routeView = routeView;
   }

   public String getRouteType() {
	  return routeType;
   }

   public void setRouteType(String routeType) {
	  this.routeType = routeType;
   }

}
