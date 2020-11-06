package ru.armd.pbk.domain.viss.easu;

import ru.armd.pbk.core.IHasId;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

/**
 * Домен остановочных пунктов.
 */
public class EasuTripSchedule
	extends BaseDomain
	implements IHasId {

   Date date;
   String scheduleCode;
   Long recordNum;
   String routeCode;
   String moveCode;
   Long shiftNum;
   String vehicleType;
   String dayType;
   String shortStopName;
   String stopName;
   String time;
   Long routeNum;

   public Date getDate() {
	  return date;
   }

   public void setDate(Date date) {
	  this.date = date;
   }

   public String getScheduleCode() {
	  return scheduleCode;
   }

   public void setScheduleCode(String scheduleCode) {
	  this.scheduleCode = scheduleCode;
   }

   public String getRouteCode() {
	  return routeCode;
   }

   public void setRouteCode(String routeCode) {
	  this.routeCode = routeCode;
   }

   public String getMoveCode() {
	  return moveCode;
   }

   public void setMoveCode(String moveCode) {
	  this.moveCode = moveCode;
   }

   public String getVehicleType() {
	  return vehicleType;
   }

   public void setVehicleType(String vehicleType) {
	  this.vehicleType = vehicleType;
   }

   public String getDayType() {
	  return dayType;
   }

   public void setDayType(String dayType) {
	  this.dayType = dayType;
   }

   public String getShortStopName() {
	  return shortStopName;
   }

   public void setShortStopName(String shortStopName) {
	  this.shortStopName = shortStopName;
   }

   public String getStopName() {
	  return stopName;
   }

   public void setStopName(String stopName) {
	  this.stopName = stopName;
   }

   public String getTime() {
	  return time;
   }

   public void setTime(String time) {
	  this.time = time;
   }

   public Long getRouteNum() {
	  return routeNum;
   }

   public void setRouteNum(Long routeNum) {
	  this.routeNum = routeNum;
   }

   public void setRecordNum(Long recordNum) {
	  this.recordNum = recordNum;
   }

   public void setShiftNum(Long shiftNum) {
	  this.shiftNum = shiftNum;
   }

   public Long getRecordNum() {
	  return recordNum;
   }

   public Long getShiftNum() {
	  return shiftNum;
   }

   @Override
   public String toAuditString() {
	  String auditStr = super.toAuditString();
	  StringBuilder sb = new StringBuilder(auditStr);
	  sb.append("scheduleCode: ").append(StringUtils.nvl(getScheduleCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("recordNum: ").append(StringUtils.nvl(getRecordNum(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("routeCode: ").append(StringUtils.nvl(getRouteCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("moveCode: ").append(StringUtils.nvl(getMoveCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("shiftNum: ").append(StringUtils.nvl(getShiftNum(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("vehicleType: ").append(StringUtils.nvl(getVehicleType(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("dayType: ").append(StringUtils.nvl(getDayType(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("shortStopName: ").append(StringUtils.nvl(getShortStopName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopName: ").append(StringUtils.nvl(getStopName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("time: ").append(StringUtils.nvl(getTime(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
