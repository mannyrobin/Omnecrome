package ru.armd.pbk.domain.core;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Домен результата очистки логов аудита.
 */
public class AuditLog
	extends BaseDomain {

   private Date startDate;
   private Date endDate;
   private Long logStateId;
   private Date toDate;
   private String message;

   public Date getStartDate() {
	  return startDate;
   }

   public void setStartDate(Date startDate) {
	  this.startDate = startDate;
   }

   public Date getEndDate() {
	  return endDate;
   }

   public void setEndDate(Date endDate) {
	  this.endDate = endDate;
   }

   public String getMessage() {
	  return message;
   }

   public void setMessage(String message) {
	  this.message = message;
   }

   public Long getLogStateId() {
	  return logStateId;
   }

   public void setLogStateId(Long logStateId) {
	  this.logStateId = logStateId;
   }

   public Date getToDate() {
	  return toDate;
   }

   public void setToDate(Date toDate) {
	  this.toDate = toDate;
   }

}
