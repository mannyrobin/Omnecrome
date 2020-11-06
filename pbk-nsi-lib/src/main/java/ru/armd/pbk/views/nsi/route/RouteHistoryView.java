package ru.armd.pbk.views.nsi.route;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * Представлении истории маршрута.
 */
public class RouteHistoryView
	extends BaseVersionView {

   private String routeNumber;
   private int routeNumberInt;
   private String asduRouteId;
   private String askpRouteCode;
   private String asmppRouteCode;
   private Long routeId;
   private Long routeTsKindId;
   private Double profitRatio;
   private String easuFhdRouteCode;
   private Boolean isNight;

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

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public Long getRouteTsKindId() {
	  return routeTsKindId;
   }

   public void setRouteTsKindId(Long routeTsKindId) {
	  this.routeTsKindId = routeTsKindId;
   }

   public Double getProfitRatio() {
	  return profitRatio;
   }

   public void setProfitRatio(Double profitRatio) {
	  this.profitRatio = profitRatio;
   }

   public String getEasuFhdRouteCode() {
	  return easuFhdRouteCode;
   }

   public void setEasuFhdRouteCode(String easuFhdRouteCode) {
	  this.easuFhdRouteCode = easuFhdRouteCode;
   }

   public Boolean getIsNight() {
	  return isNight;
   }

   public void setIsNight(Boolean isNight) {
	  this.isNight = isNight;
   }

}
