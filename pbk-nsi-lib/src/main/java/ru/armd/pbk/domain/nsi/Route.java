package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен маршрутов.
 */
public class Route
	extends BaseVersionDomain {

   private String routeNumber;
   private Integer routeNumberInt;
   private String asduRouteId;
   private String askpRouteCode;
   private String asmppRouteCode;
   private Long routeId;
   private Long routeTsKindId;
   private Double profitRatio;
   private String easuFhdRouteCode;
   private Boolean isNight = false;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("routeNumber: ").append(StringUtils.nvl(getRouteNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("routeNumberInt: ").append(StringUtils.nvl(getRouteNumberInt(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("asduRouteId: ").append(StringUtils.nvl(getRouteId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("askpRouteCode: ").append(StringUtils.nvl(getAskpRouteCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("asmppRouteCode: ").append(StringUtils.nvl(getAsmppRouteCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("routeId: ").append(StringUtils.nvl(getRouteId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("routeTsKindId: ").append(StringUtils.nvl(getRouteTsKindId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("profitRatio: ").append(StringUtils.nvl(getProfitRatio(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("easuFhdRouteCode: ").append(StringUtils.nvl(getEasuFhdRouteCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("isNight: ").append(StringUtils.nvl(this.getIsNight(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public Double getProfitRatio() {
	  return profitRatio;
   }

   public void setProfitRatio(Double profitRatio) {
	  this.profitRatio = profitRatio;
   }

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public Integer getRouteNumberInt() {
	  return routeNumberInt;
   }

   public void setRouteNumberInt(Integer routeNumberInt) {
	  this.routeNumberInt = routeNumberInt;
   }

   public String getAsduRouteId() {
	  return asduRouteId;
   }

   public void setAsduRouteId(String asduRouteId) {
	  this.asduRouteId = asduRouteId;
   }

   public String getAskpRouteCode() {
	  return askpRouteCode;
   }

   public void setAskpRouteCode(String askpRouteCode) {
	  this.askpRouteCode = askpRouteCode;
   }

   public String getAsmppRouteCode() {
	  return asmppRouteCode;
   }

   public void setAsmppRouteCode(String asmppRouteCode) {
	  this.asmppRouteCode = asmppRouteCode;
   }

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public Long getRouteTsKindId() {
	  return routeTsKindId;
   }

   public void setRouteTsKindId(Long routeTsKindId) {
	  this.routeTsKindId = routeTsKindId;
   }

   public String getEasuFhdRouteCode() {
	  return easuFhdRouteCode;
   }

   public void setEasuFhdRouteCode(String easuFhdRouteCode) {
	  this.easuFhdRouteCode = easuFhdRouteCode;
   }

   public Boolean getIsNight() {
	  return isNight;
   }

   public void setIsNight(Boolean isNight) {
	  this.isNight = isNight;
   }

}
