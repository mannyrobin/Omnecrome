package ru.armd.pbk.domain.nsi.employee;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

public class EmployeeAbsence
	extends BaseDomain {

   private Long employeeId;
   private Long absenceTypeId;
   private Date periodStartDate;
   private Date periodEndDate;
   private String description;

   public Long getEmployeeId() {
	  return employeeId;
   }

   public void setEmployeeId(Long employeeId) {
	  this.employeeId = employeeId;
   }

   public Long getAbsenceTypeId() {
	  return absenceTypeId;
   }

   public void setAbsenceTypeId(Long absenceTypeId) {
	  this.absenceTypeId = absenceTypeId;
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

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("employeeId: ").append(StringUtils.nvl(getEmployeeId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("absenceTypeId: ").append(StringUtils.nvl(getAbsenceTypeId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("periodStartDate: ").append(StringUtils.nvl(getPeriodStartDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("periodEndDate: ").append(StringUtils.nvl(getPeriodEndDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(getDescription(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
