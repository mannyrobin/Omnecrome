package ru.armd.pbk.domain.nsi.department;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен парка депортамента.
 */
public class DepartmentPark
	extends BaseVersionDomain {

   private Long departmentId;
   private Long parkId;

   public Long getDepartmentId() {
	  return departmentId;
   }

   public void setDepartmentId(Long departmentId) {
	  this.departmentId = departmentId;
   }

   public Long getParkId() {
	  return parkId;
   }

   public void setParkId(Long parkId) {
	  this.parkId = parkId;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("departmentId: ").append(StringUtils.nvl(getDepartmentId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("parkId: ").append(StringUtils.nvl(getParkId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
