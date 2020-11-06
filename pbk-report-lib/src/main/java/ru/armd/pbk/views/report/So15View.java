package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта "Итоги ПБК по контролеру по данным АСУ ПБК".
 */
public class So15View
	extends SoView {

   @JsonSerialize(using = SoDateTimeSerializer.class)
   private Date checkStartDateTime;
   private String routeNumber;
   private Integer tsOutgoNumber;
   private String employee;
   private String department;
   private Integer checkedPassengerCount;

   public String getDepartment() {
	  return department;
   }

   public void setDepartment(String department) {
	  this.department = department;
   }

   public Date getCheckStartDateTime() {
	  return checkStartDateTime;
   }

   public void setCheckStartDateTime(Date checkStartDateTime) {
	  this.checkStartDateTime = checkStartDateTime;
   }

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public Integer getTsOutgoNumber() {
	  return tsOutgoNumber;
   }

   public void setTsOutgoNumber(Integer tsOutgoNumber) {
	  this.tsOutgoNumber = tsOutgoNumber;
   }

   public String getEmployee() {
	  return employee;
   }

   public void setEmployee(String employee) {
	  this.employee = employee;
   }

   public Integer getCheckedPassengerCount() {
	  return checkedPassengerCount;
   }

   public void setCheckedPassengerCount(Integer checkedPassengerCount) {
	  this.checkedPassengerCount = checkedPassengerCount;
   }
}
