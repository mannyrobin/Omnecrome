package ru.armd.pbk.views.nsi.askppuskcheck;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import java.util.Date;

import static ru.armd.pbk.utils.StringUtils.binaryToMarkSymbol;

/**
 * View для отображения привязанных АСКП.
 */
public class AskpPuskCheckBindView
	extends BaseGridView {

   private Long id;

   private String ticketName;

   private String routeNumber;

   private String moveCode;

   private String routeCode;

   @JsonSerialize(using = AskpPuskCheckDateSerializer.class)
   private Date checkStartTime;

   @JsonSerialize(using = AskpPuskCheckDateSerializer.class)
   private Date checkEndTime;

   private Integer checkResult1Count;

   private Integer checkResult2Count;

   private Integer checkResult3Count;

   private String isBind;

   private String cardNumber;

   private String deptName;

   private String employeeName;

   @JsonSerialize(using = DotSepratedDateSerializer.class)
   private Date workDate;

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

   public String getIsBind() {
	  return isBind;
   }

   public void setIsBind(String isBind) {
	  this.isBind = binaryToMarkSymbol(isBind);
   }

   public String getRouteCode() {
	  return routeCode;
   }

   public void setRouteCode(String routeCode) {
	  this.routeCode = routeCode;
   }

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

   public String getDeptName() {
	  return deptName;
   }

   public void setDeptName(String deptName) {
	  this.deptName = deptName;
   }

   public String getEmployeeName() {
	  return employeeName;
   }

   public void setEmployeeName(String employeeName) {
	  this.employeeName = employeeName;
   }

}
