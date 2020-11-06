package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

public class GmRoute
	extends GmBaseDomain {

   private String number;
   private Long kindMuid;
   private Long transportKindMuid;
   private Long transportationKindMuid;
   private Long stateMuid;
   private Long state2Muid;
   private Long currentRouteVariantMuid;
   private Long agencyMuid;
   private Long openOrderMuid;
   private Long closeOrderMuid;
   private String comment;
   private Date openDate;
   private Date closeDate;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("number: ").append(StringUtils.nvl(getNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("kindMuid: ").append(StringUtils.nvl(getKindMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("transportKindMuid: ").append(StringUtils.nvl(getTransportKindMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("transportationKindMuid: ").append(StringUtils.nvl(getTransportKindMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stateMuid: ").append(StringUtils.nvl(getStateMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("state2Muid: ").append(StringUtils.nvl(getState2Muid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("currentRouteVariantMuid: ").append(StringUtils.nvl(getCurrentRouteVariantMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("agencyMuid: ").append(StringUtils.nvl(getAgencyMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("openOrderMuid: ").append(StringUtils.nvl(getOpenOrderMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("closeOrderMuid: ").append(StringUtils.nvl(getCloseOrderMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("comment: ").append(StringUtils.nvl(getComment(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("openDate: ").append(StringUtils.nvl(getOpenDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("closeDate: ").append(StringUtils.nvl(getCloseDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public String getNumber() {
	  return number;
   }

   public void setNumber(String number) {
	  this.number = number;
   }

   public Long getKindMuid() {
	  return kindMuid;
   }

   public void setKindMuid(Long KindMuid) {
	  this.kindMuid = KindMuid;
   }

   public Long getTransportKindMuid() {
	  return transportKindMuid;
   }

   public void setTransportKindMuid(Long transportKindMuid) {
	  this.transportKindMuid = transportKindMuid;
   }

   public Long getTransportationKindMuid() {
	  return transportationKindMuid;
   }

   public void setTransportationKindMuid(Long transportationKindMuid) {
	  this.transportationKindMuid = transportationKindMuid;
   }

   public Long getStateMuid() {
	  return stateMuid;
   }

   public void setStateMuid(Long stateMuid) {
	  this.stateMuid = stateMuid;
   }

   public Long getState2Muid() {
	  return state2Muid;
   }

   public void setState2Muid(Long state2Muid) {
	  this.state2Muid = state2Muid;
   }

   public Long getCurrentRouteVariantMuid() {
	  return currentRouteVariantMuid;
   }

   public void setCurrentRouteVariantMuid(Long currentRouteVariantMuid) {
	  this.currentRouteVariantMuid = currentRouteVariantMuid;
   }

   public Long getAgencyMuid() {
	  return agencyMuid;
   }

   public void setAgencyMuid(Long agencyMuid) {
	  this.agencyMuid = agencyMuid;
   }

   public Long getOpenOrderMuid() {
	  return openOrderMuid;
   }

   public void setOpenOrderMuid(Long openOrderMuid) {
	  this.openOrderMuid = openOrderMuid;
   }

   public Long getCloseOrderMuid() {
	  return closeOrderMuid;
   }

   public void setCloseOrderMuid(Long closeOrderMuid) {
	  this.closeOrderMuid = closeOrderMuid;
   }

   public String getComment() {
	  return comment;
   }

   public void setComment(String comment) {
	  this.comment = comment;
   }

   public Date getOpenDate() {
	  return openDate;
   }

   public void setOpenDate(Date openDate) {
	  this.openDate = openDate;
   }

   public Date getCloseDate() {
	  return closeDate;
   }

   public void setCloseDate(Date closeDate) {
	  this.closeDate = closeDate;
   }

}
