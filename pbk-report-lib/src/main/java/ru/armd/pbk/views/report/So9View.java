package ru.armd.pbk.views.report;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта "Сводные данные по работе
 * контролеров по подразделениям".
 */
public class So9View
	extends SoView {

   private String employee;
   private String personalNumber;
   private Integer exemptSkmCount;
   private Integer exemptSkmoCount;
   private Integer exemptVesbCount;
   private Integer lpkCount;
   private Integer tsCheckCount;
   private Integer exemptValidLessCount;
   private Integer plantStowawayCount;
   private Integer deliveryOvdCount;

   private Integer ordinance1000Count;
   private Integer ordinance2500Count;
   private Integer protocol1000Count;
   private Integer protocol2500Count;
   private Integer ticketSoldCount;

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

   public Integer getTicketSoldCount() {
	  return ticketSoldCount;
   }

   public void setTicketSoldCount(Integer ticketSoldCount) {
	  this.ticketSoldCount = ticketSoldCount;
   }

   public String getEmployee() {
	  return employee;
   }

   public void setEmployee(String employee) {
	  this.employee = employee;
   }

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
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

   public Integer getLpkCount() {
	  return lpkCount;
   }

   public void setLpkCount(Integer lpkCount) {
	  this.lpkCount = lpkCount;
   }

   public Integer getTsCheckCount() {
	  return tsCheckCount;
   }

   public void setTsCheckCount(Integer tsCheckCount) {
	  this.tsCheckCount = tsCheckCount;
   }

   public Integer getExemptValidLessCount() {
	  return exemptValidLessCount;
   }

   public void setExemptValidLessCount(Integer exemptValidLessCount) {
	  this.exemptValidLessCount = exemptValidLessCount;
   }

   public Integer getPlantStowawayCount() {
	  return plantStowawayCount;
   }

   public void setPlantStowawayCount(Integer plantStowawayCount) {
	  this.plantStowawayCount = plantStowawayCount;
   }

   public Integer getDeliveryOvdCount() {
	  return deliveryOvdCount;
   }

   public void setDeliveryOvdCount(Integer deliveryOvdCount) {
	  this.deliveryOvdCount = deliveryOvdCount;
   }
}
