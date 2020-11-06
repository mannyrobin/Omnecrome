package ru.armd.pbk.views.report;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта
 * "Работа контролеров".
 */
public class So11View
	extends SoView {

   private String toSdik;
   private int planEmployeeCount;
   private int factEmployeeCount;
   private int exemptSkmCount;
   private int exemptSkmoCount;
   private int exemptVesbCount;
   private int exemptLpkCount;
   private int ticketSoldCount;
   private int tsCheckCount;
   private int exemptValidLessCount;
   private int plantStowawayCount;
   private int deliveryOvdCount;
   private int totalSkmSkmoVesb;

   public String getToSdik() {
	  return toSdik;
   }

   public void setToSdik(String toSdik) {
	  this.toSdik = toSdik;
   }

   public int getPlanEmployeeCount() {
	  return planEmployeeCount;
   }

   public void setPlanEmployeeCount(int planEmployeeCount) {
	  this.planEmployeeCount = planEmployeeCount;
   }

   public int getFactEmployeeCount() {
	  return factEmployeeCount;
   }

   public void setFactEmployeeCount(int factEmployeeCount) {
	  this.factEmployeeCount = factEmployeeCount;
   }

   public int getExemptSkmCount() {
	  return exemptSkmCount;
   }

   public void setExemptSkmCount(int exemptSkmCount) {
	  this.exemptSkmCount = exemptSkmCount;
   }

   public int getExemptSkmoCount() {
	  return exemptSkmoCount;
   }

   public void setExemptSkmoCount(int exemptSkmoCount) {
	  this.exemptSkmoCount = exemptSkmoCount;
   }

   public int getExemptVesbCount() {
	  return exemptVesbCount;
   }

   public void setExemptVesbCount(int exemptVesbCount) {
	  this.exemptVesbCount = exemptVesbCount;
   }

   public int getExemptLpkCount() {
	  return exemptLpkCount;
   }

   public void setExemptLpkCount(int exemptLpkCount) {
	  this.exemptLpkCount = exemptLpkCount;
   }

   public int getTicketSoldCount() {
	  return ticketSoldCount;
   }

   public void setTicketSoldCount(int ticketSoldCount) {
	  this.ticketSoldCount = ticketSoldCount;
   }

   public int getTsCheckCount() {
	  return tsCheckCount;
   }

   public void setTsCheckCount(int tsCheckCount) {
	  this.tsCheckCount = tsCheckCount;
   }

   public int getExemptValidLessCount() {
	  return exemptValidLessCount;
   }

   public void setExemptValidLessCount(int exemptValidLessCount) {
	  this.exemptValidLessCount = exemptValidLessCount;
   }

   public int getPlantStowawayCount() {
	  return plantStowawayCount;
   }

   public void setPlantStowawayCount(int plantStowawayCount) {
	  this.plantStowawayCount = plantStowawayCount;
   }

   public int getDeliveryOvdCount() {
	  return deliveryOvdCount;
   }

   public void setDeliveryOvdCount(int deliveryOvdCount) {
	  this.deliveryOvdCount = deliveryOvdCount;
   }

   public int getTotalSkmSkmoVesb() {
	  return totalSkmSkmoVesb;
   }

   public void setTotalSkmSkmoVesb(int totalSkmSkmoVesb) {
	  this.totalSkmSkmoVesb = totalSkmSkmoVesb;
   }

}
