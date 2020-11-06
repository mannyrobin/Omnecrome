package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.IHasCod;
import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен вместимость ТС.
 */
public class TsCapacity
	extends BaseDomain
	implements IHasCod, IHasName {

   private String name;
   private String description;
   private String cod;
   private Long tsTypeId;
   private Long gmCapacityId;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("cod: ").append(StringUtils.nvl(getCod(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(getDescription(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("tsTypeId: ").append(StringUtils.nvl(getTsTypeId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("gmCapacityId: ").append(StringUtils.nvl(getGmCapacityId(), FIELD_NULL)).append(FIELD_SEPARATOR);
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

   public Long getTsTypeId() {
	  return tsTypeId;
   }

   public void setTsTypeId(Long tsTypeId) {
	  this.tsTypeId = tsTypeId;
   }

   public Long getGmCapacityId() {
	  return gmCapacityId;
   }

   public void setGmCapacityId(Long gmCapacityId) {
	  this.gmCapacityId = gmCapacityId;
   }

}
