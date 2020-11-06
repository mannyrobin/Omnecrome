package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен видеорегистраторов.
 */
public class Dvr
	extends BaseVersionDomain {

   private String dvrNumber;
   private String dvrModel;
   private String description;
   private Long deptId;

   public String getDvrNumber() {
	  return dvrNumber;
   }

   public void setDvrNumber(String dvrNumber) {
	  this.dvrNumber = dvrNumber;
   }

   public String getDvrModel() {
	  return dvrModel;
   }

   public void setDvrModel(String dvrModel) {
	  this.dvrModel = dvrModel;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("dvrNumber: ").append(StringUtils.nvl(getDvrNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("dvrModel: ").append(StringUtils.nvl(getDvrModel(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("deptId: ").append(StringUtils.nvl(getDeptId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(getDescription(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
