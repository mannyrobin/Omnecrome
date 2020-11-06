package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.utils.StringUtils;

public class GmRouteTrajectory
	extends GmBaseDomain {

   private String wktGeom;
   private Long routeRoundMuid;
   private Long routeNullRoundMuid;
   private Long trajectoryTypeMuid;
   private Float length;
   private Float fixedLength;
   private String specification;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("wktGeom: ").append(StringUtils.nvl(getWktGeom(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("routeRoundMuid: ").append(StringUtils.nvl(getRouteRoundMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("routeNullRoundMuid: ").append(StringUtils.nvl(getRouteNullRoundMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("trajectoryTypeMuid: ").append(StringUtils.nvl(getTrajectoryTypeMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("length: ").append(StringUtils.nvl(getLength(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("fixedLength: ").append(StringUtils.nvl(getFixedLength(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("specification: ").append(StringUtils.nvl(getSpecification(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public String getWktGeom() {
	  return wktGeom;
   }

   public void setWktGeom(String wktGeom) {
	  this.wktGeom = wktGeom;
   }

   public Long getRouteRoundMuid() {
	  return routeRoundMuid;
   }

   public void setRouteRoundMuid(Long routeRoundMuid) {
	  this.routeRoundMuid = routeRoundMuid;
   }

   public Long getRouteNullRoundMuid() {
	  return routeNullRoundMuid;
   }

   public void setRouteNullRoundMuid(Long routeNullRoundMuid) {
	  this.routeNullRoundMuid = routeNullRoundMuid;
   }

   public Long getTrajectoryTypeMuid() {
	  return trajectoryTypeMuid;
   }

   public void setTrajectoryTypeMuid(Long trajectoryTypeMuid) {
	  this.trajectoryTypeMuid = trajectoryTypeMuid;
   }

   public Float getLength() {
	  return length;
   }

   public void setLength(Float length) {
	  this.length = length;
   }

   public Float getFixedLength() {
	  return fixedLength;
   }

   public void setFixedLength(Float fixedLength) {
	  this.fixedLength = fixedLength;
   }

   public String getSpecification() {
	  return specification;
   }

   public void setSpecification(String specification) {
	  this.specification = specification;
   }

}
