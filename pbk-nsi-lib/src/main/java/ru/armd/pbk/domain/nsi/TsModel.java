package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен моделей ТС.
 */
public class TsModel
	extends BaseDomain {

   private String make;
   private String model;
   private Long tsCapacityId;
   private Integer passengerCountMax;
   private Integer seatCount;
   private Double square;
   private Double mass;
   private Double length;
   private Double width;
   private Double height;
   private Double speedMax;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("make: ").append(StringUtils.nvl(getMake(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("model: ").append(StringUtils.nvl(getModel(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("tsCapacityId: ").append(StringUtils.nvl(getTsCapacityId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("passengerCountMax: ").append(StringUtils.nvl(getPassengerCountMax(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("seatCount: ").append(StringUtils.nvl(getSeatCount(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("square: ").append(StringUtils.nvl(getSquare(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("mass: ").append(StringUtils.nvl(getMass(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("length: ").append(StringUtils.nvl(getLength(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("width: ").append(StringUtils.nvl(getWidth(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("height: ").append(StringUtils.nvl(getHeight(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("speedMax: ").append(StringUtils.nvl(getSpeedMax(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public Double getSquare() {
	  return square;
   }

   public void setSquare(Double square) {
	  this.square = square;
   }

   public Double getMass() {
	  return mass;
   }

   public void setMass(Double mass) {
	  this.mass = mass;
   }

   public Double getLength() {
	  return length;
   }

   public void setLength(Double length) {
	  this.length = length;
   }

   public Double getWidth() {
	  return width;
   }

   public void setWidth(Double width) {
	  this.width = width;
   }

   public Double getHeight() {
	  return height;
   }

   public void setHeight(Double height) {
	  this.height = height;
   }

   public Double getSpeedMax() {
	  return speedMax;
   }

   public void setSpeedMax(Double speedMax) {
	  this.speedMax = speedMax;
   }

   public String getMake() {
	  return make;
   }

   public void setMake(String make) {
	  this.make = make;
   }

   public String getModel() {
	  return model;
   }

   public void setModel(String model) {
	  this.model = model;
   }

   public Long getTsCapacityId() {
	  return tsCapacityId;
   }

   public void setTsCapacityId(Long tsCapacityId) {
	  this.tsCapacityId = tsCapacityId;
   }

   public Integer getPassengerCountMax() {
	  return passengerCountMax;
   }

   public void setPassengerCountMax(Integer passengerCountMax) {
	  this.passengerCountMax = passengerCountMax;
   }

   public Integer getSeatCount() {
	  return seatCount;
   }

   public void setSeatCount(Integer seatCount) {
	  this.seatCount = seatCount;
   }
}
