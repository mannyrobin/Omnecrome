package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.HyphenSepratedDateSerializer;

import java.util.Date;

/**
 * View для сводных данных по дате (для использования в отчёте "Сводные данные по работе контролеров за период").
 */
public class So23DaySummariesView
	extends SoView {

   @JsonSerialize(using = HyphenSepratedDateSerializer.class)
   private Date date;
   private Integer exemptSkm;
   private Integer exemptSkmo;
   private Integer exemptVesb;
   private Integer lpk;
   private Integer tsCheck;
   private Integer exemptValidLess;
   private Integer plantStowaway;
   private Integer deliveryOvd;
   private Integer ordinance1000;
   private Integer ordinance2500;
   private Integer ticketSold;
   private Integer shiftId;
   private Integer sumByShifts;

   public Date getDate() {
	  return date;
   }

   public void setDate(Date date) {
	  this.date = date;
   }

   public Integer getOrdinance1000() {
		  return ordinance1000;
	   }

	   public void setOrdinance1000(Integer ordinance1000) {
		  this.ordinance1000 = ordinance1000;
	   }

	   public Integer getOrdinance2500() {
		  return ordinance2500;
	   }

	   public void setOrdinance2500(Integer ordinance2500) {
		  this.ordinance2500 = ordinance2500;
	   }

	   public Integer getTicketSold() {
		  return ticketSold;
	   }

	   public void setTicketSold(Integer ticketSold) {
		  this.ticketSold = ticketSold;
	   }

	   public Integer getExemptSkm() {
		  return exemptSkm;
	   }

	   public void setExemptSkm(Integer exemptSkm) {
		  this.exemptSkm = exemptSkm;
	   }

	   public Integer getExemptSkmo() {
		  return exemptSkmo;
	   }

	   public void setExemptSkmo(Integer exemptSkmo) {
		  this.exemptSkmo = exemptSkmo;
	   }

	   public Integer getExemptVesb() {
		  return exemptVesb;
	   }

	   public void setExemptVesb(Integer exemptVesb) {
		  this.exemptVesb = exemptVesb;
	   }

	   public Integer getLpk() {
		  return lpk;
	   }

	   public void setLpk(Integer lpk) {
		  this.lpk = lpk;
	   }

	   public Integer getTsCheck() {
		  return tsCheck;
	   }

	   public void setTsCheck(Integer tsCheck) {
		  this.tsCheck = tsCheck;
	   }

	   public Integer getExemptValidLess() {
		  return exemptValidLess;
	   }

	   public void setExemptValidLess(Integer exemptValidLess) {
		  this.exemptValidLess = exemptValidLess;
	   }

	   public Integer getPlantStowaway() {
		  return plantStowaway;
	   }

	   public void setPlantStowaway(Integer plantStowaway) {
		  this.plantStowaway = plantStowaway;
	   }

	   public Integer getDeliveryOvd() {
		  return deliveryOvd;
	   }

	   public void setDeliveryOvd(Integer deliveryOvd) {
		  this.deliveryOvd = deliveryOvd;
	   }

	   public Integer getShiftId() {
		  return shiftId;
	   }

	   public void setShiftId(Integer shiftId) {
		  this.shiftId = shiftId;
	   }

	   public Integer getSumByShifts() {
		  return sumByShifts;
	   }

	   public void setSumByShifts(Integer sumByShifts) {
		  this.sumByShifts = sumByShifts;
	   }
}
