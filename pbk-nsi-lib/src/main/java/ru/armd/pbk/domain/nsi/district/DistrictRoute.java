package ru.armd.pbk.domain.nsi.district;

import ru.armd.pbk.utils.StringUtils;

/**
 * Домен маршрутов района.
 */
public class DistrictRoute
	extends BaseDistrictVersionDomain {

   private Long routeId;

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("routeId: ").append(StringUtils.nvl(getRouteId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

}
