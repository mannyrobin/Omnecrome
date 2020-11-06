package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.utils.StringUtils;

public class GmDistrictEgko
	extends GmBaseDomain {

   private String name;
   private String caption;
   private String wktGeom;
   private Long districtMuid;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("caption: ").append(StringUtils.nvl(getCaption(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("wktGeom: ").append(StringUtils.nvl(getWktGeom(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("districtMuid: ").append(StringUtils.nvl(getDistrictMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getCaption() {
	  return caption;
   }

   public void setCaption(String caption) {
	  this.caption = caption;
   }

   public String getWktGeom() {
	  return wktGeom;
   }

   public void setWktGeom(String wktGeom) {
	  this.wktGeom = wktGeom;
   }

   public Long getDistrictMuid() {
	  return districtMuid;
   }

   public void setDistrictMuid(Long districtMuid) {
	  this.districtMuid = districtMuid;
   }

}
