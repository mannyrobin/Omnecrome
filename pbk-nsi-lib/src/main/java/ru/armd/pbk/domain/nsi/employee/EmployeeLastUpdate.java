package ru.armd.pbk.domain.nsi.employee;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

public class EmployeeLastUpdate
	extends BaseDomain {

   private Long employeeId;
   private Date lastUpdate;
   private Long lastDepartmentId;
   private Long currDepartmentId;
   private Date fireDate;
   private Boolean isFire;
   private Boolean isScheduled;

   public Long getEmployeeId() {
	  return employeeId;
   }

   public void setEmployeeId(Long employeeId) {
	  this.employeeId = employeeId;
   }

   public Date getLastUpdate() {
	  return lastUpdate;
   }

   public void setLastUpdate(Date lastUpdate) {
	  this.lastUpdate = lastUpdate;
   }

   public Long getLastDepartmentId() {
	  return lastDepartmentId;
   }

   public void setLastDepartmentId(Long lastDepartmentId) {
	  this.lastDepartmentId = lastDepartmentId;
   }

   public Long getCurrDepartmentId() {
	  return currDepartmentId;
   }

   public void setCurrDepartmentId(Long currDepartmentId) {
	  this.currDepartmentId = currDepartmentId;
   }

   public Date getFireDate() {
	  return fireDate;
   }

   public void setFireDate(Date fireDate) {
	  this.fireDate = fireDate;
   }

   public Boolean getIsFire() {
	  return isFire;
   }

   public void setIsFire(Boolean isFire) {
	  this.isFire = isFire;
   }

    public Boolean getIsScheduled() {
        return isScheduled;
    }

    public void setIsScheduled(Boolean isScheduled) {
        this.isScheduled = isScheduled;
    }

    @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("lastUpdate: ").append(StringUtils.nvl(getLastUpdate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("fireDate: ").append(StringUtils.nvl(getFireDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("employeeId: ").append(StringUtils.nvl(getEmployeeId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("lastDepartmentId: ").append(StringUtils.nvl(getLastDepartmentId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("currDepartmentId: ").append(StringUtils.nvl(getCurrDepartmentId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("isFire: ").append(StringUtils.nvl(getIsFire(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("isScheduled: ").append(StringUtils.nvl(getIsScheduled(), FIELD_NULL)).append(FIELD_SEPARATOR);
      return sb.toString();
   }
}
