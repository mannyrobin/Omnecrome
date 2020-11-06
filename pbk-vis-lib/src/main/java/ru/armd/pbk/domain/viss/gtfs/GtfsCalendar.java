package ru.armd.pbk.domain.viss.gtfs;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен "Справочник периодов действий маршрутов".
 */
public class GtfsCalendar
	extends BaseDomain {

   private Date workDate;
   private Integer routeId;
   private Integer serviceId;
   private Integer monday;
   private Integer tuesday;
   private Integer wednesday;
   private Integer thursday;
   private Integer friday;
   private Integer saturday;
   private Integer sunday;
   private Date startDate;
   private Date endDate;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getRouteId() {
	  return routeId;
   }

   public void setRouteId(Integer routeId) {
	  this.routeId = routeId;
   }

   public Integer getServiceId() {
	  return serviceId;
   }

   public void setServiceId(Integer serviceId) {
	  this.serviceId = serviceId;
   }

   public Integer getMonday() {
	  return monday;
   }

   public void setMonday(Integer monday) {
	  this.monday = monday;
   }

   public Integer getTuesday() {
	  return tuesday;
   }

   public void setTuesday(Integer tuesday) {
	  this.tuesday = tuesday;
   }

   public Integer getWednesday() {
	  return wednesday;
   }

   public void setWednesday(Integer wednesday) {
	  this.wednesday = wednesday;
   }

   public Integer getThursday() {
	  return thursday;
   }

   public void setThursday(Integer thursday) {
	  this.thursday = thursday;
   }

   public Integer getFriday() {
	  return friday;
   }

   public void setFriday(Integer friday) {
	  this.friday = friday;
   }

   public Integer getSaturday() {
	  return saturday;
   }

   public void setSaturday(Integer saturday) {
	  this.saturday = saturday;
   }

   public Integer getSunday() {
	  return sunday;
   }

   public void setSunday(Integer sunday) {
	  this.sunday = sunday;
   }

   public Date getStartDate() {
	  return startDate;
   }

   public void setStartDate(Date startDate) {
	  this.startDate = startDate;
   }

   public Date getEndDate() {
	  return endDate;
   }

   public void setEndDate(Date endDate) {
	  this.endDate = endDate;
   }

}
