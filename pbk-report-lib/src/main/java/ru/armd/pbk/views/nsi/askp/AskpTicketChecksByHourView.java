package ru.armd.pbk.views.nsi.askp;

import ru.armd.pbk.core.views.BaseGridView;

import java.util.Date;

/**
 * View для данных из АСКП.
 */
public class AskpTicketChecksByHourView
	extends BaseGridView {

   private Long routeId;
   private String routeNumber;
   private String moveCode;
   private Date workDate;
   private Integer hour;
   private Integer checks;
   private String routeName;
   private Boolean isHasTelematics;
   private Boolean isHasAsdu;
   private String depotNumber;

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public String getRouteNumber() {
	  return routeNumber;
   }

   public void setRouteNumber(String routeNumber) {
	  this.routeNumber = routeNumber;
   }

   public String getMoveCode() {
	  return moveCode;
   }

   public void setMoveCode(String moveCode) {
	  this.moveCode = moveCode;
   }

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
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

   public String getRouteName() {
	  return routeName;
   }

   public void setRouteName(String routeName) {
	  this.routeName = routeName;
   }

   public Boolean getIsHasTelematics() {
	  return isHasTelematics;
   }

   public void setIsHasTelematics(Boolean isHasTelematics) {
	  this.isHasTelematics = isHasTelematics;
   }

   public Boolean getIsHasAsdu() {
	  return isHasAsdu;
   }

   public void setIsHasAsdu(Boolean isHasAsdu) {
	  this.isHasAsdu = isHasAsdu;
   }

   public String getDepotNumber() {
	  return depotNumber;
   }

   public void setDepotNumber(String depotNumber) {
	  this.depotNumber = depotNumber;
   }

}
