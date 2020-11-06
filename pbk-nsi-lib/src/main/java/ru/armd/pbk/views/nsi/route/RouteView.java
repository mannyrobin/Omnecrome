package ru.armd.pbk.views.nsi.route;

import ru.armd.pbk.core.views.BaseGridView;

/**
 * View маршрута.
 */
public class RouteView
	extends BaseGridView {

   private String routeNumber;
   private int routeNumberInt;
   private String asduRouteId;
   private String askpRouteCode;
   private String asmppRouteCode;
   private String route;
   private String routeTsKind;
   private String easuFhdRouteCode;
   private Long id;
   private boolean isDelete;

   private String district;
   private String cities;

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public int getRouteNumberInt() {
	  return routeNumberInt;
   }

   public void setRouteNumberInt(int routeNumberInt) {
	  this.routeNumberInt = routeNumberInt;
   }

   public String getAsduRouteId() {
	  return asduRouteId;
   }

   public void setAsduRouteId(String asduRouteId) {
	  this.asduRouteId = asduRouteId;
   }

   public String getAskpRouteCode() {
	  return askpRouteCode;
   }

   public void setAskpRouteCode(String askpRouteCode) {
	  this.askpRouteCode = askpRouteCode;
   }

   public String getAsmppRouteCode() {
	  return asmppRouteCode;
   }

   public void setAsmppRouteCode(String asmppRouteCode) {
	  this.asmppRouteCode = asmppRouteCode;
   }

   public String getRoute() {
	  return route;
   }

   public void setRoute(String route) {
	  this.route = route;
   }

   public String getRouteTsKind() {
	  return routeTsKind;
   }

   public void setRouteTsKind(String routeTsKind) {
	  this.routeTsKind = routeTsKind;
   }

   public String getEasuFhdRouteCode() {
	  return easuFhdRouteCode;
   }

   public void setEasuFhdRouteCode(String easuFhdRouteCode) {
	  this.easuFhdRouteCode = easuFhdRouteCode;
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public boolean isDelete() {
	  return isDelete;
   }

   public void setDelete(boolean isDelete) {
	  this.isDelete = isDelete;
   }

   public String getDistrict() {
	  return district;
   }

   public void setDistrict(String district) {
	  this.district = district;
   }

   public String getCities() {
	  return cities;
   }

   public void setCities(String cities) {
	  this.cities = cities;
   }

}
