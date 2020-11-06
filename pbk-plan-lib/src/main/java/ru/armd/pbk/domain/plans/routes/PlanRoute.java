package ru.armd.pbk.domain.plans.routes;

import ru.armd.pbk.views.plans.routes.PlanRouteView;

import java.util.List;

/**
 * Домен маршрутов плана отдела.
 */
public class PlanRoute {

   private List<PlanRouteView> routes;
   private List<RouteRatio> routeRatios;

   public List<PlanRouteView> getRoutes() {
	  return routes;
   }

   public void setRoutes(List<PlanRouteView> routes) {
	  this.routes = routes;
   }

   public List<RouteRatio> getRouteRatios() {
	  return routeRatios;
   }

   public void setRouteRatios(List<RouteRatio> routeRatios) {
	  this.routeRatios = routeRatios;
   }

}
