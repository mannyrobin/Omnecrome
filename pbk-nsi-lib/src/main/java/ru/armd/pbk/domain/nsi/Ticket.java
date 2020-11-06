package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен билета.
 *
 * @author nikita_lobanov
 */
public class Ticket
	extends BaseVersionDomain {

   private String code;
   private String name;
   private String description;
   private Integer ticketTypeId;
   private String applicationCode;
   private Boolean useInAnalysis;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("code: ").append(StringUtils.nvl(getCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(getDescription(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("ticketTypeId: ").append(StringUtils.nvl(getTicketTypeId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("applicationCode: ").append(StringUtils.nvl(getApplicationCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public String getCode() {
	  return code;
   }

   public void setCode(String code) {
	  this.code = code;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public String getApplicationCode() {
	  return applicationCode;
   }

   public void setApplicationCode(String applicationCode) {
	  this.applicationCode = applicationCode;
   }

   public Integer getTicketTypeId() {
	  return ticketTypeId;
   }

   public void setTicketTypeId(Integer ticketTypeId) {
	  this.ticketTypeId = ticketTypeId;
   }

   public Boolean getUseInAnalysis() {
	  return useInAnalysis;
   }

   public void setUseInAnalysis(Boolean useInAnalysis) {
	  this.useInAnalysis = useInAnalysis;
   }
}
