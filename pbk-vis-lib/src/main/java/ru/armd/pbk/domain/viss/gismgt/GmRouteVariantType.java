package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.utils.StringUtils;

public class GmRouteVariantType
	extends GmBaseDomain
	implements IHasName {

   private String name;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   @Override
   public String getName() {
	  return name;
   }

   @Override
   public void setName(String name) {
	  this.name = name;
   }

}
