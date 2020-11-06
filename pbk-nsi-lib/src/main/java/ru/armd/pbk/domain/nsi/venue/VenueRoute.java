package ru.armd.pbk.domain.nsi.venue;

import ru.armd.pbk.core.domain.BaseVersionDomain;

/**
 * Домен "Сопутствующие маршруты".
 */
public class VenueRoute
	extends BaseVersionDomain {

   private Long venueId;
   private Long routeId;
   private Long venueRouteTypeId;
   private Long districtId;

   public Long getVenueId() {
	  return venueId;
   }

   public void setVenueId(Long venueId) {
	  this.venueId = venueId;
   }

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public Long getVenueRouteTypeId() {
	  return venueRouteTypeId;
   }

   public void setVenueRouteTypeId(Long venueRouteTypeId) {
	  this.venueRouteTypeId = venueRouteTypeId;
   }

   public Long getDistrictId() {
	  return districtId;
   }

   public void setDistrictId(Long districtId) {
	  this.districtId = districtId;
   }

}
