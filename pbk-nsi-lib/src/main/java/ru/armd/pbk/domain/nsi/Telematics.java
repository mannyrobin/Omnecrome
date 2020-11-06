package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

/**
 * Домен телематики.
 */
public class Telematics
	extends BaseDomain {

   private Date pointTime;
   private Long equipmentId;
   private Double pointLatitude;
   private Double pointLongitude;
   private Date workDate;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("pointTime: ").append(StringUtils.nvl(getPointTime(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("equipmentId: ").append(StringUtils.nvl(getEquipmentId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("pointLatitude: ").append(StringUtils.nvl(getPointLatitude(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("pointLongitude: ").append(StringUtils.nvl(getPointLongitude(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("workDate: ").append(StringUtils.nvl(getWorkDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public Date getPointTime() {
	  return pointTime;
   }

   public void setPointTime(Date pointTime) {
	  this.pointTime = pointTime;
   }

   public Long getEquipmentId() {
	  return equipmentId;
   }

   public void setEquipmentId(Long equipmentId) {
	  this.equipmentId = equipmentId;
   }

   public Double getPointLatitude() {
	  return pointLatitude;
   }

   public void setPointLatitude(Double pointLatitude) {
	  this.pointLatitude = pointLatitude;
   }

   public Double getPointLongitude() {
	  return pointLongitude;
   }

   public void setPointLongitude(Double pointLongitude) {
	  this.pointLongitude = pointLongitude;
   }

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

}
