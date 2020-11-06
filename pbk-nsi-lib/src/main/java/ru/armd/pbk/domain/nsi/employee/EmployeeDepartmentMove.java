package ru.armd.pbk.domain.nsi.employee;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

public class EmployeeDepartmentMove
	extends BaseDomain {

   private Long employeeId;
   private Long departmentId;
   private Date periodStartDate;
   private Date periodEndDate;

   public Long getEmployeeId() {
	  return employeeId;
   }

   public void setEmployeeId(Long employeeId) {
	  this.employeeId = employeeId;
   }

   public Long getDepartmentId() {
	  return departmentId;
   }

   public void setDepartmentId(Long departmentId) {
	  this.departmentId = departmentId;
   }

   public Date getPeriodStartDate() {
	  return periodStartDate;
   }

   public void setPeriodStartDate(Date periodStartDate) {
	  this.periodStartDate = periodStartDate;
   }

   public Date getPeriodEndDate() {
	  return periodEndDate;
   }

   public void setPeriodEndDate(Date periodEndDate) {
	  this.periodEndDate = periodEndDate;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("employeeId: ").append(StringUtils.nvl(getEmployeeId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("departmentId: ").append(StringUtils.nvl(getDepartmentId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("periodStartDate: ").append(StringUtils.nvl(getPeriodStartDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("periodEndDate: ").append(StringUtils.nvl(getPeriodEndDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
