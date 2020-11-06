package ru.armd.pbk.domain.dvr;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен для периуда хранения записи с видеорегистратора.
 */
public class DvrStorePeriod
	extends BaseDomain {

   private String name;
   private Long dvrId;
   private String description;

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Long getDvrId() {
	  return dvrId;
   }

   public void setDvrId(Long dvrId) {
	  this.dvrId = dvrId;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("dvrId: ").append(StringUtils.nvl(getDvrId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(getDescription(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

}
