package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

/**
 * Домен смены.
 */
public class Shift
	extends BaseDomain {

   public enum CODE {
	  HOLIDAY,
	  DAY,
	  NIGHT,
	  VACATION,
	  SICK,
	  I,
	  II,
          III,
	  OTHER,
          LINE1,
          LINE2;
   }

   private String code;
   private String name;
   private String description;
   private Integer reserveCount;
   private Date workStartTime;
   private Date workEndTime;
   private Date breakStartTime;
   private Date breakEndTime;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("code: ").append(StringUtils.nvl(getCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(getDescription(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("reserveCount: ").append(StringUtils.nvl(getReserveCount(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("workStartTime: ").append(StringUtils.nvl(getWorkStartTime(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("workEndTime: ").append(StringUtils.nvl(getWorkEndTime(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("breakStartTime: ").append(StringUtils.nvl(getBreakStartTime(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("breakEndTime: ").append(StringUtils.nvl(getBreakEndTime(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

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

   public Integer getReserveCount() {
	  return reserveCount;
   }

   public void setReserveCount(Integer reserveCount) {
	  this.reserveCount = reserveCount;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Date getWorkStartTime() {
	  return workStartTime;
   }

   public void setWorkStartTime(Date workStartTime) {
	  this.workStartTime = workStartTime;
   }

   public Date getWorkEndTime() {
	  return workEndTime;
   }

   public void setWorkEndTime(Date workEndTime) {
	  this.workEndTime = workEndTime;
   }

   public Date getBreakStartTime() {
	  return breakStartTime;
   }

   public void setBreakStartTime(Date breakStartTime) {
	  this.breakStartTime = breakStartTime;
   }

   public Date getBreakEndTime() {
	  return breakEndTime;
   }

   public void setBreakEndTime(Date breakEndTime) {
	  this.breakEndTime = breakEndTime;
   }
}