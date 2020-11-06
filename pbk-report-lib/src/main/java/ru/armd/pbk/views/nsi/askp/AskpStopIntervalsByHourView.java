package ru.armd.pbk.views.nsi.askp;

import ru.armd.pbk.core.views.BaseGridView;

import java.util.Date;

/**
 * View для данных из АСКП.
 */
public class AskpStopIntervalsByHourView
	extends BaseGridView {

   private Date workDate;
   private Integer tripId;
   private Integer tripKind;
   private Integer orderNum;
   private Integer stopId;
   private String stopName;
   private Integer hour;
   private Integer checks;
   private Integer checksPaid;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getTripId() {
	  return tripId;
   }

   public void setTripId(Integer tripId) {
	  this.tripId = tripId;
   }

   public Integer getTripKind() {
	  return tripKind;
   }

   public void setTripKind(Integer tripKind) {
	  this.tripKind = tripKind;
   }

   public Integer getOrderNum() {
	  return orderNum;
   }

   public void setOrderNum(Integer orderNum) {
	  this.orderNum = orderNum;
   }

   public Integer getStopId() {
	  return stopId;
   }

   public void setStopId(Integer stopId) {
	  this.stopId = stopId;
   }

   public String getStopName() {
	  return stopName;
   }

   public void setStopName(String stopName) {
	  this.stopName = stopName;
   }

   public Integer getHour() {
	  return hour;
   }

   public void setHour(Integer hour) {
	  this.hour = hour;
   }

   public Integer getChecks() {
	  return checks;
   }

   public void setChecks(Integer checks) {
	  this.checks = checks;
   }

   public Integer getChecksPaid() {
	  return checksPaid;
   }

   public void setChecksPaid(Integer checksPaid) {
	  this.checksPaid = checksPaid;
   }

}
