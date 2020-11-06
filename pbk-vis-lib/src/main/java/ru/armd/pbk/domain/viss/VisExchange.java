package ru.armd.pbk.domain.viss;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

/**
 * Домен журнальной записи домена.
 */
public class VisExchange
	extends BaseDomain {

   private Long configId;
   private Long stateId;
   private Date workDate;
   private Integer isDayFail;
   private String errorContent;
   private String parameter;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("configId: ").append(StringUtils.nvl(getConfigId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stateId: ").append(StringUtils.nvl(getStateId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("workDate: ").append(StringUtils.nvl(getWorkDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("isDayFail: ").append(StringUtils.nvl(getIsDayFail(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("errorContent: ").append(StringUtils.nvl(getErrorContent(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("parameter: ").append(StringUtils.nvl(getParameter(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public Long getConfigId() {
	  return configId;
   }

   public void setConfigId(Long configId) {
	  this.configId = configId;
   }

   public Long getStateId() {
	  return stateId;
   }

   public void setStateId(Long stateId) {
	  this.stateId = stateId;
   }

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public Integer getIsDayFail() {
	  return isDayFail;
   }

   public void setIsDayFail(Integer isDayFail) {
	  this.isDayFail = isDayFail;
   }

   public String getErrorContent() {
	  return errorContent;
   }

   public void setErrorContent(String errorContent) {
	  this.errorContent = errorContent;
   }

   public String getParameter() {
	  return parameter;
   }

   public void setParameter(String parameter) {
	  this.parameter = parameter;
   }
}
