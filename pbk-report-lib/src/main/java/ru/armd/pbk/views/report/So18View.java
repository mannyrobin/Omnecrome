package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта "Сопоставительный анализ данных
 * по наряд-заданию и из АСКП".
 */
public class So18View
	extends SoView {

   @JsonSerialize(using = SoDateSerializer.class)
   private Date date;
   private String employee;
   private String routeNumber;
   private Integer tsCheckTaskReportCount;
   private Integer tsCheckAskpCount;

   public Date getDate() {
	  return date;
   }

   public void setDate(Date date) {
	  this.date = date;
   }

   public String getEmployee() {
	  return employee;
   }

   public void setEmployee(String employee) {
	  this.employee = employee;
   }

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public Integer getTsCheckTaskReportCount() {
	  return tsCheckTaskReportCount;
   }

   public void setTsCheckTaskReportCount(Integer tsCheckTaskReportCount) {
	  this.tsCheckTaskReportCount = tsCheckTaskReportCount;
   }

   public Integer getTsCheckAskpCount() {
	  return tsCheckAskpCount;
   }

   public void setTsCheckAskpCount(Integer tsCheckAskpCount) {
	  this.tsCheckAskpCount = tsCheckAskpCount;
   }
}
