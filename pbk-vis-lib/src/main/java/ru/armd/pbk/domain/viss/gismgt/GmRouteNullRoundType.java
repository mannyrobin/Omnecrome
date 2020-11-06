package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.core.IHasCod;
import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.utils.StringUtils;

public class GmRouteNullRoundType
	extends GmBaseDomain
	implements IHasName, IHasCod {

   private String cod;
   private String name;
   private String shortName;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("cod: ").append(StringUtils.nvl(getCod(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("shortName: ").append(StringUtils.nvl(getShortName(), FIELD_NULL)).append(FIELD_SEPARATOR);
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

   public String getShortName() {
	  return shortName;
   }

   public void setShortName(String shortName) {
	  this.shortName = shortName;
   }

   @Override
   public String getCod() {
	  return cod;
   }

   @Override
   public void setCod(String cod) {
	  this.cod = cod;
   }
}
