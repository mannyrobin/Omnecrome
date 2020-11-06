package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.IHasCod;
import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен Типы ТС.
 */
public class TsType
	extends BaseDomain
	implements IHasCod, IHasName {

   private String name;
   private String description;
   private String cod;
   private Long tsKindId;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("cod: ").append(StringUtils.nvl(getCod(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(getDescription(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("tsKindId: ").append(StringUtils.nvl(getTsKindId(), FIELD_NULL)).append(FIELD_SEPARATOR);
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

   @Override
   public String getCod() {
	  return cod;
   }

   @Override
   public void setCod(String cod) {
	  this.cod = cod;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Long getTsKindId() {
	  return tsKindId;
   }

   public void setTsKindId(Long tsKindId) {
	  this.tsKindId = tsKindId;
   }

}
