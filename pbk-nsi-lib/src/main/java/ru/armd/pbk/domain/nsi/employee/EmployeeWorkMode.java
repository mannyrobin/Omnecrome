package ru.armd.pbk.domain.nsi.employee;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Домен для работы со сущностью "Рабочие время сотрудника".
 */
public class EmployeeWorkMode
	extends BaseDomain {

   private Date workDate;
   private Long employeeId;
   private Long workModeId;
   private BigDecimal workPlanHours;
   private BigDecimal workFactHours;
   private Boolean isChange;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Long getEmployeeId() {
	  return employeeId;
   }

   public void setEmployeeId(Long employeeId) {
	  this.employeeId = employeeId;
   }

   public Long getWorkModeId() {
	  return workModeId;
   }

   public void setWorkModeId(Long workModeId) {
	  this.workModeId = workModeId;
   }

   public BigDecimal getWorkPlanHours() {
	  return workPlanHours;
   }

   public void setWorkPlanHours(BigDecimal workPlanHours) {
	  this.workPlanHours = workPlanHours;
   }

   public BigDecimal getWorkFactHours() {
	  return workFactHours;
   }

   public void setWorkFactHours(BigDecimal workFactHours) {
	  this.workFactHours = workFactHours;
   }

   public Boolean getIsChange() {
	  return isChange;
   }

   public void setIsChange(Boolean isChange) {
	  this.isChange = isChange;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("workDate: ").append(StringUtils.nvl(getWorkDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("employeeId: ").append(StringUtils.nvl(getEmployeeId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("workModeId: ").append(StringUtils.nvl(getWorkModeId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("workPlanHours: ").append(StringUtils.nvl(getWorkPlanHours(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("workFactHours: ").append(StringUtils.nvl(getWorkFactHours(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("isChange: ").append(StringUtils.nvl(getIsChange(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
