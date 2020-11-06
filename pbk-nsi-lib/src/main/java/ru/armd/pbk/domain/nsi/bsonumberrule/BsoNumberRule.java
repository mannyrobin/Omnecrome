package ru.armd.pbk.domain.nsi.bsonumberrule;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен правил формирования номеров БСО.
 */
public class BsoNumberRule
	extends BaseDomain {

   private Long bsoTypeId;
   private String bsoTypeCode;
   private Long branchId;
   private String branchCode;
   private Long departmentId;
   private String departmentCode;
   private Long increment;

   public Long getBsoTypeId() {
	  return bsoTypeId;
   }

   public void setBsoTypeId(Long bsoTypeId) {
	  this.bsoTypeId = bsoTypeId;
   }

   public String getBsoTypeCode() {
	  return bsoTypeCode;
   }

   public void setBsoTypeCode(String bsoTypeCode) {
	  this.bsoTypeCode = bsoTypeCode;
   }

   public Long getBranchId() {
	  return branchId;
   }

   public void setBranchId(Long branchId) {
	  this.branchId = branchId;
   }

   public String getBranchCode() {
	  return branchCode;
   }

   public void setBranchCode(String branchCode) {
	  this.branchCode = branchCode;
   }

   public Long getDepartmentId() {
	  return departmentId;
   }

   public void setDepartmentId(Long departmentId) {
	  this.departmentId = departmentId;
   }

   public String getDepartmentCode() {
	  return departmentCode;
   }

   public void setDepartmentCode(String departmentCode) {
	  this.departmentCode = departmentCode;
   }

   public Long getIncrement() {
	  return increment;
   }

   public void setIncrement(Long increment) {
	  this.increment = increment;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("bsoTypeId: ").append(StringUtils.nvl(getBsoTypeId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("bsoTypeCode: ").append(StringUtils.nvl(getBsoTypeCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("branchId: ").append(StringUtils.nvl(getBranchId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("branchCode: ").append(StringUtils.nvl(getBranchCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("departmentId: ").append(StringUtils.nvl(getDepartmentId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("departmentCode: ").append(StringUtils.nvl(getDepartmentCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("increment: ").append(StringUtils.nvl(getIncrement(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
