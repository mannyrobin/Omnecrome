package ru.armd.pbk.dto.nsi;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Дто смены.
 */
public class ShiftDTO
	extends BaseDTO {

   @NotBlank(message = "Поле \"Код\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Код\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String code;

   @NotBlank(message = "Поле \"Название\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Название\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String name;

   @NotNull(message = "Поле \"Резерв\" должно быть заполнено.")
   private Integer reserveCount;

   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Описание\" не должно превышать "
	   + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String description;

   @NotNull(message = "Поле \"Время начала работы\" должно быть заполнено.")
   private Date workStartTime;

   @NotNull(message = "Поле \"Время окончания работы\" должно быть заполнено.")
   private Date workEndTime;

   @NotNull(message = "Поле \"Время начала перерыва\" должно быть заполнено.")
   private Date breakStartTime;

   @NotNull(message = "Поле \"Время окончания перерыва\" должно быть заполнено.")
   private Date breakEndTime;

   public String getCode() {
	  return code;
   }

   public void setCode(String code) {
	  this.code = code;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Integer getReserveCount() {
	  return reserveCount;
   }

   public void setReserveCount(Integer reserveCount) {
	  this.reserveCount = reserveCount;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Date getWorkStartTime() {
	  return workStartTime;
   }

   public void setWorkStartTime(Date workStartTime) {
	  this.workStartTime = workStartTime;
   }

   public Date getWorkEndTime() {
	  return workEndTime;
   }

   public void setWorkEndTime(Date workEndTime) {
	  this.workEndTime = workEndTime;
   }

   public Date getBreakStartTime() {
	  return breakStartTime;
   }

   public void setBreakStartTime(Date breakStartTime) {
	  this.breakStartTime = breakStartTime;
   }

   public Date getBreakEndTime() {
	  return breakEndTime;
   }

   public void setBreakEndTime(Date breakEndTime) {
	  this.breakEndTime = breakEndTime;
   }
}
