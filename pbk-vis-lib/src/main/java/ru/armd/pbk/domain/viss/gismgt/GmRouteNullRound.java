package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.core.IHasCod;
import ru.armd.pbk.utils.StringUtils;

public class GmRouteNullRound
	extends GmBaseDomain
	implements IHasCod {

   private String cod;
   private Long routeVariantMuid;
   private Long typeMuid;
   private Long park1Muid;
   private Long stopPlace1Muid;
   private Long park2Muid;
   private Long stopPlace2Muid;
   private Long park3Muid;
   private Long stopPlace3Muid;
   private String comment;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("cod: ").append(StringUtils.nvl(getCod(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("routeVariantMuid: ").append(StringUtils.nvl(getRouteVariantMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("typeMuid: ").append(StringUtils.nvl(getTypeMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("park1Muid: ").append(StringUtils.nvl(getPark1Muid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopPlace1Muid: ").append(StringUtils.nvl(getStopPlace1Muid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("park2Muid: ").append(StringUtils.nvl(getPark2Muid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopPlace2Muid: ").append(StringUtils.nvl(getStopPlace2Muid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("park3Muid: ").append(StringUtils.nvl(getPark3Muid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopPlace3Muid: ").append(StringUtils.nvl(getStopPlace3Muid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("comment: ").append(StringUtils.nvl(getComment(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   @Override
   public String getCod() {
	  return cod;
   }

   @Override
   public void setCod(String cod) {
	  this.cod = cod;
   }

   public Long getRouteVariantMuid() {
	  return routeVariantMuid;
   }

   public void setRouteVariantMuid(Long routeVariantMuid) {
	  this.routeVariantMuid = routeVariantMuid;
   }

   public Long getTypeMuid() {
	  return typeMuid;
   }

   public void setTypeMuid(Long typeMuid) {
	  this.typeMuid = typeMuid;
   }

   public Long getPark1Muid() {
	  return park1Muid;
   }

   public void setPark1Muid(Long park1Muid) {
	  this.park1Muid = park1Muid;
   }

   public Long getStopPlace1Muid() {
	  return stopPlace1Muid;
   }

   public void setStopPlace1Muid(Long stopPlace1Muid) {
	  this.stopPlace1Muid = stopPlace1Muid;
   }

   public Long getPark2Muid() {
	  return park2Muid;
   }

   public void setPark2Muid(Long park2Muid) {
	  this.park2Muid = park2Muid;
   }

   public Long getStopPlace2Muid() {
	  return stopPlace2Muid;
   }

   public void setStopPlace2Muid(Long stopPlace2Muid) {
	  this.stopPlace2Muid = stopPlace2Muid;
   }

   public Long getPark3Muid() {
	  return park3Muid;
   }

   public void setPark3Muid(Long park3Muid) {
	  this.park3Muid = park3Muid;
   }

   public Long getStopPlace3Muid() {
	  return stopPlace3Muid;
   }

   public void setStopPlace3Muid(Long stopPlace3Muid) {
	  this.stopPlace3Muid = stopPlace3Muid;
   }

   public String getComment() {
	  return comment;
   }

   public void setComment(String comment) {
	  this.comment = comment;
   }

}
