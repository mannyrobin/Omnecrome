package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта "Сводный сравнительный анализ данных
 * пассажиропотока (АСКП/АСМ-ПП)".
 */
public class So17View
	extends SoView {

   private Long id;
   @JsonSerialize(using = SoDateSerializer.class)
   private Date date;
   private String routeNumber;
   private String routeCode;
   @JsonSerialize(using = SoTimeSerializer.class)
   private Date routeWorkStartTime;
   @JsonSerialize(using = SoTimeSerializer.class)
   private Date routeWorkEndTime;
   private Integer askpPassengerCount;
   private Integer asmppPassengerCount;
   private Integer diffs;
   private String moveCode;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Date getDate() {
	  return date;
   }

   public void setDate(Date date) {
	  this.date = date;
   }

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public String getRouteCode() {
	  return routeCode;
   }

   public void setRouteCode(String routeCode) {
	  this.routeCode = routeCode;
   }

   public Date getRouteWorkStartTime() {
	  return routeWorkStartTime;
   }

   public void setRouteWorkStartTime(Date routeWorkStartTime) {
	  this.routeWorkStartTime = routeWorkStartTime;
   }

   public Date getRouteWorkEndTime() {
	  return routeWorkEndTime;
   }

   public void setRouteWorkEndTime(Date routeWorkEndTime) {
	  this.routeWorkEndTime = routeWorkEndTime;
   }

   public Integer getAskpPassengerCount() {
	  return askpPassengerCount;
   }

   public void setAskpPassengerCount(Integer askpPassengerCount) {
	  this.askpPassengerCount = askpPassengerCount;
   }

   public Integer getAsmppPassengerCount() {
	  return asmppPassengerCount;
   }

   public void setAsmppPassengerCount(Integer asmppPassengerCount) {
	  this.asmppPassengerCount = asmppPassengerCount;
   }

   public Integer getDiffs() {
	  return diffs;
   }

   public void setDiffs(Integer diffs) {
	  this.diffs = diffs;
   }

   public String getMoveCode() {
	  return moveCode;
   }

   public void setMoveCode(String moveCode) {
	  this.moveCode = moveCode;
   }

}
