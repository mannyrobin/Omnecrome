package ru.armd.pbk.domain.viss.asdu;

import ru.armd.pbk.core.IHasId;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен остановочных пунктов.
 */
public class AsduTrip
	extends BaseDomain
	implements IHasId {
   Long asduTripId;
   Long asduRouteId;
   Long serviceId;
   String tripHeadsign;
   String tripShortName;
   Long directionId;
   Long blockId;
   Long shapeId;
   String wheelchairAccessible;
   String routeVar;

   public Long getAsduTripId() {
	  return asduTripId;
   }

   public void setAsduTripId(Long asduTripId) {
	  this.asduTripId = asduTripId;
   }

   public Long getAsduRouteId() {
	  return asduRouteId;
   }

   public void setAsduRouteId(Long asduRouteId) {
	  this.asduRouteId = asduRouteId;
   }

   public Long getServiceId() {
	  return serviceId;
   }

   public void setServiceId(Long serviceId) {
	  this.serviceId = serviceId;
   }

   public String getTripHeadsign() {
	  return tripHeadsign;
   }

   public void setTripHeadsign(String tripHeadsign) {
	  this.tripHeadsign = tripHeadsign;
   }

   public String getTripShortName() {
	  return tripShortName;
   }

   public void setTripShortName(String tripShortName) {
	  this.tripShortName = tripShortName;
   }

   public Long getDirectionId() {
	  return directionId;
   }

   public void setDirectionId(Long directionId) {
	  this.directionId = directionId;
   }

   public Long getBlockId() {
	  return blockId;
   }

   public void setBlockId(Long blockId) {
	  this.blockId = blockId;
   }

   public Long getShapeId() {
	  return shapeId;
   }

   public void setShapeId(Long shapeId) {
	  this.shapeId = shapeId;
   }

   public String getWheelchairAccessible() {
	  return wheelchairAccessible;
   }

   public void setWheelchairAccessible(String wheelchairAccessible) {
	  this.wheelchairAccessible = wheelchairAccessible;
   }

   public String getRouteVar() {
	  return routeVar;
   }

   public void setRouteVar(String routeVar) {
	  this.routeVar = routeVar;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("asduTripId: ").append(StringUtils.nvl(getAsduTripId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("asduRouteId: ").append(StringUtils.nvl(getAsduRouteId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("serviceId: ").append(StringUtils.nvl(getServiceId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("tripHeadsign: ").append(StringUtils.nvl(getTripHeadsign(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("tripShortName: ").append(StringUtils.nvl(getTripShortName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("directionId: ").append(StringUtils.nvl(getDirectionId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("blockId: ").append(StringUtils.nvl(getBlockId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("shapeId: ").append(StringUtils.nvl(getShapeId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("wheelchairAccessible: ").append(StringUtils.nvl(getWheelchairAccessible(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("routeVar: ").append(StringUtils.nvl(getRouteVar(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
