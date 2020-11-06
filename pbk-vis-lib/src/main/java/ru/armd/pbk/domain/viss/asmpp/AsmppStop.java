package ru.armd.pbk.domain.viss.asmpp;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен остановки АСМПП.
 */
public class AsmppStop
	extends BaseDomain {

   private Date workDate;
   private Integer routeId;
   private Integer routeNum;
   private Integer grafic;
   private String direction;
   private Integer stopSequence;
   private Integer stopId;
   private String stopName;
   private Date time;
   private Integer passengersincluded;
   private Integer passengersleft;
   private Integer transported;
   private Integer tripId;
   private Integer depotId;

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

   public Integer getRouteNum() {
	  return routeNum;
   }

   public void setRouteNum(Integer routeNum) {
	  this.routeNum = routeNum;
   }

   public Integer getGrafic() {
	  return grafic;
   }

   public void setGrafic(Integer grafic) {
	  this.grafic = grafic;
   }

   public String getDirection() {
	  return direction;
   }

   public void setDirection(String direction) {
	  this.direction = direction;
   }

   public Integer getStopSequence() {
	  return stopSequence;
   }

   public void setStopSequence(Integer stopSequence) {
	  this.stopSequence = stopSequence;
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


   public Integer getPassengersincluded() {
	  return passengersincluded;
   }

   public void setPassengersincluded(Integer passengersincluded) {
	  this.passengersincluded = passengersincluded;
   }

   public Integer getPassengersleft() {
	  return passengersleft;
   }

   public void setPassengersleft(Integer passengersleft) {
	  this.passengersleft = passengersleft;
   }

   public Integer getTransported() {
	  return transported;
   }

   public void setTransported(Integer transported) {
	  this.transported = transported;
   }

   public Integer getTripId() {
	  return tripId;
   }

   public void setTripId(Integer tripId) {
	  this.tripId = tripId;
   }

   public Integer getDepotId() {
	  return depotId;
   }

   public void setDepotId(Integer depotId) {
	  this.depotId = depotId;
   }

   public Date getTime() {
	  return time;
   }

   public void setTime(Date time) {
	  this.time = time;
   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AsmppStop asmppStop = (AsmppStop) o;

        if (workDate != null ? !workDate.equals(asmppStop.workDate) : asmppStop.workDate != null) return false;
        if (routeId != null ? !routeId.equals(asmppStop.routeId) : asmppStop.routeId != null) return false;
        if (routeNum != null ? !routeNum.equals(asmppStop.routeNum) : asmppStop.routeNum != null) return false;
        if (grafic != null ? !grafic.equals(asmppStop.grafic) : asmppStop.grafic != null) return false;
        if (direction != null ? !direction.equals(asmppStop.direction) : asmppStop.direction != null) return false;
        if (stopSequence != null ? !stopSequence.equals(asmppStop.stopSequence) : asmppStop.stopSequence != null)
            return false;
        if (stopId != null ? !stopId.equals(asmppStop.stopId) : asmppStop.stopId != null) return false;
        if (stopName != null ? !stopName.equals(asmppStop.stopName) : asmppStop.stopName != null) return false;
        if (time != null ? !time.equals(asmppStop.time) : asmppStop.time != null) return false;
        if (passengersincluded != null ? !passengersincluded.equals(asmppStop.passengersincluded) : asmppStop.passengersincluded != null)
            return false;
        if (passengersleft != null ? !passengersleft.equals(asmppStop.passengersleft) : asmppStop.passengersleft != null)
            return false;
        if (transported != null ? !transported.equals(asmppStop.transported) : asmppStop.transported != null)
            return false;
        if (tripId != null ? !tripId.equals(asmppStop.tripId) : asmppStop.tripId != null) return false;
        return depotId != null ? depotId.equals(asmppStop.depotId) : asmppStop.depotId == null;
    }

    @Override
    public int hashCode() {
        int result = workDate != null ? workDate.hashCode() : 0;
        result = 31 * result + (routeId != null ? routeId.hashCode() : 0);
        result = 31 * result + (routeNum != null ? routeNum.hashCode() : 0);
        result = 31 * result + (grafic != null ? grafic.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (stopSequence != null ? stopSequence.hashCode() : 0);
        result = 31 * result + (stopId != null ? stopId.hashCode() : 0);
        result = 31 * result + (stopName != null ? stopName.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (passengersincluded != null ? passengersincluded.hashCode() : 0);
        result = 31 * result + (passengersleft != null ? passengersleft.hashCode() : 0);
        result = 31 * result + (transported != null ? transported.hashCode() : 0);
        result = 31 * result + (tripId != null ? tripId.hashCode() : 0);
        result = 31 * result + (depotId != null ? depotId.hashCode() : 0);
        return result;
    }
}
