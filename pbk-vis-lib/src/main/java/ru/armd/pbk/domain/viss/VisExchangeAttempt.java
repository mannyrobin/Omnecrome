package ru.armd.pbk.domain.viss;

import ru.armd.pbk.core.IAuditable;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

/**
 * Журнальная запись о запросе и ответе в ВИС.
 */
public class VisExchangeAttempt
	extends BaseDomain
	implements IAuditable {

   private Long exchangeId;
   private Date attemptDate;
   private Long exchangeStateId;
   private Long fileId;
   private String comment;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("exchangeId: ").append(StringUtils.nvl(getExchangeId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("attemptDate: ").append(StringUtils.nvl(getAttemptDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("exchangeStateId: ").append(StringUtils.nvl(getExchangeStateId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("fileId: ").append(StringUtils.nvl(getFileId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("comment: ").append(StringUtils.nvl(getComment(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public Long getExchangeId() {
	  return exchangeId;
   }

   public void setExchangeId(Long exchangeId) {
	  this.exchangeId = exchangeId;
   }

   public Date getAttemptDate() {
	  return attemptDate;
   }

   public void setAttemptDate(Date attemptDate) {
	  this.attemptDate = attemptDate;
   }

   public Long getExchangeStateId() {
	  return exchangeStateId;
   }

   public void setExchangeStateId(Long exchangeStateId) {
	  this.exchangeStateId = exchangeStateId;
   }

   public Long getFileId() {
	  return fileId;
   }

   public void setFileId(Long fileId) {
	  this.fileId = fileId;
   }

   public String getComment() {
	  return comment;
   }

   public void setComment(String comment) {
	  this.comment = comment;
   }
}
