package ru.armd.pbk.domain.nsi.district;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

public class BaseDistrictVersionDomain
	extends BaseVersionDomain {

   private Long districtId;

   public Long getDistrictId() {
	  return districtId;
   }

   public void setDistrictId(Long districtId) {
	  this.districtId = districtId;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("districtId: ").append(StringUtils.nvl(getDistrictId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
