package ru.armd.pbk.dto.nsi.district;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * ДТО маршрутов района.
 */
public class DistrictRouteDTO
	extends BaseDistrictVersionDTO {

   private Long routeId;

   @NotNull(message = "\"Маршрут\" должен быть выбран.")
   @Size(min = 1, message = "\"Маршрут\" должен быть выбран.")
   private List<Long> routeIds = new ArrayList<Long>();

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public List<Long> getRouteIds() {
	  return routeIds;
   }

   public void setRouteIds(List<Long> routeIds) {
	  this.routeIds = routeIds;
   }

}
