package ru.armd.pbk.domain.nsi.district;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен района.
 */
public class District
	extends BaseVersionDomain {

   private Long countyId;
   private Long gmRegionId;
   private String name;
   private String description;
   private String cod;
   private Long planCountyId;

   public Long getCountyId() {
	  return countyId;
   }

   public void setCountyId(Long countyId) {
	  this.countyId = countyId;
   }

   public Long getGmRegionId() {
	  return gmRegionId;
   }

   public void setGmRegionId(Long gmRegionId) {
	  this.gmRegionId = gmRegionId;
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

   public String getCod() {
	  return cod;
   }

   public void setCod(String cod) {
	  this.cod = cod;
   }

   public Long getPlanCountyId() {
	  return planCountyId;
   }

   public void setPlanCountyId(Long planCountyId) {
	  this.planCountyId = planCountyId;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(getDescription(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("countyId: ").append(StringUtils.nvl(getCountyId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("gmRegionId: ").append(StringUtils.nvl(getGmRegionId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("cod: ").append(StringUtils.nvl(getCod(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("planCountyId: ").append(StringUtils.nvl(getPlanCountyId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
