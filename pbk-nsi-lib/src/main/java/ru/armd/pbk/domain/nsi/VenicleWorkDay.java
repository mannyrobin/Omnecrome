package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

public class VenicleWorkDay
	extends BaseDomain {

   private Long venicleId;
   private Date workDay;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("venicleId: ").append(StringUtils.nvl(getVenicleId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("workDay: ").append(StringUtils.nvl(getWorkDay(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public Long getVenicleId() {
	  return venicleId;
   }

   public void setVenicleId(Long venicleId) {
	  this.venicleId = venicleId;
   }

   public Date getWorkDay() {
	  return workDay;
   }

   public void setWorkDay(Date workDay) {
	  this.workDay = workDay;
   }

}
