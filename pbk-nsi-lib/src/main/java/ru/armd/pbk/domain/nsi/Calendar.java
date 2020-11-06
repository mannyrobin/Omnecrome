package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

/**
 * Домен календаря.
 */
public class Calendar
	extends BaseDomain {

   private String code;
   private String name;
   private String description;
   private Date workDate;
   private Integer workDayTypeId;

   public String getCode() {
	  return code;
   }

   public void setCode(String code) {
	  this.code = code;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getWorkDayTypeId() {
	  return workDayTypeId;
   }

   public void setWorkDayTypeId(Integer workDayTypeId) {
	  this.workDayTypeId = workDayTypeId;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("code: ").append(StringUtils.nvl(getCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(getDescription(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("workDate: ").append(StringUtils.nvl(getWorkDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("workDayTypeId: ").append(StringUtils.nvl(getWorkDayTypeId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
