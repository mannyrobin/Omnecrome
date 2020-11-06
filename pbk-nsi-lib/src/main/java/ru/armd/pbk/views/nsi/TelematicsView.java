package ru.armd.pbk.views.nsi;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.utils.json.DotSepratedDateTimeSerializer;

import java.util.Date;

/**
 * Представление телематики.
 */
public class TelematicsView
	extends BaseGridView {

   private Long id;

   @JsonSerialize(using = DotSepratedDateTimeSerializer.class)
   private Date pointTime;

   private Long equipmentId;
   private Double pointLatitude;
   private Double pointLongitude;
   private String isAlarm;
   private Float speed;

   public Long getId() {
	  return id;
   }

   public void setId(Long id) {
	  this.id = id;
   }

   public Date getPointTime() {
	  return pointTime;
   }

   public void setPointTime(Date pointTime) {
	  this.pointTime = pointTime;
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

   public Long getEquipmentId() {
	  return equipmentId;
   }

   public void setEquipmentId(Long equipmentId) {
	  this.equipmentId = equipmentId;
   }


   public String getIsAlarm() {
	  return isAlarm;
   }

   public void setIsAlarm(String isAlarm) {
	  this.isAlarm = isAlarm;
   }

   public Float getSpeed() {
	  return speed;
   }

   public void setSpeed(Float speed) {
	  this.speed = speed;
   }

}
