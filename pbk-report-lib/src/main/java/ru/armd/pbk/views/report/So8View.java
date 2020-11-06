package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта "Работа контролеров на маршруте".
 */
public class So8View
	extends SoView {

   @JsonSerialize(using = SoDateSerializer.class)
   private Date date;
   private Integer tsCheckCount;
   private Integer ticketSoldCount;
   private Integer exemptSkmCount;
   private Integer exemptSkmoCount;
   private Integer exemptVesbCount;
   private Integer exemptValidlessCount;
   private Integer deliveryOvdCount;
   private Integer ordinance1000Count;
   private Integer ordinance2500Count;
   private Integer protocol1000Count;
   private Integer protocol2500Count;
   private Integer mgtEmployeeCount;
   private Integer gkuopEmployeeCount;
   private Integer exemptOtherLpkCount;
   private Integer getOffCount;

   public Date getDate() {
	  return date;
   }

   public void setDate(Date date) {
	  this.date = date;
   }

   public Integer getTsCheckCount() {
	  return tsCheckCount;
   }

   public void setTsCheckCount(Integer tsCheckCount) {
	  this.tsCheckCount = tsCheckCount;
   }

   public Integer getTicketSoldCount() {
	  return ticketSoldCount;
   }

   public void setTicketSoldCount(Integer ticketSoldCount) {
	  this.ticketSoldCount = ticketSoldCount;
   }

   public Integer getExemptSkmCount() {
	  return exemptSkmCount;
   }

   public void setExemptSkmCount(Integer exemptSkmCount) {
	  this.exemptSkmCount = exemptSkmCount;
   }

   public Integer getExemptSkmoCount() {
	  return exemptSkmoCount;
   }

   public void setExemptSkmoCount(Integer exemptSkmoCount) {
	  this.exemptSkmoCount = exemptSkmoCount;
   }

   public Integer getExemptVesbCount() {
	  return exemptVesbCount;
   }

   public void setExemptVesbCount(Integer exemptVesbCount) {
	  this.exemptVesbCount = exemptVesbCount;
   }

   public Integer getExemptValidlessCount() {
	  return exemptValidlessCount;
   }

   public void setExemptValidlessCount(Integer exemptValidlessCount) {
	  this.exemptValidlessCount = exemptValidlessCount;
   }

   public Integer getDeliveryOvdCount() {
	  return deliveryOvdCount;
   }

   public void setDeliveryOvdCount(Integer deliveryOvdCount) {
	  this.deliveryOvdCount = deliveryOvdCount;
   }

   public Integer getOrdinance1000Count() {
	  return ordinance1000Count;
   }

   public void setOrdinance1000Count(Integer ordinance1000Count) {
	  this.ordinance1000Count = ordinance1000Count;
   }

   public Integer getOrdinance2500Count() {
	  return ordinance2500Count;
   }

   public void setOrdinance2500Count(Integer ordinance2500Count) {
	  this.ordinance2500Count = ordinance2500Count;
   }

   public Integer getProtocol1000Count() {
	  return protocol1000Count;
   }

   public void setProtocol1000Count(Integer protocol1000Count) {
	  this.protocol1000Count = protocol1000Count;
   }

   public Integer getProtocol2500Count() {
	  return protocol2500Count;
   }

   public void setProtocol2500Count(Integer protocol2500Count) {
	  this.protocol2500Count = protocol2500Count;
   }

   public Integer getMgtEmployeeCount() {
	  return mgtEmployeeCount;
   }

   public void setMgtEmployeeCount(Integer mgtEmployeeCount) {
	  this.mgtEmployeeCount = mgtEmployeeCount;
   }

   public Integer getGkuopEmployeeCount() {
	  return gkuopEmployeeCount;
   }

   public void setGkuopEmployeeCount(Integer gkuopEmployeeCount) {
	  this.gkuopEmployeeCount = gkuopEmployeeCount;
   }

   public Integer getExemptOtherLpkCount() {
	  return exemptOtherLpkCount;
   }

   public void setExemptOtherLpkCount(Integer exemptOtherLpkCount) {
	  this.exemptOtherLpkCount = exemptOtherLpkCount;
   }

    public Integer getGetOffCount() {
        return getOffCount;
    }

    public void setGetOffCount(Integer getOffCount) {
        this.getOffCount = getOffCount;
    }
}
