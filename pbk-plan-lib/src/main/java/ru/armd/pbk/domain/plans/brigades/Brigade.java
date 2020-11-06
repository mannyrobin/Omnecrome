package ru.armd.pbk.domain.plans.brigades;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Домен бригады.
 */
public class Brigade
	extends BaseDomain {

   private Long deptId;
   private String deptName;
   private Date planDate;
   private Long cityVenueId;
   private Long shiftId;
   private Integer mgtCount;
   private Integer gkuopCount;
   private boolean isAgree;
   private boolean isNotFull;
   private boolean isEnough;
   private boolean haveFreeControlers;
   private String countyIds;
   private int diff;
   private int taskCount;
   private int shiftReserveCount;
   private boolean manual = false;

   public Date getPlanDate() {
	  return planDate;
   }

   public void setPlanDate(Date planDate) {
	  this.planDate = planDate;
   }

   public Long getCityVenueId() {
	  return cityVenueId;
   }

   public void setCityVenueId(Long cityVenueId) {
	  this.cityVenueId = cityVenueId;
   }

   public Long getShiftId() {
	  return shiftId;
   }

   public void setShiftId(Long shiftId) {
	  this.shiftId = shiftId;
   }

   public Integer getMgtCount() {
	  return mgtCount;
   }

   public void setMgtCount(Integer mgtCount) {
	  this.mgtCount = mgtCount;
   }

   public Integer getGkuopCount() {
	  return gkuopCount;
   }

   public void setGkuopCount(Integer gkuopCount) {
	  this.gkuopCount = gkuopCount;
   }

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }

   public boolean getIsAgree() {
	  return isAgree;
   }

   public void setAgree(boolean isAgree) {
	  this.isAgree = isAgree;
   }

   public boolean isNotFull() {
	  return isNotFull;
   }

   public void setNotFull(boolean isNotFull) {
	  this.isNotFull = isNotFull;
   }

   public boolean isEnough() {
	  return isEnough;
   }

   public void setEnough(boolean isEnough) {
	  this.isEnough = isEnough;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("deptId: ").append(StringUtils.nvl(getDeptId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("planDate: ").append(StringUtils.nvl(getPlanDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("cityVenueId: ").append(StringUtils.nvl(getCityVenueId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("mgtCount: ").append(StringUtils.nvl(getMgtCount(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("gkuopCount: ").append(StringUtils.nvl(getGkuopCount(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("isAgree: ").append(StringUtils.nvl(getIsAgree(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("isNotFull: ").append(StringUtils.nvl(isNotFull(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("isEnough: ").append(StringUtils.nvl(isEnough(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public boolean isHaveFreeControlers() {
	  return haveFreeControlers;
   }

   public void setHaveFreeControlers(boolean haveFreeControlers) {
	  this.haveFreeControlers = haveFreeControlers;
   }

   public String getDeptName() {
	  return deptName;
   }

   public void setDeptName(String deptName) {
	  this.deptName = deptName;
   }

   public String getCountyIds() {
	  return countyIds;
   }

   public void setCountyIds(String countyIds) {
	  this.countyIds = countyIds;
   }

   public List<String> getCountyList() {
	  if (countyIds == null) {
		 return new ArrayList<>();
	  }
	  return Arrays.asList(countyIds.split(","));
   }

   public int getDiff() {
	  return diff;
   }

   public void setDiff(int diff) {
	  this.diff = diff;
   }

   public int getTaskCount() {
	  return taskCount;
   }

   public void setTaskCount(int taskCount) {
	  this.taskCount = taskCount;
   }

   public int getShiftReserveCount() {
	  return shiftReserveCount;
   }

   public void setShiftReserveCount(int shiftReserveCount) {
	  this.shiftReserveCount = shiftReserveCount;
   }

   public boolean isManual() {
	  return manual;
   }

   public void setManual(boolean manual) {
	  this.manual = manual;
   }
}