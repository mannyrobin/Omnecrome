package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен водителей парка.
 */
public class ParkDriver
	extends BaseVersionDomain {

   private Long parkId;
   private Long tsDriverId;

   public Long getParkId() {
	  return parkId;
   }

   public void setParkId(Long parkId) {
	  this.parkId = parkId;
   }

   public Long getTsDriverId() {
	  return tsDriverId;
   }

   public void setTsDriverId(Long tsDriverId) {
	  this.tsDriverId = tsDriverId;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("parkId: ").append(StringUtils.nvl(getParkId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("tsDriverId: ").append(StringUtils.nvl(getTsDriverId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

}
