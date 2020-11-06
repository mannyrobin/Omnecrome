package ru.armd.pbk.domain.tasks;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен БСК из АСКП.
 */
public class AskpContactlessCard
	extends BaseDomain {

   private Date workDate;
   private String cardNumber;
   private Date checkTime;
   private String parkName;
   private String routeNumber;
   private String moveCode;
   private Long taskId;
   private Long cardId;
   private Long routeId;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public String getCardNumber() {
	  return cardNumber;
   }

   public void setCardNumber(String cardNumber) {
	  this.cardNumber = cardNumber;
   }

   public Date getCheckTime() {
	  return checkTime;
   }

   public void setCheckTime(Date checkTime) {
	  this.checkTime = checkTime;
   }

   public String getParkName() {
	  return parkName;
   }

   public void setParkName(String parkName) {
	  this.parkName = parkName;
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

   public Long getTaskId() {
	  return taskId;
   }

   public void setTaskId(Long taskId) {
	  this.taskId = taskId;
   }

   public Long getCardId() {
	  return cardId;
   }

   public void setCardId(Long cardId) {
	  this.cardId = cardId;
   }

   public Long getRouteId() {
	  return routeId;
   }

   public void setRouteId(Long routeId) {
	  this.routeId = routeId;
   }

}
