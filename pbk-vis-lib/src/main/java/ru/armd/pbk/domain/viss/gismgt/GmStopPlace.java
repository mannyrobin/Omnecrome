package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

public class GmStopPlace
	extends GmBaseDomain {

   private String name;
   private String wktGeom;
   private Long stopMuid;
   private Long orderMuid;
   private Long buildingMuid;
   private Long graphSectionMuid;
   private Long graphTramSectionMuid;
   private Date startDate;
   private Date changeDate;
   private Date endDate;
   private byte[] photo;
   private Integer isTechnical;
   private Integer hasFacilitiesForDisabled;
   private Integer hasBay;
   private Integer hasDisplayPanel;
   private Integer hasBus;
   private Integer hasTrolley;
   private Integer hasTram;
   private Integer hasSpeedTram;
   private String displayPanelType;
   private String displayPanelIMEI;
   private String displayPanelSim;
   private String displayPanelASUT;
   private String buildingAddressString;
   private String comment;
   private Float graphSectionOffset;
   private Float graphTramSectionOffset;
   private Float buildingDistance;
   private String suffix;
   private Long stopStateMuid;
   private Long stopModeMuid;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("wktGeom: ").append(StringUtils.nvl(getWktGeom(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopMuid: ").append(StringUtils.nvl(getStopMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("orderMuid: ").append(StringUtils.nvl(getOrderMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("buildingMuid: ").append(StringUtils.nvl(getBuildingMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("graphSectionMuid: ").append(StringUtils.nvl(getGraphSectionMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("graphTramSectionMuid: ").append(StringUtils.nvl(getGraphTramSectionMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("startDate: ").append(StringUtils.nvl(getStartDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("changeDate: ").append(StringUtils.nvl(getChangeDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("endDate: ").append(StringUtils.nvl(getEndDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("isTechnical: ").append(StringUtils.nvl(getIsTechnical(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("hasFacilitiesForDisabled: ").append(StringUtils.nvl(getHasFacilitiesForDisabled(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("hasBay: ").append(StringUtils.nvl(getHasBay(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("hasDisplayPanel: ").append(StringUtils.nvl(getHasDisplayPanel(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("hasBus: ").append(StringUtils.nvl(getHasBus(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("hasTrolley: ").append(StringUtils.nvl(getHasTrolley(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("hasTram: ").append(StringUtils.nvl(getHasTram(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("hasSpeedTram: ").append(StringUtils.nvl(getHasSpeedTram(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("displayPanelType: ").append(StringUtils.nvl(getDisplayPanelType(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("displayPanelIMEI: ").append(StringUtils.nvl(getDisplayPanelIMEI(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("displayPanelSim: ").append(StringUtils.nvl(getDisplayPanelSim(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("displayPanelASUT: ").append(StringUtils.nvl(getDisplayPanelASUT(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("buildingAddressString: ").append(StringUtils.nvl(getBuildingAddressString(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("comment: ").append(StringUtils.nvl(getComment(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("graphSectionOffset: ").append(StringUtils.nvl(getGraphSectionOffset(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("graphTramSectionOffset: ").append(StringUtils.nvl(getGraphTramSectionOffset(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("buildingDistance: ").append(StringUtils.nvl(getBuildingDistance(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("suffix: ").append(StringUtils.nvl(getSuffix(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopStateMuid: ").append(StringUtils.nvl(getStopStateMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("stopModeMuid: ").append(StringUtils.nvl(getStopModeMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public String getWktGeom() {
	  return wktGeom;
   }

   public void setWktGeom(String wktGeom) {
	  this.wktGeom = wktGeom;
   }

   public Long getStopMuid() {
	  return stopMuid;
   }

   public void setStopMuid(Long stopMuid) {
	  this.stopMuid = stopMuid;
   }

   public Long getOrderMuid() {
	  return orderMuid;
   }

   public void setOrderMuid(Long orderMuid) {
	  this.orderMuid = orderMuid;
   }

   public Long getBuildingMuid() {
	  return buildingMuid;
   }

   public void setBuildingMuid(Long buildingMuid) {
	  this.buildingMuid = buildingMuid;
   }

   public Long getGraphSectionMuid() {
	  return graphSectionMuid;
   }

   public void setGraphSectionMuid(Long graphSectionMuid) {
	  this.graphSectionMuid = graphSectionMuid;
   }

   public Long getGraphTramSectionMuid() {
	  return graphTramSectionMuid;
   }

   public void setGraphTramSectionMuid(Long graphTramSectionMuid) {
	  this.graphTramSectionMuid = graphTramSectionMuid;
   }

   public Date getStartDate() {
	  return startDate;
   }

   public void setStartDate(Date startDate) {
	  this.startDate = startDate;
   }

   public Date getChangeDate() {
	  return changeDate;
   }

   public void setChangeDate(Date changeDate) {
	  this.changeDate = changeDate;
   }

   public Date getEndDate() {
	  return endDate;
   }

   public void setEndDate(Date endDate) {
	  this.endDate = endDate;
   }

   public byte[] getPhoto() {
	  return photo;
   }

   public void setPhoto(byte[] photo) {
	  this.photo = photo;
   }

   public Integer getIsTechnical() {
	  return isTechnical;
   }

   public void setIsTechnical(Integer isTechnical) {
	  this.isTechnical = isTechnical;
   }

   public Integer getHasFacilitiesForDisabled() {
	  return hasFacilitiesForDisabled;
   }

   public void setHasFacilitiesForDisabled(Integer hasFacilitiesForDisabled) {
	  this.hasFacilitiesForDisabled = hasFacilitiesForDisabled;
   }

   public Integer getHasBay() {
	  return hasBay;
   }

   public void setHasBay(Integer hasBay) {
	  this.hasBay = hasBay;
   }

   public Integer getHasDisplayPanel() {
	  return hasDisplayPanel;
   }

   public void setHasDisplayPanel(Integer hasDisplayPanel) {
	  this.hasDisplayPanel = hasDisplayPanel;
   }

   public Integer getHasBus() {
	  return hasBus;
   }

   public void setHasBus(Integer hasBus) {
	  this.hasBus = hasBus;
   }

   public Integer getHasTrolley() {
	  return hasTrolley;
   }

   public void setHasTrolley(Integer hasTrolley) {
	  this.hasTrolley = hasTrolley;
   }

   public Integer getHasTram() {
	  return hasTram;
   }

   public void setHasTram(Integer hasTram) {
	  this.hasTram = hasTram;
   }

   public Integer getHasSpeedTram() {
	  return hasSpeedTram;
   }

   public void setHasSpeedTram(Integer hasSpeedTram) {
	  this.hasSpeedTram = hasSpeedTram;
   }

   public String getDisplayPanelType() {
	  return displayPanelType;
   }

   public void setDisplayPanelType(String displayPanelType) {
	  this.displayPanelType = displayPanelType;
   }

   public String getDisplayPanelIMEI() {
	  return displayPanelIMEI;
   }

   public void setDisplayPanelIMEI(String displayPanelIMEI) {
	  this.displayPanelIMEI = displayPanelIMEI;
   }

   public String getDisplayPanelSim() {
	  return displayPanelSim;
   }

   public void setDisplayPanelSim(String displayPanelSim) {
	  this.displayPanelSim = displayPanelSim;
   }

   public String getDisplayPanelASUT() {
	  return displayPanelASUT;
   }

   public void setDisplayPanelASUT(String displayPanelASUT) {
	  this.displayPanelASUT = displayPanelASUT;
   }

   public String getBuildingAddressString() {
	  return buildingAddressString;
   }

   public void setBuildingAddressString(String buildingAddressString) {
	  this.buildingAddressString = buildingAddressString;
   }

   public String getComment() {
	  return comment;
   }

   public void setComment(String comment) {
	  this.comment = comment;
   }

   public Float getGraphSectionOffset() {
	  return graphSectionOffset;
   }

   public void setGraphSectionOffset(Float graphSectionOffset) {
	  this.graphSectionOffset = graphSectionOffset;
   }

   public Float getGraphTramSectionOffset() {
	  return graphTramSectionOffset;
   }

   public void setGraphTramSectionOffset(Float graphTramSectionOffset) {
	  this.graphTramSectionOffset = graphTramSectionOffset;
   }

   public Float getBuildingDistance() {
	  return buildingDistance;
   }

   public void setBuildingDistance(Float buildingDistance) {
	  this.buildingDistance = buildingDistance;
   }

   public String getSuffix() {
	  return suffix;
   }

   public void setSuffix(String suffix) {
	  this.suffix = suffix;
   }

   public Long getStopStateMuid() {
	  return stopStateMuid;
   }

   public void setStopStateMuid(Long stopStateMuid) {
	  this.stopStateMuid = stopStateMuid;
   }

   public Long getStopModeMuid() {
	  return stopModeMuid;
   }

   public void setStopModeMuid(Long stopModeMuid) {
	  this.stopModeMuid = stopModeMuid;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

}
