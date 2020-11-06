package ru.armd.pbk.domain.viss;

import ru.armd.pbk.core.IAuditable;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

/**
 * Журнальная запись о запросе и ответе в ВИС.
 */
public class VisExchangeResult
	extends BaseDomain
	implements IAuditable {

   private Long exchangeAttemptId;
   private Date resultDate;
   private Long exchangeStateId;
   private Long fileId;
   private String comment;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("exchangeAttemptId: ").append(StringUtils.nvl(getExchangeAttemptId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("resultDate: ").append(StringUtils.nvl(getResultDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("exchangeStateId: ").append(StringUtils.nvl(getExchangeStateId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("fileId: ").append(StringUtils.nvl(getFileId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("comment: ").append(StringUtils.nvl(getComment(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public Long getExchangeAttemptId() {
	  return exchangeAttemptId;
   }

   public void setExchangeAttemptId(Long exchangeAttemptId) {
	  this.exchangeAttemptId = exchangeAttemptId;
   }

   public Date getResultDate() {
	  return resultDate;
   }

   public void setResultDate(Date resultDate) {
	  this.resultDate = resultDate;
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
