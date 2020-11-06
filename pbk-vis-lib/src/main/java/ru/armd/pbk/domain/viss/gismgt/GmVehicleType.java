package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.utils.StringUtils;

public class GmVehicleType
	extends GmBaseDomain
	implements IHasName {

   private String name;
   private String shortName;
   private String model;
   private Long routeTransportKindMuid;
   private Long seatingCapacity;
   private Long maxCapacity;
   private Float effectiveArea;
   private Float fullLoadWeight;
   private String dimension;
   private Integer hasFacilitiesForDisabled;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("shortName: ").append(StringUtils.nvl(getShortName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("model: ").append(StringUtils.nvl(getModel(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("routeTransportKindMuid: ").append(StringUtils.nvl(getRouteTransportKindMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("seatingCapacity: ").append(StringUtils.nvl(getSeatingCapacity(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("maxCapacity: ").append(StringUtils.nvl(getMaxCapacity(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("effectiveArea: ").append(StringUtils.nvl(getEffectiveArea(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("fullLoadWeight: ").append(StringUtils.nvl(getFullLoadWeight(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("dimension: ").append(StringUtils.nvl(getDimension(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("hasFacilitiesForDisabled: ").append(StringUtils.nvl(getHasFacilitiesForDisabled(), FIELD_NULL)).append(FIELD_SEPARATOR);
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

   public String getModel() {
	  return model;
   }

   public void setModel(String model) {
	  this.model = model;
   }

   public Long getRouteTransportKindMuid() {
	  return routeTransportKindMuid;
   }

   public void setRouteTransportKindMuid(Long routeTransportKindMuid) {
	  this.routeTransportKindMuid = routeTransportKindMuid;
   }

   public Long getSeatingCapacity() {
	  return seatingCapacity;
   }

   public void setSeatingCapacity(Long seatingCapacity) {
	  this.seatingCapacity = seatingCapacity;
   }

   public Long getMaxCapacity() {
	  return maxCapacity;
   }

   public void setMaxCapacity(Long maxCapacity) {
	  this.maxCapacity = maxCapacity;
   }

   public Float getEffectiveArea() {
	  return effectiveArea;
   }

   public void setEffectiveArea(Float effectiveArea) {
	  this.effectiveArea = effectiveArea;
   }

   public Float getFullLoadWeight() {
	  return fullLoadWeight;
   }

   public void setFullLoadWeight(Float fullLoadWeight) {
	  this.fullLoadWeight = fullLoadWeight;
   }

   public String getDimension() {
	  return dimension;
   }

   public void setDimension(String dimension) {
	  this.dimension = dimension;
   }

   public Integer getHasFacilitiesForDisabled() {
	  return hasFacilitiesForDisabled;
   }

   public void setHasFacilitiesForDisabled(Integer hasFacilitiesForDisabled) {
	  this.hasFacilitiesForDisabled = hasFacilitiesForDisabled;
   }

}
