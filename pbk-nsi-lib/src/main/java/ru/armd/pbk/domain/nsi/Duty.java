package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

/**
 * Домен нарядов.
 */
public class Duty
	extends BaseDomain {

   private Date workDate;
   private String easuFhdRouteCode;
   private String tsDepoNumber;
   private String driverPersonalNumber;
   private String moveCode;
   private Date moveStartTime;
   private Date moveEndTime;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public String getEasuFhdRouteCode() {
	  return easuFhdRouteCode;
   }

   public void setEasuFhdRouteCode(String easuFhdRouteCode) {
	  this.easuFhdRouteCode = easuFhdRouteCode;
   }

   public String getTsDepoNumber() {
	  return tsDepoNumber;
   }

   public void setTsDepoNumber(String tsDepoNumber) {
	  this.tsDepoNumber = tsDepoNumber;
   }

   public String getDriverPersonalNumber() {
	  return driverPersonalNumber;
   }

   public void setDriverPersonalNumber(String driverPersonalNumber) {
	  this.driverPersonalNumber = driverPersonalNumber;
   }

   public String getMoveCode() {
	  return moveCode;
   }

   public void setMoveCode(String moveCode) {
	  this.moveCode = moveCode;
   }

   public Date getMoveStartTime() {
	  return moveStartTime;
   }

   public void setMoveStartTime(Date moveStartTime) {
	  this.moveStartTime = moveStartTime;
   }

   public Date getMoveEndTime() {
	  return moveEndTime;
   }

   public void setMoveEndTime(Date moveEndTime) {
	  this.moveEndTime = moveEndTime;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("workDate: ").append(StringUtils.nvl(getWorkDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("easuFhdRouteCode: ").append(StringUtils.nvl(getEasuFhdRouteCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("tsDepoNumber: ").append(StringUtils.nvl(getTsDepoNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("driverPersonalNumber: ").append(StringUtils.nvl(getDriverPersonalNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("moveCode: ").append(StringUtils.nvl(getMoveCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("moveStartTime: ").append(StringUtils.nvl(getMoveStartTime(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("moveEndTime: ").append(StringUtils.nvl(getMoveEndTime(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
