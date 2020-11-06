package ru.armd.pbk.views.nsi.venue;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * Представление "Сопутствующие маршруты".
 */
public class VenueRouteView
	extends BaseVersionView {

   private String route;
   private String venueRouteType;
   private Long routeId;

   public String getRoute() {
	  return route;
   }

   public void setRoute(String route) {
	  this.route = route;
   }

   public String getVenueRouteType() {
	  return venueRouteType;
   }

   public void setVenueRouteType(String venueRouteType) {
	  this.venueRouteType = venueRouteType;
   }

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

}
