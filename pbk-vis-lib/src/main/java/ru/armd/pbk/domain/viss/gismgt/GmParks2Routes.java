package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.utils.StringUtils;

public class GmParks2Routes
	extends GmBaseDomain {

   private Long parkMuid;
   private Long routeMuid;

   public Long getRouteMuid() {
	  return routeMuid;
   }

   public void setRouteMuid(Long routeMuid) {
	  this.routeMuid = routeMuid;
   }

   public Long getParkMuid() {
	  return parkMuid;
   }

   public void setParkMuid(Long parkMuid) {
	  this.parkMuid = parkMuid;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("parkMuid: ").append(StringUtils.nvl(getParkMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("routeMuid: ").append(StringUtils.nvl(getRouteMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
