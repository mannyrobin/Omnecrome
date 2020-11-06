package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

public class GmTerminalStation
	extends GmBaseDomain
	implements IHasName {

   private String wktGeom;
   private String name;
   private String phone;
   private Long orderMuid;
   private Long streetMuid;
   private Long buildingMuid;
   private Long parkMuid;
   private Long graphSectionMuid;
   private byte[] photo;
   private Date startDate;
   private String addressString;
   private Integer hasParking;
   private Float graphSectionOffset;
   private Integer hasBus;
   private Integer hasTrolley;
   private Integer hasTram;
   private Integer hasSpeedTram;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("wktGeom: ").append(StringUtils.nvl(getWktGeom(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("phone: ").append(StringUtils.nvl(getPhone(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("orderMuid: ").append(StringUtils.nvl(getOrderMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("buildingMuid: ").append(StringUtils.nvl(getBuildingMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("graphSectionMuid: ").append(StringUtils.nvl(getGraphSectionMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("streetMuid: ").append(StringUtils.nvl(getStreetMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("startDate: ").append(StringUtils.nvl(getStartDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("addressString: ").append(StringUtils.nvl(getAddressString(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("hasParking: ").append(StringUtils.nvl(getHasParking(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("parkMuid: ").append(StringUtils.nvl(getParkMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("hasParking: ").append(StringUtils.nvl(getHasParking(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("graphSectionOffset: ").append(StringUtils.nvl(getGraphSectionOffset(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("hasBus: ").append(StringUtils.nvl(getHasBus(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("hasTrolley: ").append(StringUtils.nvl(getHasTrolley(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("hasTram: ").append(StringUtils.nvl(getHasTram(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("hasSpeedTram: ").append(StringUtils.nvl(getHasSpeedTram(), FIELD_NULL)).append(FIELD_SEPARATOR);
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

   public String getWktGeom() {
	  return wktGeom;
   }

   public void setWktGeom(String wktGeom) {
	  this.wktGeom = wktGeom;
   }

   public String getPhone() {
	  return phone;
   }

   public void setPhone(String phone) {
	  this.phone = phone;
   }

   public Long getOrderMuid() {
	  return orderMuid;
   }

   public void setOrderMuid(Long orderMuid) {
	  this.orderMuid = orderMuid;
   }

   public Long getStreetMuid() {
	  return streetMuid;
   }

   public void setStreetMuid(Long streetMuid) {
	  this.streetMuid = streetMuid;
   }

   public Long getBuildingMuid() {
	  return buildingMuid;
   }

   public void setBuildingMuid(Long buildingMuid) {
	  this.buildingMuid = buildingMuid;
   }

   public Long getParkMuid() {
	  return parkMuid;
   }

   public void setParkMuid(Long parkMuid) {
	  this.parkMuid = parkMuid;
   }

   public Long getGraphSectionMuid() {
	  return graphSectionMuid;
   }

   public void setGraphSectionMuid(Long graphSectionMuid) {
	  this.graphSectionMuid = graphSectionMuid;
   }

   public byte[] getPhoto() {
	  return photo;
   }

   public void setPhoto(byte[] photo) {
	  this.photo = photo;
   }

   public Date getStartDate() {
	  return startDate;
   }

   public void setStartDate(Date startDate) {
	  this.startDate = startDate;
   }

   public String getAddressString() {
	  return addressString;
   }

   public void setAddressString(String addressString) {
	  this.addressString = addressString;
   }

   public Integer getHasParking() {
	  return hasParking;
   }

   public void setHasParking(Integer hasParking) {
	  this.hasParking = hasParking;
   }

   public Float getGraphSectionOffset() {
	  return graphSectionOffset;
   }

   public void setGraphSectionOffset(Float graphSectionOffset) {
	  this.graphSectionOffset = graphSectionOffset;
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

}
