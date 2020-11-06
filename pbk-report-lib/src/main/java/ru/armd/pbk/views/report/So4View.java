package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта "Ежедневная посменная численность контролёров
 * ГУП "Мосгортранс" по территориальному подразделению".
 */
public class So4View
	extends SoView {

   @JsonSerialize(using = SoDateSerializer.class)
   private Date date;
   private int firstShiftCount;
   private int secondShiftCount;
   private int dayCount;
   private int nightCount;
   private int totalCount;
   private String totalTitle;

   public Date getDate() {
	  return date;
   }

   public void setDate(Date date) {
	  this.date = date;
   }

   public int getFirstShiftCount() {
	  return firstShiftCount;
   }

   public void setFirstShiftCount(int firstShiftCount) {
	  this.firstShiftCount = firstShiftCount;
   }

   public int getSecondShiftCount() {
	  return secondShiftCount;
   }

   public void setSecondShiftCount(int secondShiftCount) {
	  this.secondShiftCount = secondShiftCount;
   }

   public int getDayCount() {
	  return dayCount;
   }

   public void setDayCount(int dayCount) {
	  this.dayCount = dayCount;
   }

   public int getNightCount() {
	  return nightCount;
   }

   public void setNightCount(int nightCount) {
	  this.nightCount = nightCount;
   }

   public int getTotalCount() {
	  return totalCount;
   }

   public void setTotalCount(int totalCount) {
	  this.totalCount = totalCount;
   }

   public String getTotalTitle() {
	  return totalTitle;
   }

   public void setTotalTitle(String totalTitle) {
	  this.totalTitle = totalTitle;
   }
}
