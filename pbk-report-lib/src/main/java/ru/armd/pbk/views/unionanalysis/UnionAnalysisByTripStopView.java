package ru.armd.pbk.views.unionanalysis;

import ru.armd.pbk.core.views.BaseGridView;

public class UnionAnalysisByTripStopView
	extends BaseGridView {

   private Integer orderNum;
   private String stopName;
   private Integer asmppPassengersIncludedCount;
   private Integer asmppPassengersLeftCount;
   private Integer asmppPassengersTransportedCount;
   private Integer askpPassengersCount;

   public Integer getOrderNum() {
	  return orderNum;
   }

   public void setOrderNum(Integer orderNum) {
	  this.orderNum = orderNum;
   }

   public String getStopName() {
	  return stopName;
   }

   public void setStopName(String stopName) {
	  this.stopName = stopName;
   }

   public Integer getAsmppPassengersIncludedCount() {
	  return asmppPassengersIncludedCount;
   }

   public void setAsmppPassengersIncludedCount(Integer asmppPassengersIncludedCount) {
	  this.asmppPassengersIncludedCount = asmppPassengersIncludedCount;
   }

   public Integer getAsmppPassengersLeftCount() {
	  return asmppPassengersLeftCount;
   }

   public void setAsmppPassengersLeftCount(Integer asmppPassengersLeftCount) {
	  this.asmppPassengersLeftCount = asmppPassengersLeftCount;
   }

   public Integer getAsmppPassengersTransportedCount() {
	  return asmppPassengersTransportedCount;
   }

   public void setAsmppPassengersTransportedCount(Integer asmppPassengersTransportedCount) {
	  this.asmppPassengersTransportedCount = asmppPassengersTransportedCount;
   }

   public Integer getAskpPassengersCount() {
	  return askpPassengersCount;
   }

   public void setAskpPassengersCount(Integer askpPassengersCount) {
	  this.askpPassengersCount = askpPassengersCount;
   }

}
