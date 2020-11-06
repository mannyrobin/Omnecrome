package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.core.IHasCod;
import ru.armd.pbk.utils.StringUtils;

public class GmRouteRound
	extends GmBaseDomain
	implements IHasCod {

   private String cod;
   private Long routeVariantMuid;
   private Long typeMuid;
   private Long terminalPointZoneAMuid;
   private Long terminalPointZoneBMuid;
   private Long terminalStationAMuid;
   private Long terminalStationBMuid;
   private Long terminalStationCMuid;
   private Long stopPlaceAMuid;
   private Long stopPlaceBMuid;
   private Float averageLength;
   private Float averageLengthFixed;
   private String specification1;
   private String specification2;
   private String comment;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("cod: ").append(StringUtils.nvl(getCod(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("routeVariantMuid: ").append(StringUtils.nvl(getRouteVariantMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("typeMuid: ").append(StringUtils.nvl(getTypeMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("terminalPointZoneAMuid: ").append(StringUtils.nvl(getTerminalPointZoneAMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("terminalPointZoneBMuid: ").append(StringUtils.nvl(getTerminalPointZoneBMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("terminalStationAMuid: ").append(StringUtils.nvl(getTerminalStationAMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("terminalStationBMuid: ").append(StringUtils.nvl(getTerminalStationBMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("terminalStationCMuid: ").append(StringUtils.nvl(getTerminalStationCMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopPlaceAMuid: ").append(StringUtils.nvl(getStopPlaceAMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopPlaceBMuid: ").append(StringUtils.nvl(getStopPlaceBMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("averageLength: ").append(StringUtils.nvl(getAverageLength(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("averageLengthFixed: ").append(StringUtils.nvl(getAverageLengthFixed(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("specification1: ").append(StringUtils.nvl(getSpecification1(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("specification2: ").append(StringUtils.nvl(getSpecification2(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("comment: ").append(StringUtils.nvl(getComment(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   @Override
   public String getCod() {
	  return cod;
   }

   @Override
   public void setCod(String cod) {
	  this.cod = cod;
   }

   public Long getRouteVariantMuid() {
	  return routeVariantMuid;
   }

   public void setRouteVariantMuid(Long routeVariantMuid) {
	  this.routeVariantMuid = routeVariantMuid;
   }

   public Long getTypeMuid() {
	  return typeMuid;
   }

   public void setTypeMuid(Long typeMuid) {
	  this.typeMuid = typeMuid;
   }

   public Long getTerminalPointZoneAMuid() {
	  return terminalPointZoneAMuid;
   }

   public void setTerminalPointZoneAMuid(Long terminalPointZoneAMuid) {
	  this.terminalPointZoneAMuid = terminalPointZoneAMuid;
   }

   public Long getTerminalPointZoneBMuid() {
	  return terminalPointZoneBMuid;
   }

   public void setTerminalPointZoneBMuid(Long terminalPointZoneBMuid) {
	  this.terminalPointZoneBMuid = terminalPointZoneBMuid;
   }

   public Long getTerminalStationAMuid() {
	  return terminalStationAMuid;
   }

   public void setTerminalStationAMuid(Long terminalStationAMuid) {
	  this.terminalStationAMuid = terminalStationAMuid;
   }

   public Long getTerminalStationBMuid() {
	  return terminalStationBMuid;
   }

   public void setTerminalStationBMuid(Long terminalStationBMuid) {
	  this.terminalStationBMuid = terminalStationBMuid;
   }

   public Long getTerminalStationCMuid() {
	  return terminalStationCMuid;
   }

   public void setTerminalStationCMuid(Long terminalStationCMuid) {
	  this.terminalStationCMuid = terminalStationCMuid;
   }

   public Float getAverageLength() {
	  return averageLength;
   }

   public void setAverageLength(Float averageLength) {
	  this.averageLength = averageLength;
   }

   public Float getAverageLengthFixed() {
	  return averageLengthFixed;
   }

   public void setAverageLengthFixed(Float averageLengthFixed) {
	  this.averageLengthFixed = averageLengthFixed;
   }

   public String getSpecification1() {
	  return specification1;
   }

   public void setSpecification1(String specification1) {
	  this.specification1 = specification1;
   }

   public String getSpecification2() {
	  return specification2;
   }

   public void setSpecification2(String specification2) {
	  this.specification2 = specification2;
   }

   public String getComment() {
	  return comment;
   }

   public void setComment(String comment) {
	  this.comment = comment;
   }

   public Long getStopPlaceAMuid() {
	  return stopPlaceAMuid;
   }

   public void setStopPlaceAMuid(Long stopPlaceAMuid) {
	  this.stopPlaceAMuid = stopPlaceAMuid;
   }

   public Long getStopPlaceBMuid() {
	  return stopPlaceBMuid;
   }

   public void setStopPlaceBMuid(Long stopPlaceBMuid) {
	  this.stopPlaceBMuid = stopPlaceBMuid;
   }

}
