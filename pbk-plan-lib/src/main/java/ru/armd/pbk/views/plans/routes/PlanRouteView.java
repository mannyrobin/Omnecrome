package ru.armd.pbk.views.plans.routes;

import ru.armd.pbk.core.views.BaseGridView;

import java.util.Map;

/**
 * View маршрутов планов отдела.
 */
public class PlanRouteView
	extends BaseGridView {

   private Long id;
   private String routeNumber;
   private String routeType;
   private Map<String, RouteRatioView> routes;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public Map<String, RouteRatioView> getRoutes() {
	  return routes;
   }

   public void setRoutes(Map<String, RouteRatioView> routes) {
	  this.routes = routes;
   }

   public String getRouteType() {
	  return routeType;
   }

   public void setRouteType(String routeType) {
	  this.routeType = routeType;
   }

}
