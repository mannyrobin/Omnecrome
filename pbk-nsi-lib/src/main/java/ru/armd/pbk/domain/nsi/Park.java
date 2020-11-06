package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен парков.
 */
public class Park
	extends BaseVersionDomain {

   private String shortName;
   private String name;
   private Integer parkNumber;
   private Long tsKindId;
   private Long gmParkId;
   private String asduDepotId;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("shortName: ").append(StringUtils.nvl(getShortName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("parkNumber: ").append(StringUtils.nvl(getParkNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("tsKindId: ").append(StringUtils.nvl(getTsKindId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("gmParkId: ").append(StringUtils.nvl(getGmParkId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("asduDepotId: ").append(StringUtils.nvl(getAsduDepotId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public String getShortName() {
	  return shortName;
   }

   public void setShortName(String shortName) {
	  this.shortName = shortName;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Integer getParkNumber() {
	  return parkNumber;
   }

   public void setParkNumber(Integer parkNumber) {
	  this.parkNumber = parkNumber;
   }

   public Long getTsKindId() {
	  return tsKindId;
   }

   public void setTsKindId(Long tsKindId) {
	  this.tsKindId = tsKindId;
   }

   public Long getGmParkId() {
	  return gmParkId;
   }

   public void setGmParkId(Long gmParkId) {
	  this.gmParkId = gmParkId;
   }

   public String getAsduDepotId() {
	  return asduDepotId;
   }

   public void setAsduDepotId(String asduDepotId) {
	  this.asduDepotId = asduDepotId;
   }

}
