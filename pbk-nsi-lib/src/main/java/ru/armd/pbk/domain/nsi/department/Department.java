package ru.armd.pbk.domain.nsi.department;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Домен департанмента.
 */
public class Department
	extends BaseVersionDomain {

   private String easuFhdId;
   private String name;
   private String planCount;
   private Long parentDeptId;
   private String fullName;
   private String description;
   private int forPlanUse = 0;
   private Long employeeSignId;
   private List<Long> countyIds = new ArrayList<Long>();
   private String wktGeom;

   public Long getEmployeeSignId() {
	  return employeeSignId;
   }

   public void setEmployeeSignId(Long employeeSignId) {
	  this.employeeSignId = employeeSignId;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }
   
   public String getPlanCount() {
		  return planCount;
	   }

	   public void setPlanCount(String planCount) {
		  this.planCount = planCount;
	   }

   public Long getParentDeptId() {
	  return parentDeptId;
   }

   public void setParentDeptId(Long parentDeptId) {
	  this.parentDeptId = parentDeptId;
   }

   public String getFullName() {
	  return fullName;
   }

   public void setFullName(String fullName) {
	  this.fullName = fullName;
   }

   public String getEasuFhdId() {
	  return easuFhdId;
   }

   public void setEasuFhdId(String easuFhdId) {
	  this.easuFhdId = easuFhdId;
   }

   public int getForPlanUse() {
	  return forPlanUse;
   }

   public void setForPlanUse(int forPlanUse) {
	  this.forPlanUse = forPlanUse;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public List<Long> getCountyIds() {
	  return countyIds;
   }

   public void setCountyIds(List<Long> countyIds) {
	  this.countyIds = countyIds;
   }

   public String getWktGeom() {
	  return wktGeom;
   }

   public void setWktGeom(String wktGeom) {
	  this.wktGeom = wktGeom;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(getDescription(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("easuFhdId: ").append(StringUtils.nvl(getEasuFhdId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("parentDeptId: ").append(StringUtils.nvl(getParentDeptId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("fullName: ").append(StringUtils.nvl(getFullName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("forPlanUse: ").append(StringUtils.nvl(getForPlanUse(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("planCount: ").append(StringUtils.nvl(getPlanCount(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("wktGeom: ").append(StringUtils.nvl(getWktGeom(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

}