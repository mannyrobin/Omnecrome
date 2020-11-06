package ru.armd.pbk.views.nsi.askp;

import ru.armd.pbk.core.views.BaseGridView;

import java.util.List;
import java.util.Map;

/**
 * View для данных из АСКП.
 */
public class AskpChecksByStopsView
	extends BaseGridView {
   private Integer tripId;
   private Integer tripNum;
   private Integer tripKind;
   private Integer orderNum;
   private String stopName;
   private Integer stopId;
   private Integer sum;
   private Integer sumPaid;
   private List<Integer> hours;
   private Map<Integer, String> extcols;
   private String moveCode;

   public String getMoveCode() {
	  return moveCode;
   }

   public void setMoveCode(String moveCode) {
	  this.moveCode = moveCode;
   }

   public Integer getTripId() {
	  return tripId;
   }

   public void setTripId(Integer tripId) {
	  this.tripId = tripId;
   }

   public Integer getTripNum() {
	  return tripNum;
   }

   public void setTripNum(Integer tripNum) {
	  this.tripNum = tripNum;
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

   public Integer getSumPaid() {
	  return sumPaid;
   }

   public void setSumPaid(Integer sumPaid) {
	  this.sumPaid = sumPaid;
   }

   public Integer getSum() {
	  return sum;
   }

   public void setSum(Integer sum) {
	  this.sum = sum;
   }

   public List<Integer> getHours() {
	  return hours;
   }

   public void setHours(List<Integer> hours) {
	  this.hours = hours;
   }

   public Map<Integer, String> getExtcols() {
	  return extcols;
   }

   public void setExtcols(Map<Integer, String> extcols) {
	  this.extcols = extcols;
   }
}
