package ru.armd.pbk.domain.nsi.stop;

import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен остановочных пунктов.
 */
public class Stop
	extends BaseVersionDomain
	implements IHasName {

   private String name;
   private Long gmStopId;
   private Long asduStopId;
   private String routeNames;
   private String nameWithoutDistrict;

   @Override
   public String getName() {
	  return name;
   }

   @Override
   public void setName(String name) {
	  this.name = name;
   }

   public Long getGmStopId() {
	  return gmStopId;
   }

   public void setGmStopId(Long gmStopId) {
	  this.gmStopId = gmStopId;
   }

   public Long getAsduStopId() {
	  return asduStopId;
   }

   public void setAsduStopId(Long asduStopId) {
	  this.asduStopId = asduStopId;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("gmStopId: ").append(StringUtils.nvl(getGmStopId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("asduStopId: ").append(StringUtils.nvl(getAsduStopId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public String getRouteNames() {
	  return routeNames;
   }

   public void setRouteNames(String routeNames) {
	  this.routeNames = routeNames;
   }

   public String getNameWithoutDistrict() {
	  return nameWithoutDistrict;
   }

   public void setNameWithoutDistrict(String nameWithoutDistrict) {
	  this.nameWithoutDistrict = nameWithoutDistrict;
   }
}
