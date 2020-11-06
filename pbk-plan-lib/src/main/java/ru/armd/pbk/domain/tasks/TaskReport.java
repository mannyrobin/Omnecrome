package ru.armd.pbk.domain.tasks;

import ru.armd.pbk.core.domain.BaseDomain;

/**
 * Домен отчётов по заданиям.
 */
public class TaskReport
	extends BaseDomain {

   private Long taskId;
   private String tsCheckCount = "0";
   private String ticketIssueCount = "0";
   private String ticketSoldCount = "0";
   private String exemptSkmCount = "0";
   private String exemptSkmoCount = "0";
   private String exemptVesbCount = "0";
   private String exemptOtherLpkCount = "0";
   private String exemptValidLessCount = "0";
   private String plantStowawayCount = "0";
   private String deliveryOvdCount = "0";
   private String driverRaportCount = "0";
   private String ordinance1000Count = "0";
   private String ordinance2500Count = "0";
   private String protocol1000Count = "0";
   private String protocol2500Count = "0";

   public Long getTaskId() {
	  return taskId;
   }

   public void setTaskId(Long taskId) {
	  this.taskId = taskId;
   }

   public String getTsCheckCount() {
	  return tsCheckCount;
   }

   public void setTsCheckCount(String tsCheckCount) {
	  this.tsCheckCount = tsCheckCount;
   }

   public String getTicketIssueCount() {
	  return ticketIssueCount;
   }

   public void setTicketIssueCount(String ticketIssueCount) {
	  this.ticketIssueCount = ticketIssueCount;
   }

   public String getTicketSoldCount() {
	  return ticketSoldCount;
   }

   public void setTicketSoldCount(String ticketSoldCount) {
	  this.ticketSoldCount = ticketSoldCount;
   }

   public String getExemptSkmCount() {
	  return exemptSkmCount;
   }

   public void setExemptSkmCount(String exemptSkmCount) {
	  this.exemptSkmCount = exemptSkmCount;
   }

   public String getExemptSkmoCount() {
	  return exemptSkmoCount;
   }

   public void setExemptSkmoCount(String exemptSkmoCount) {
	  this.exemptSkmoCount = exemptSkmoCount;
   }

   public String getExemptVesbCount() {
	  return exemptVesbCount;
   }

   public void setExemptVesbCount(String exemptVesbCount) {
	  this.exemptVesbCount = exemptVesbCount;
   }

   public String getExemptOtherLpkCount() {
	  return exemptOtherLpkCount;
   }

   public void setExemptOtherLpkCount(String exemptOtherLpkCount) {
	  this.exemptOtherLpkCount = exemptOtherLpkCount;
   }

   public String getExemptValidLessCount() {
	  return exemptValidLessCount;
   }

   public void setExemptValidLessCount(String exemptValidLessCount) {
	  this.exemptValidLessCount = exemptValidLessCount;
   }

   public String getPlantStowawayCount() {
	  return plantStowawayCount;
   }

   public void setPlantStowawayCount(String plantStowawayCount) {
	  this.plantStowawayCount = plantStowawayCount;
   }

   public String getDeliveryOvdCount() {
	  return deliveryOvdCount;
   }

   public void setDeliveryOvdCount(String deliveryOvdCount) {
	  this.deliveryOvdCount = deliveryOvdCount;
   }

   public String getDriverRaportCount() {
	  return driverRaportCount;
   }

   public void setDriverRaportCount(String driverRaportCount) {
	  this.driverRaportCount = driverRaportCount;
   }

   public String getOrdinance1000Count() {
	  return ordinance1000Count;
   }

   public void setOrdinance1000Count(String ordinance1000Count) {
	  this.ordinance1000Count = ordinance1000Count;
   }

   public String getOrdinance2500Count() {
	  return ordinance2500Count;
   }

   public void setOrdinance2500Count(String ordinance2500Count) {
	  this.ordinance2500Count = ordinance2500Count;
   }

   public String getProtocol1000Count() {
	  return protocol1000Count;
   }

   public void setProtocol1000Count(String protocol1000Count) {
	  this.protocol1000Count = protocol1000Count;
   }

   public String getProtocol2500Count() {
	  return protocol2500Count;
   }

   public void setProtocol2500Count(String protocol2500Count) {
	  this.protocol2500Count = protocol2500Count;
   }
}
