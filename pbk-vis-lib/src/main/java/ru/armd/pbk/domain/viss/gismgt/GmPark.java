package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.utils.StringUtils;

public class GmPark
	extends GmBaseDomain
	implements IHasName {

   private String name;
   private String wktGeom;
   private String shortName;
   private Integer number;
   private String phone;
   private Long transportKindMuid;
   private Long graphSectionMuid;
   private String addressString;
   private Float graphSectionOffset;
   private Long buildingMuid;

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

   public String getShortName() {
	  return shortName;
   }

   public void setShortName(String shortName) {
	  this.shortName = shortName;
   }

   public Integer getNumber() {
	  return number;
   }

   public void setNumber(Integer number) {
	  this.number = number;
   }

   public String getPhone() {
	  return phone;
   }

   public void setPhone(String phone) {
	  this.phone = phone;
   }

   public Long getTransportKindMuid() {
	  return transportKindMuid;
   }

   public void setTransportKindMuid(Long transportKindMuid) {
	  this.transportKindMuid = transportKindMuid;
   }

   public Long getGraphSectionMuid() {
	  return graphSectionMuid;
   }

   public void setGraphSectionMuid(Long graphSectionMuid) {
	  this.graphSectionMuid = graphSectionMuid;
   }

   public String getAddressString() {
	  return addressString;
   }

   public void setAddressString(String addressString) {
	  this.addressString = addressString;
   }

   public Float getGraphSectionOffset() {
	  return graphSectionOffset;
   }

   public void setGraphSectionOffset(Float graphSectionOffset) {
	  this.graphSectionOffset = graphSectionOffset;
   }

   public Long getBuildingMuid() {
	  return buildingMuid;
   }

   public void setBuildingMuid(Long buildingMuid) {
	  this.buildingMuid = buildingMuid;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("wktGeom: ").append(StringUtils.nvl(getWktGeom(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("shortName: ").append(StringUtils.nvl(getShortName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("number: ").append(StringUtils.nvl(getNumber(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("phone: ").append(StringUtils.nvl(getPhone(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("transportKindMuid: ").append(StringUtils.nvl(getTransportKindMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("graphSectionMuid: ").append(StringUtils.nvl(getGraphSectionMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("addressString: ").append(StringUtils.nvl(getAddressString(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("graphSectionOffset: ").append(StringUtils.nvl(getGraphSectionOffset(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("buildingMuid: ").append(StringUtils.nvl(getBuildingMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
