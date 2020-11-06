package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта "Сравнительный анализ данных
 * пассажиропотока (АСКП/АСМ-ПП) поостановочно".
 */
public class So16View
	extends SoView {

   private Long id;
   @JsonSerialize(using = SoDateSerializer.class)
   private Date date;
   private String routeNumber;
   private String routeCode;
   private Integer routeOutgoNumber;
   private String stopName;
   @JsonSerialize(using = SoTimeSerializer.class)
   private Date askpArrivalTime;
   @JsonSerialize(using = SoTimeSerializer.class)
   private Date asmppArrivalTime;
   private Integer askpPassengerCount;
   private Integer asmppPassengerCount;
   private Integer diffs;

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

   public Integer getRouteOutgoNumber() {
	  return routeOutgoNumber;
   }

   public void setRouteOutgoNumber(Integer routeOutgoNumber) {
	  this.routeOutgoNumber = routeOutgoNumber;
   }

   public String getStopName() {
	  return stopName;
   }

   public void setStopName(String stopName) {
	  this.stopName = stopName;
   }

   public Date getAskpArrivalTime() {
	  return askpArrivalTime;
   }

   public void setAskpArrivalTime(Date askpArrivalTime) {
	  this.askpArrivalTime = askpArrivalTime;
   }

   public Date getAsmppArrivalTime() {
	  return asmppArrivalTime;
   }

   public void setAsmppArrivalTime(Date asmppArrivalTime) {
	  this.asmppArrivalTime = asmppArrivalTime;
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
}
