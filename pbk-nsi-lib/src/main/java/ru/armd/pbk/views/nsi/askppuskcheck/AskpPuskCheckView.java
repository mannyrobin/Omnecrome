package ru.armd.pbk.views.nsi.askppuskcheck;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;

import java.util.Date;

/**
 * View для данных из АСКП.
 */
public class AskpPuskCheckView
	extends BaseGridView {

   private Long id;

   private String ticketName;

   private String routeNumber;

   private String moveCode;

   @JsonSerialize(using = AskpPuskCheckDateSerializer.class)
   private Date checkStartTime;

   @JsonSerialize(using = AskpPuskCheckDateSerializer.class)
   private Date checkEndTime;

   private Integer checkResult1Count;

   private Integer checkResult2Count;

   private Integer checkResult3Count;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public String getTicketName() {
	  return ticketName;
   }

   public void setTicketName(String ticketName) {
	  this.ticketName = ticketName;
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
