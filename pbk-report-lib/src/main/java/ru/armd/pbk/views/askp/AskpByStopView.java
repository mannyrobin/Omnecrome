package ru.armd.pbk.views.askp;

import ru.armd.pbk.core.views.BaseGridView;

import java.util.List;

public class AskpByStopView
	extends BaseGridView {

   private Long stopId;
   private String stopName;
   private List<AskpByRouteView> routes;

   public Long getStopId() {
	  return stopId;
   }

   public void setStopId(Long stopId) {
	  this.stopId = stopId;
   }

   public String getStopName() {
	  return stopName;
   }

   public void setStopName(String stopName) {
	  this.stopName = stopName;
   }

   public List<AskpByRouteView> getRoutes() {
	  return routes;
   }

   public void setRoutes(List<AskpByRouteView> routes) {
	  this.routes = routes;
   }

}
