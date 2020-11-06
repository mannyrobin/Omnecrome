package ru.armd.pbk.domain.nsi.askp;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

public class PuskCheck
	extends BaseDomain {

   private Long ticketId;
   private Long routeId;
   private String moveCode;
   private Date checkStartTime;
   private Date checkEndTime;
   private Integer checkResult1Count;
   private Integer checkResult2Count;
   private Integer checkResult3Count;

   public Long getTicketId() {
	  return ticketId;
   }

   public void setTicketId(Long ticketId) {
	  this.ticketId = ticketId;
   }

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

   public String getMoveCode() {
	  return moveCode;
   }

   public void setMoveCode(String moveCode) {
	  this.moveCode = moveCode;
   }

   public Date getCheckStartTime() {
	  return checkStartTime;
   }

   public void setCheckStartTime(Date checkStartTime) {
	  this.checkStartTime = checkStartTime;
   }

   public Date getCheckEndTime() {
	  return checkEndTime;
   }

   public void setCheckEndTime(Date checkEndTime) {
	  this.checkEndTime = checkEndTime;
   }

   public Integer getCheckResult1Count() {
	  return checkResult1Count;
   }

   public void setCheckResult1Count(Integer checkResult1Count) {
	  this.checkResult1Count = checkResult1Count;
   }

   public Integer getCheckResult2Count() {
	  return checkResult2Count;
   }

   public void setCheckResult2Count(Integer checkResult2Count) {
	  this.checkResult2Count = checkResult2Count;
   }

   public Integer getCheckResult3Count() {
	  return checkResult3Count;
   }

   public void setCheckResult3Count(Integer checkResult3Count) {
	  this.checkResult3Count = checkResult3Count;
   }

}
