package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта "Проходы по БСК контролера".
 */
public class So21View
	extends SoView {

   @JsonSerialize(using = SoDateTimeSerializer.class)
   private Date dateTimes; 
   private String branch;
   private String employee;
   private String bsk;
   private String operator;
   private String route;
   private Integer run;

   public String getBranch() {
	  return branch;
   }

   public void setBranch(String branch) {
	  this.branch = branch;
   }

   public String getEmployee() {
	  return employee;
   }

   public void setEmployee(String employee) {
	  this.employee = employee;
   }

   public String getBsk() {
	  return bsk;
   }

   public void setBsk(String bsk) {
	  this.bsk = bsk;
   }

   public Date getDateTimes() {
	  return dateTimes;
   }

   public void setDateTimes(Date dateTimes) {
	  this.dateTimes = dateTimes;
   }

   public String getOperator() {
	  return operator;
   }

   public void setOperator(String operator) {
	  this.operator = operator;
   }

   public String getRoute() {
	  return route;
   }

   public void setRoute(String route) {
	  this.route = route;
   }

   public Integer getRun() {
	  return run;
   }

   public void setRun(Integer run) {
	  this.run = run;
   }
}
