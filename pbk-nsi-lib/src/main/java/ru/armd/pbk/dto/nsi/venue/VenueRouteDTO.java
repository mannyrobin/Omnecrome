package ru.armd.pbk.dto.nsi.venue;

import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.validation.IVenueRouteValidator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * ДТО "Сопутствующие маршруты".
 */
@IVenueRouteValidator
public class VenueRouteDTO
	extends BaseVersionDTO {

   @NotNull(message = "\"Место встречи\" должно быть выбрано.")
   private Long venueId;

   @NotNull(message = "\"Маршрут\" должен быть выбран.")
   @Size(min = 1, message = "\"Маршрут\" должен быть выбран.")
   private List<Long> routeIds = new ArrayList<Long>();

   private Long routeId;

   @NotNull(message = "\"Тип Маршрута\" должен быть выбран.")
   private Long venueRouteTypeId;

   private Long districtId;

   public Long getVenueId() {
	  return venueId;
   }

   public void setVenueId(Long venueId) {
	  this.venueId = venueId;
   }

   public Long getVenueRouteTypeId() {
	  return venueRouteTypeId;
   }

   public void setVenueRouteTypeId(Long venueRouteTypeId) {
	  this.venueRouteTypeId = venueRouteTypeId;
   }

   public List<Long> getRouteIds() {
	  return routeIds;
   }

   public void setRouteIds(List<Long> routeIds) {
	  this.routeIds = routeIds;
   }

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public Long getDistrictId() {
	  return districtId;
   }

   public void setDistrictId(Long districtId) {
	  this.districtId = districtId;
   }

}
