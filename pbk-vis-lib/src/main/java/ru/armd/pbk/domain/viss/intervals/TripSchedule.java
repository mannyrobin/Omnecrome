package ru.armd.pbk.domain.viss.intervals;


/**
 * Класс TripSchedule.
 */
public class TripSchedule {

   private String routeCode;
   private String moveCode;
   private String depotNumber;
   private Long shiftNum;
   private Long routeNum;
   private String startTime;
   private String endTime;

   public String getRouteCode() {
	  return routeCode;
   }

   public void setRouteCode(String routeCode) {
	  this.routeCode = routeCode;
   }

   public String getMoveCode() {
	  return moveCode;
   }

   public void setMoveCode(String moveCode) {
	  this.moveCode = moveCode;
   }

   public String getDepotNumber() {
	  return depotNumber;
   }

   public void setDepotNumber(String depotNumber) {
	  this.depotNumber = depotNumber;
   }

   public Long getShiftNum() {
	  return shiftNum;
   }

   public void setShiftNum(Long shiftNum) {
	  this.shiftNum = shiftNum;
   }

   public Long getRouteNum() {
	  return routeNum;
   }

   public void setRouteNum(Long routeNum) {
	  this.routeNum = routeNum;
   }

   public String getStartTime() {
	  return startTime;
   }

   public void setStartTime(String startTime) {
	  this.startTime = startTime;
   }

   public String getEndTime() {
	  return endTime;
   }

   public void setEndTime(String endTime) {
	  this.endTime = endTime;
   }
}
