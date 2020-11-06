package ru.armd.pbk.domain.viss.asdu;

import ru.armd.pbk.core.IHasId;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен остановочных пунктов.
 */
public class AsduStop
	extends BaseDomain
	implements IHasId {
   Long asduStopId;
   String stopCode;
   String stopName;
   String stopDesc;
   Double stopLat;
   Double stopLon;
   Long zoneId;
   String stopUrl;
   String locationType;
   String parentStation;
   String stopTimezone;
   String wheelchairBoarding;
   String stopPolygon;

   public Long getAsduStopId() {
	  return asduStopId;
   }

   public void setAsduStopId(Long asduStopId) {
	  this.asduStopId = asduStopId;
   }

   public String getStopCode() {
	  return stopCode;
   }

   public void setStopCode(String stopCode) {
	  this.stopCode = stopCode;
   }

   public String getStopName() {
	  return stopName;
   }

   public void setStopName(String stopName) {
	  this.stopName = stopName;
   }

   public String getStopDesc() {
	  return stopDesc;
   }

   public void setStopDesc(String stopDesc) {
	  this.stopDesc = stopDesc;
   }

   public Double getStopLat() {
	  return stopLat;
   }

   public void setStopLat(Double stopLat) {
	  this.stopLat = stopLat;
   }

   public Double getStopLon() {
	  return stopLon;
   }

   public void setStopLon(Double stopLon) {
	  this.stopLon = stopLon;
   }

   public Long getZoneId() {
	  return zoneId;
   }

   public void setZoneId(Long zoneId) {
	  this.zoneId = zoneId;
   }

   public String getStopUrl() {
	  return stopUrl;
   }

   public void setStopUrl(String stopUrl) {
	  this.stopUrl = stopUrl;
   }

   public String getLocationType() {
	  return locationType;
   }

   public void setLocationType(String locationType) {
	  this.locationType = locationType;
   }

   public String getParentStation() {
	  return parentStation;
   }

   public void setParentStation(String parentStation) {
	  this.parentStation = parentStation;
   }

   public String getStopTimezone() {
	  return stopTimezone;
   }

   public void setStopTimezone(String stopTimezone) {
	  this.stopTimezone = stopTimezone;
   }

   public String getWheelchairBoarding() {
	  return wheelchairBoarding;
   }

   public void setWheelchairBoarding(String wheelchairBoarding) {
	  this.wheelchairBoarding = wheelchairBoarding;
   }

   public String getStopPolygon() {
	  return stopPolygon;
   }

   public void setStopPolygon(String stopPolygon) {
	  this.stopPolygon = stopPolygon;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("asduStopId: ").append(StringUtils.nvl(getAsduStopId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopCode: ").append(StringUtils.nvl(getStopCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopName: ").append(StringUtils.nvl(getStopName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopDesc: ").append(StringUtils.nvl(getStopDesc(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopLat: ").append(StringUtils.nvl(getStopLat(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopLon: ").append(StringUtils.nvl(getStopLon(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("zoneId: ").append(StringUtils.nvl(getZoneId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopUrl: ").append(StringUtils.nvl(getStopUrl(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("locationType: ").append(StringUtils.nvl(getLocationType(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("parentStation: ").append(StringUtils.nvl(getParentStation(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopTimezone: ").append(StringUtils.nvl(getStopTimezone(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("wheelchairBoarding: ").append(StringUtils.nvl(getWheelchairBoarding(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopPolygon: ").append(StringUtils.nvl(getStopPolygon(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
