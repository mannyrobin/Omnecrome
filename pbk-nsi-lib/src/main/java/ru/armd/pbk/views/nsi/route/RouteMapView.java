package ru.armd.pbk.views.nsi.route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Представление для отображение маршрута на карте.
 */
public class RouteMapView {

   private String name;
   private Long id;
   private List<RouteTrajectory> routeTrajectories;
   private Map<Long, List<RouteStop>> routeStops = new HashMap<>();

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public List<RouteTrajectory> getRouteTrajectories() {
	  return routeTrajectories;
   }

   public void setRouteTrajectories(List<RouteTrajectory> routeTrajectories) {
	  this.routeTrajectories = routeTrajectories;
   }

   public Map<Long, List<RouteStop>> getRouteStops() {
	  return routeStops;
   }

   public void setRouteStops(Map<Long, List<RouteStop>> routeStops) {
	  this.routeStops = routeStops;
   }

}
