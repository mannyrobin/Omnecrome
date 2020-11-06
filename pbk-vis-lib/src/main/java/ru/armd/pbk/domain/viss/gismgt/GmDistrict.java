package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.utils.StringUtils;

public class GmDistrict
	extends GmBaseDomain {

   private String omkCode;
   private String name;
   private String shortName;
   private String translitName;
   private String territorialUnitTypeCode;
   private String okatoCode;

   public String getOmkCode() {
	  return omkCode;
   }

   public void setOmkCode(String omkCode) {
	  this.omkCode = omkCode;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getShortName() {
	  return shortName;
   }

   public void setShortName(String shortName) {
	  this.shortName = shortName;
   }

   public String getTranslitName() {
	  return translitName;
   }

   public void setTranslitName(String translitName) {
	  this.translitName = translitName;
   }

   public String getTerritorialUnitTypeCode() {
	  return territorialUnitTypeCode;
   }

   public void setTerritorialUnitTypeCode(String territorialUnitTypeCode) {
	  this.territorialUnitTypeCode = territorialUnitTypeCode;
   }

   public String getOkatoCode() {
	  return okatoCode;
   }

   public void setOkatoCode(String okatoCode) {
	  this.okatoCode = okatoCode;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("omkCode: ").append(StringUtils.nvl(getOmkCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("shortName: ").append(StringUtils.nvl(getShortName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("translitName: ").append(StringUtils.nvl(getTranslitName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("territorialUnitTypeCode: ").append(StringUtils.nvl(getTerritorialUnitTypeCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("okatoCode: ").append(StringUtils.nvl(getOkatoCode(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
