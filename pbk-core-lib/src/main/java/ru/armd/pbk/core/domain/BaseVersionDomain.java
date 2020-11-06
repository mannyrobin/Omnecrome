package ru.armd.pbk.core.domain;

import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

/**
 * Базовый класс версионных объектов.
 */
public abstract class BaseVersionDomain
	extends BaseDomain {

   private Date versionStartDate;
   private Date versionEndDate;
   private Long headId;

   /**
	* Конструктор по умолчанию.
	*/
   public BaseVersionDomain() {
   }

   public Date getVersionStartDate() {
	  return versionStartDate;
   }

   public void setVersionStartDate(Date versionStartDate) {
	  this.versionStartDate = versionStartDate;
   }

   public Date getVersionEndDate() {
	  return versionEndDate;
   }

   public void setVersionEndDate(Date versionEndDate) {
	  this.versionEndDate = versionEndDate;
   }

   public Long getHeadId() {
	  return headId;
   }

   public void setHeadId(Long headId) {
	  this.headId = headId;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("versionStartDate: ").append(StringUtils.nvl(getVersionStartDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("versionEndDate: ").append(StringUtils.nvl(getVersionEndDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("headId: ").append(StringUtils.nvl(getHeadId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
