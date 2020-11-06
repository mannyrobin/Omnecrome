package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseDictionaryDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен пола.
 *
 * @author nikita_chebotaryov
 */
public class Sex
	extends BaseDictionaryDomain {
   private String name;
   private String description;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(getDescription(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
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

}
