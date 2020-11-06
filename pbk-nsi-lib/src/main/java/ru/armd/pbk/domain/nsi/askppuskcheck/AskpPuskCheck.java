package ru.armd.pbk.domain.nsi.askppuskcheck;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

/**
 * Домен данных от АСКП.
 */
public class AskpPuskCheck
	extends BaseDomain {

   private Date workDate;
   private Long ticketId;
   private Long routeId;
   private String moveCode;
   private Date checkStartTime;
   private String askpRouteCode;
   private Date checkEndTime;
   private Integer checkResult1Count;
   private Integer checkResult2Count;
   private Integer checkResult3Count;
   private Long uid;
   private String cardNumber;
   private Long taskId;

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

   public Long getUid() {
	  return uid;
   }

   public void setUid(Long uid) {
	  this.uid = uid;
   }

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public String getAskpRouteCode() {
	  return askpRouteCode;
   }

   public void setAskpRouteCode(String askpRouteCode) {
	  this.askpRouteCode = askpRouteCode;
   }


   public String getCardNumber() {
	  return cardNumber;
   }

   public void setCardNumber(String cardNumber) {
	  this.cardNumber = cardNumber;
   }

   public Long getTaskId() {
	  return taskId;
   }

   public void setTaskId(Long taskId) {
	  this.taskId = taskId;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("workDate: ").append(StringUtils.nvl(getWorkDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("ticketId: ").append(StringUtils.nvl(getTicketId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("routeId: ").append(StringUtils.nvl(getRouteId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("moveCode: ").append(StringUtils.nvl(getMoveCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("checkStartTime: ").append(StringUtils.nvl(getCheckStartTime(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("askpRouteCode: ").append(StringUtils.nvl(getAskpRouteCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("checkEndTime: ").append(StringUtils.nvl(getCheckEndTime(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("checkResult1Count: ").append(StringUtils.nvl(getCheckResult1Count(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("checkResult2Count: ").append(StringUtils.nvl(getCheckResult2Count(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("checkResult3Count: ").append(StringUtils.nvl(getCheckResult3Count(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("uid: ").append(StringUtils.nvl(getUid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("cardNumber: ").append(StringUtils.nvl(getCardNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("taskId: ").append(StringUtils.nvl(getTaskId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
