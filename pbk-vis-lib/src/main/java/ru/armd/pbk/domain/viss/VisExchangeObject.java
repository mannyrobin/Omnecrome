package ru.armd.pbk.domain.viss;

import ru.armd.pbk.core.domain.BaseDictionaryDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен типа взаимодействия ВИС.
 */
public class VisExchangeObject
	extends BaseDictionaryDomain {

   private Long visId;
   private String vis;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("visId: ").append(StringUtils.nvl(getVisId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("vis: ").append(StringUtils.nvl(getVis(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public Long getVisId() {
	  return visId;
   }

   public void setVisId(Long visId) {
	  this.visId = visId;
   }

   public String getVis() {
	  return vis;
   }

   public void setVis(String vis) {
	  this.vis = vis;
   }
}
