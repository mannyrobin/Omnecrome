package ru.armd.pbk.domain.viss.asdu;

import ru.armd.pbk.core.IHasId;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

/**
 * Домен остановочных пунктов.
 */
public class AsduPlanTrip
	extends BaseDomain
	implements IHasId {

   Date date;
   String mrCode;
   String depotNumber;
   Long shiftNum;
   Long routeNum;
   Long orderNum;
   Long stopId;
   Long ermId;
   Long tripId;
   Long grafic;

   public Date getDate() {
	  return date;
   }

   public void setDate(Date date) {
	  this.date = date;
   }

   public String getMrCode() {
	  return mrCode;
   }

   public void setMrCode(String mrCode) {
	  this.mrCode = mrCode;
   }

   public String getDepotNumber() {
	  return depotNumber;
   }

   public void setDepotNumber(String depotNumber) {
	  this.depotNumber = depotNumber;
   }

   public Long getShiftNum() {
	  return shiftNum;
   }

   public void setShiftNum(Long shiftNum) {
	  this.shiftNum = shiftNum;
   }

   public Long getRouteNum() {
	  return routeNum;
   }

   public void setRouteNum(Long routeNum) {
	  this.routeNum = routeNum;
   }

   public Long getOrderNum() {
	  return orderNum;
   }

   public void setOrderNum(Long orderNum) {
	  this.orderNum = orderNum;
   }

   public Long getStopId() {
	  return stopId;
   }

   public void setStopId(Long stopId) {
	  this.stopId = stopId;
   }

   public Long getErmId() {
	  return ermId;
   }

   public void setErmId(Long ermId) {
	  this.ermId = ermId;
   }

   public Long getTripId() {
	  return tripId;
   }

   public void setTripId(Long tripId) {
	  this.tripId = tripId;
   }

   public Long getGrafic() {
	  return grafic;
   }

   public void setGrafic(Long grafic) {
	  this.grafic = grafic;
   }

   @Override
   public String toAuditString() {
	  String auditStr = super.toAuditString();
	  StringBuilder sb = new StringBuilder(auditStr);
	  sb.append("date: ").append(StringUtils.nvl(getDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("mrCode: ").append(StringUtils.nvl(getMrCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("depotNumber: ").append(StringUtils.nvl(getDepotNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("shiftNum: ").append(StringUtils.nvl(getShiftNum(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("routeNum: ").append(StringUtils.nvl(getRouteNum(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("orderNum: ").append(StringUtils.nvl(getOrderNum(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopId: ").append(StringUtils.nvl(getStopId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("ermId: ").append(StringUtils.nvl(getErmId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("tripId: ").append(StringUtils.nvl(getTripId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("grafic: ").append(StringUtils.nvl(getGrafic(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
