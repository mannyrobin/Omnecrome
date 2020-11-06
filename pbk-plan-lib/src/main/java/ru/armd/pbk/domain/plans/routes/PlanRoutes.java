package ru.armd.pbk.domain.plans.routes;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен рейтингов маршрута.
 */
public class PlanRoutes
	extends BaseDomain {
   private Date workDate;
   private Long routeId;
   private Integer ratio;

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

   public Integer getRatio() {
	  return ratio;
   }

   public void setRatio(Integer ratio) {
	  this.ratio = ratio;
   }
}