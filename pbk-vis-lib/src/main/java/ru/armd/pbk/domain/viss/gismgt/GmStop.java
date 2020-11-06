package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.utils.StringUtils;

public class GmStop
	extends GmBaseDomain
	implements IHasName {

   private String name;
   private String nameInEnglish;
   private String nameFormTerminalPoint;
   private Long streetMuid;
   private Long directionMuid;
   private Long districtMuid;
   private Long regionMuid;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("nameInEnglish: ").append(StringUtils.nvl(getNameInEnglish(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("nameFormTerminalPoint: ").append(StringUtils.nvl(getNameFormTerminalPoint(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("directionMuid: ").append(StringUtils.nvl(getDirectionMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("districtMuid: ").append(StringUtils.nvl(getDistrictMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("regionMuid: ").append(StringUtils.nvl(getRegionMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
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

   public String getNameInEnglish() {
	  return nameInEnglish;
   }

   public void setNameInEnglish(String nameInEnglish) {
	  this.nameInEnglish = nameInEnglish;
   }

   public Long getStreetMuid() {
	  return streetMuid;
   }

   public void setStreetMuid(Long streetMuid) {
	  this.streetMuid = streetMuid;
   }

   public Long getDirectionMuid() {
	  return directionMuid;
   }

   public void setDirectionMuid(Long directionMuid) {
	  this.directionMuid = directionMuid;
   }

   public Long getDistrictMuid() {
	  return districtMuid;
   }

   public void setDistrictMuid(Long districtMuid) {
	  this.districtMuid = districtMuid;
   }

   public Long getRegionMuid() {
	  return regionMuid;
   }

   public void setRegionMuid(Long regionMuid) {
	  this.regionMuid = regionMuid;
   }

   public String getNameFormTerminalPoint() {
	  return nameFormTerminalPoint;
   }

   public void setNameFormTerminalPoint(String nameFormTerminalPoint) {
	  this.nameFormTerminalPoint = nameFormTerminalPoint;
   }

}
