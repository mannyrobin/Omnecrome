package ru.armd.pbk.views.unionanalysis;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSepratedTimeSerializer;

import java.util.Date;

public class UnionAnalysisByTripMoveRouteView
	extends BaseGridView {

   private Integer asduRouteId;
   private Integer grafic;
   private Integer tripId;
   @JsonSerialize(using = DotSepratedTimeSerializer.class)
   private Date startTripTime;
   private Integer asmppPassengersIncludedCount;
   private Integer asmppPassengersLeftCount;
   private Integer asmppPassengersTransportedCount;
   private Integer askpPassengersCount;
   private Integer tripNum;

   public Integer getAsduRouteId() {
	  return asduRouteId;
   }

   public void setAsduRouteId(Integer asduRouteId) {
	  this.asduRouteId = asduRouteId;
   }

   public Integer getGrafic() {
	  return grafic;
   }

   public void setGrafic(Integer grafic) {
	  this.grafic = grafic;
   }

   public Integer getTripId() {
	  return tripId;
   }

   public void setTripId(Integer tripId) {
	  this.tripId = tripId;
   }

   public Date getStartTripTime() {
	  return startTripTime;
   }

   public void setStartTripTime(Date startTripTime) {
	  this.startTripTime = startTripTime;
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

   public Integer getTripNum() {
	  return tripNum;
   }

   public void setTripNum(Integer tripNum) {
	  this.tripNum = tripNum;
   }

}
