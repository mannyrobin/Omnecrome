package ru.armd.pbk.dto.nsi;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.json.DotSepratedDateDeserializer;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;
import ru.armd.pbk.utils.validation.IDutyValidator;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * ДТО наряда.
 */
@IDutyValidator
public class DutyDTO
	extends BaseDTO {

   @NotNull(message = "\"Транспортные сутки\" должен быть выбран.")
   @JsonSerialize(using = DotSepratedDateSerializer.class)
   @JsonDeserialize(using = DotSepratedDateDeserializer.class)
   private Date workDate;

   @NotBlank(message = "Поле \"Код Маршрута ЕАСУ ФХД\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Код Маршрута ЕАСУ ФХД\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String easuFhdRouteCode;

   @NotBlank(message = "Поле \"ТС\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"ТС\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String tsDepoNumber;

   @NotBlank(message = "Поле \"Водитель\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Водитель\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String driverPersonalNumber;

   @NotBlank(message = "Поле \"Код выхода на Маршрут\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Код выхода на Маршрут\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String moveCode;

   @NotNull(message = "Поле \"Начало работы\" должно быть заполнено.")
   private Date moveStartTime;

   @NotNull(message = "Поле \"Завершение работы\" должно быть заполнено.")
   private Date moveEndTime;

   public Date getWorkDate() {
	  return workDate;
   }

   public void setWorkDate(Date workDate) {
	  this.workDate = workDate;
   }

   public String getEasuFhdRouteCode() {
	  return easuFhdRouteCode;
   }

   public void setEasuFhdRouteCode(String easuFhdRouteCode) {
	  this.easuFhdRouteCode = easuFhdRouteCode;
   }

   public String getTsDepoNumber() {
	  return tsDepoNumber;
   }

   public void setTsDepoNumber(String tsDepoNumber) {
	  this.tsDepoNumber = tsDepoNumber;
   }

   public String getDriverPersonalNumber() {
	  return driverPersonalNumber;
   }

   public void setDriverPersonalNumber(String driverPersonalNumber) {
	  this.driverPersonalNumber = driverPersonalNumber;
   }

   public String getMoveCode() {
	  return moveCode;
   }

   public void setMoveCode(String moveCode) {
	  this.moveCode = moveCode;
   }

   public Date getMoveStartTime() {
	  return moveStartTime;
   }

   public void setMoveStartTime(Date moveStartTime) {
	  this.moveStartTime = moveStartTime;
   }

   public Date getMoveEndTime() {
	  return moveEndTime;
   }

   public void setMoveEndTime(Date moveEndTime) {
	  this.moveEndTime = moveEndTime;
   }

}
