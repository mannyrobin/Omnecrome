package ru.armd.pbk.views.report;

/**
 * Created by Yakov Volkov.
 */
public class So20MediumResultView
	extends SoView {

   private String routeNumber;
   private Integer checkCount;
   private Integer scmSum;
   private Integer scmoSum;
   private Integer vesbSum;
   private Integer otherSum;

   private String shiftResult;

   public String getType() {
	  return "So20MediumResult";
   }

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public Integer getCheckCount() {
	  return checkCount;
   }

   public void setCheckCount(Integer checkCount) {
	  this.checkCount = checkCount;
   }

   public Integer getScmSum() {
	  return scmSum;
   }

   public void setScmSum(Integer scmSum) {
	  this.scmSum = scmSum;
   }

   public Integer getScmoSum() {
	  return scmoSum;
   }

   public void setScmoSum(Integer scmoSum) {
	  this.scmoSum = scmoSum;
   }

   public Integer getVesbSum() {
	  return vesbSum;
   }

   public void setVesbSum(Integer vesbSum) {
	  this.vesbSum = vesbSum;
   }

   public Integer getOtherSum() {
	  return otherSum;
   }

   public void setOtherSum(Integer otherSum) {
	  this.otherSum = otherSum;
   }

   public String getShiftResult() {
	  return shiftResult;
   }

   public void setShiftResult(String shiftResult) {
	  this.shiftResult = shiftResult;
   }
}
