package ru.armd.pbk.views.nsi.district;

import ru.armd.pbk.core.views.BaseVersionView;

/**
 * Представление маршрутов района.
 */
public class DistrictRouteView
	extends BaseVersionView {

   private String route;

   public String getRoute() {
	  return route;
   }

   public void setRoute(String route) {
	  this.route = route;
   }

}
