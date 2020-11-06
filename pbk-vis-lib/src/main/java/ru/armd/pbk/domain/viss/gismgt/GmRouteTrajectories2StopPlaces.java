package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.utils.StringUtils;

public class GmRouteTrajectories2StopPlaces
	extends GmBaseDomain {

   private Long routeTrajectoryMuid;
   private Long stopPlaceMuid;
   private Long modeMuid;
   private Long typeMuid;
   private Float lengthSector;
   private String comment;
   private Integer indx;
   private Integer indx2;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("routeTrajectoryMuid: ").append(StringUtils.nvl(getRouteTrajectoryMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopPlaceMuid: ").append(StringUtils.nvl(getStopPlaceMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("modeMuid: ").append(StringUtils.nvl(getModeMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("typeMuid: ").append(StringUtils.nvl(getTypeMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("lengthSector: ").append(StringUtils.nvl(getLengthSector(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("comment: ").append(StringUtils.nvl(getComment(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("indx: ").append(StringUtils.nvl(getIndx(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("indx2: ").append(StringUtils.nvl(getIndx2(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public Long getRouteTrajectoryMuid() {
	  return routeTrajectoryMuid;
   }

   public void setRouteTrajectoryMuid(Long routeTrajectoryMuid) {
	  this.routeTrajectoryMuid = routeTrajectoryMuid;
   }

   public Long getStopPlaceMuid() {
	  return stopPlaceMuid;
   }

   public void setStopPlaceMuid(Long stopPlaceMuid) {
	  this.stopPlaceMuid = stopPlaceMuid;
   }

   public Long getModeMuid() {
	  return modeMuid;
   }

   public void setModeMuid(Long modeMuid) {
	  this.modeMuid = modeMuid;
   }

   public Long getTypeMuid() {
	  return typeMuid;
   }

   public void setTypeMuid(Long typeMuid) {
	  this.typeMuid = typeMuid;
   }

   public Float getLengthSector() {
	  return lengthSector;
   }

   public void setLengthSector(Float lengthSector) {
	  this.lengthSector = lengthSector;
   }

   public String getComment() {
	  return comment;
   }

   public void setComment(String comment) {
	  this.comment = comment;
   }

   public Integer getIndx() {
	  return indx;
   }

   public void setIndx(Integer indx) {
	  this.indx = indx;
   }

   public Integer getIndx2() {
	  return indx2;
   }

   public void setIndx2(Integer indx2) {
	  this.indx2 = indx2;
   }

}
