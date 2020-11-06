package ru.armd.pbk.views.report;

import org.apache.commons.lang3.StringUtils;

import static ru.armd.pbk.utils.StringUtils.binaryToMarkSymbol;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта
 * "Результаты контроля (мотивация)".
 */
public class So6View
	extends SoView {

   private String toSdik;
   private String surname;
   private String name;
   private String patronumic;
   private String personalNumber;
   private String forPlanUse;
   private String underSkm;
   private String scheduleNumber;
   private int planShiftCount;
   private int factShiftCount;
   private int totalPlanCount;
   private int totalFactCount;
   private int factSkmCount;
   private int factSkmoCount;
   private int factVesbCount;
   private int factValidlessCount;
   private int factLpkCount;
   private int factSoldCount;
   private int factStowawayCount;
   private int factDeliveryCount;
   private int fact1000Count;
   private int fact2500Count;
   private int excessSkmCount;
   private int underSkmValue;
   private int planSubtr;
   private Double pcnt; // Процент

   public String getToSdik() {
	  return toSdik;
   }

   public void setToSdik(String toSdik) {
	  this.toSdik = toSdik;
   }

   public String getSurname() {
	  return surname;
   }

   public void setSurname(String surname) {
	  this.surname = surname;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getPatronumic() {
	  return patronumic;
   }

   public void setPatronumic(String patronumic) {
	  this.patronumic = patronumic;
   }

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }

   public String getScheduleNumber() {
	  return scheduleNumber;
   }

   public void setScheduleNumber(String scheduleNumber) {
	  if (StringUtils.contains(scheduleNumber, "004")) {
		 this.scheduleNumber = "004";
	  } else {
		 this.scheduleNumber = scheduleNumber;
	  }
   }

   public String getForPlanUse() {
	  return forPlanUse;
   }

   public void setForPlanUse(String forPlanUse) {
	  this.forPlanUse = binaryToMarkSymbol(forPlanUse);
   }

   public int getPlanShiftCount() {
	  return planShiftCount;
   }

   public void setPlanShiftCount(int planShiftCount) {
	  this.planShiftCount = planShiftCount;
   }

   public int getFactShiftCount() {
	  return factShiftCount;
   }

   public void setFactShiftCount(int factShiftCount) {
	  this.factShiftCount = factShiftCount;
   }

   public int getFactSkmCount() {
	  return factSkmCount;
   }

   public void setFactSkmCount(int factSkmCount) {
	  this.factSkmCount = factSkmCount;
   }

   public int getExcessSkmCount() {
	  return excessSkmCount;
   }

   public void setExcessSkmCount(int excessSkmCount) {
	  this.excessSkmCount = excessSkmCount;
   }

   public String getUnderSkm() {
	  return underSkm;
   }

   public void setUnderSkm(String underSkm) {
	  this.underSkm = binaryToMarkSymbol(underSkm);
   }

   public int getUnderSkmValue() {
	  return underSkmValue;
   }

   public void setUnderSkmValue(int underSkmValue) {
	  this.underSkmValue = underSkmValue;
   }

   public int getPlanSubtr() {
	  return planSubtr;
   }

   public void setPlanSubtr(int planSubtr) {
	  this.planSubtr = planSubtr;
   }

   public Double getPcnt() {
	  return pcnt;
   }

   public void setPcnt(Double pcnt) {
	  this.pcnt = pcnt;
   }

   public int getTotalPlanCount() {
	  return totalPlanCount;
   }

   public void setTotalPlanCount(int totalPlanCount) {
	  this.totalPlanCount = totalPlanCount;
   }

   public int getTotalFactCount() {
	  return totalFactCount;
   }

   public void setTotalFactCount(int totalFactCount) {
	  this.totalFactCount = totalFactCount;
   }

   public int getFactSkmoCount() {
	  return factSkmoCount;
   }

   public void setFactSkmoCount(int factSkmoCount) {
	  this.factSkmoCount = factSkmoCount;
   }

   public int getFactVesbCount() {
	  return factVesbCount;
   }

   public void setFactVesbCount(int factVesbCount) {
	  this.factVesbCount = factVesbCount;
   }

   public int getFactValidlessCount() {
	  return factValidlessCount;
   }

   public void setFactValidlessCount(int factValidlessCount) {
	  this.factValidlessCount = factValidlessCount;
   }

   public int getFactLpkCount() {
	  return factLpkCount;
   }

   public void setFactLpkCount(int factLpkCount) {
	  this.factLpkCount = factLpkCount;
   }

   public int getFactSoldCount() {
	  return factSoldCount;
   }

   public void setFactSoldCount(int factSoldCount) {
	  this.factSoldCount = factSoldCount;
   }

   public int getFactStowawayCount() {
	  return factStowawayCount;
   }

   public void setFactStowawayCount(int factStowawayCount) {
	  this.factStowawayCount = factStowawayCount;
   }

   public int getFactDeliveryCount() {
	  return factDeliveryCount;
   }

   public void setFactDeliveryCount(int factDeliveryCount) {
	  this.factDeliveryCount = factDeliveryCount;
   }

   public int getFact1000Count() {
	  return fact1000Count;
   }

   public void setFact1000Count(int fact1000Count) {
	  this.fact1000Count = fact1000Count;
   }

   public int getFact2500Count() {
	  return fact2500Count;
   }

   public void setFact2500Count(int fact2500Count) {
	  this.fact2500Count = fact2500Count;
   }
}
