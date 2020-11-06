package ru.armd.pbk.views.unionanalysis;

import ru.armd.pbk.core.views.BaseGridView;

public class UnionAnalysisByMoveRouteView
	extends BaseGridView {

   private Integer asduRouteId;
   private String routeName;
   private Integer grafic;
   private Integer asmppPassengersIncludedCount;
   private Integer asmppPassengersLeftCount;
   private Integer asmppPassengersTransportedCount;
   private Integer askpPassengersCount;
   private String routeFilterName;

   public Integer getAsduRouteId() {
	  return asduRouteId;
   }

   public void setAsduRouteId(Integer asduRouteId) {
	  this.asduRouteId = asduRouteId;
   }

   public String getRouteName() {
	  return routeName;
   }

   public void setRouteName(String routeName) {
	  this.routeName = routeName;
   }

   public Integer getGrafic() {
	  return grafic;
   }

   public void setGrafic(Integer grafic) {
	  this.grafic = grafic;
   }

   public Integer getAsmppPassengersIncludedCount() {
	  return asmppPassengersIncludedCount;
   }

   public void setAsmppPassengersIncludedCount(Integer asmppPassengersIncludedCount) {
	  this.asmppPassengersIncludedCount = asmppPassengersIncludedCount;
   }

   public Integer getAsmppPassengersLeftCount() {
	  return asmppPassengersLeftCount;
   }

   public void setAsmppPassengersLeftCount(Integer asmppPassengersLeftCount) {
	  this.asmppPassengersLeftCount = asmppPassengersLeftCount;
   }

   public Integer getAsmppPassengersTransportedCount() {
	  return asmppPassengersTransportedCount;
   }

   public void setAsmppPassengersTransportedCount(Integer asmppPassengersTransportedCount) {
	  this.asmppPassengersTransportedCount = asmppPassengersTransportedCount;
   }

   public Integer getAskpPassengersCount() {
	  return askpPassengersCount;
   }

   public void setAskpPassengersCount(Integer askpPassengersCount) {
	  this.askpPassengersCount = askpPassengersCount;
   }

   public String getRouteFilterName() {
	  return routeFilterName;
   }

   public void setRouteFilterName(String routeFilterName) {
	  this.routeFilterName = routeFilterName;
   }

}
