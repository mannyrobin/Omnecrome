package ru.armd.pbk.domain.plans.routes;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен рейтинга маршрута.
 */
public class RouteRatio
	extends BaseDomain {

   private Date workDate;
   private Long routeId;
   private Integer routeRatio;
   private String descr;
   private Double profitRatio;
   private boolean manual;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public Integer getRouteRatio() {
	  return routeRatio;
   }

   public void setRouteRatio(Integer routeRatio) {
	  this.routeRatio = routeRatio;
   }

   public String getDescr() {
	  return descr;
   }

   public void setDescr(String descr) {
	  this.descr = descr;
   }

   public Double getProfitRatio() {
	  return profitRatio;
   }

   public void setProfitRatio(Double profitRatio) {
	  this.profitRatio = profitRatio;
   }

   public boolean isManual() {
	  return manual;
   }

   public void setManual(boolean manual) {
	  this.manual = manual;
   }
}