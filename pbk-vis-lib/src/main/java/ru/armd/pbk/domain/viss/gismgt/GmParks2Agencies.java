package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.utils.StringUtils;

public class GmParks2Agencies
	extends GmBaseDomain {

   private Long parkMuid;
   private Long agencyMuid;

   public Long getParkMuid() {
	  return parkMuid;
   }

   public void setParkMuid(Long parkMuid) {
	  this.parkMuid = parkMuid;
   }

   public Long getAgencyMuid() {
	  return agencyMuid;
   }

   public void setAgencyMuid(Long agencyMuid) {
	  this.agencyMuid = agencyMuid;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("parkMuid: ").append(StringUtils.nvl(getParkMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("agencyMuid: ").append(StringUtils.nvl(getAgencyMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

}
