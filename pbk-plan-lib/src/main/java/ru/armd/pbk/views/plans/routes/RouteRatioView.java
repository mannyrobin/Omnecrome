package ru.armd.pbk.views.plans.routes;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.HyphenSepratedDateSerializer;

import java.util.Date;

/**
 * View рейтинга маршрута плана отдела.
 */
public class RouteRatioView {

   private Long id;
   private Integer routeRatio;
   @JsonSerialize(using = HyphenSepratedDateSerializer.class)
   private Date workDate;
   private boolean manual;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Integer getRouteRatio() {
	  return routeRatio;
   }

   public void setRouteRatio(Integer routeRatio) {
	  this.routeRatio = routeRatio;
   }

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public boolean isManual() {
	  return manual;
   }

   public void setManual(boolean manual) {
	  this.manual = manual;
   }
}