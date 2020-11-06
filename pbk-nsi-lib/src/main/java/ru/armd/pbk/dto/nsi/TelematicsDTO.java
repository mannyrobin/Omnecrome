package ru.armd.pbk.dto.nsi;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.json.DotSepratedDateDeserializer;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Дто телематики.
 */
public class TelematicsDTO
	extends BaseDTO {

   @NotNull(message = "Поле \"Время\" должно быть заполнено.")
   private Date pointTimeDTO;

   @NotNull(message = "Поле \"Дата\" должно быть заполнено.")
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date pointDateDTO;

   //@NotNull(message = "Поле \"Оборудование\" должно быть заполнено.")
   private Long equipmentId;

   @NotNull(message = "Поле \"Широта\" должно быть заполнено.")
   private Double pointLatitude;

   @NotNull(message = "Поле \"Долгота\" должно быть заполнено.")
   private Double pointLongitude;

   @NotBlank(message = "Поле \"Признак активности оборудования\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Признак активности оборудования\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String isAlarm;

   @NotNull(message = "Поле \"Скорость\" должно быть заполнено.")
   private Float speed;

   public Date getPointTimeDTO() {
	  return pointTimeDTO;
   }

   public void setPointTimeDTO(Date pointTimeDTO) {
	  this.pointTimeDTO = pointTimeDTO;
   }

   public Date getPointDateDTO() {
	  return pointDateDTO;
   }

   public void setPointDateDTO(Date pointDateDTO) {
	  this.pointDateDTO = pointDateDTO;
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
