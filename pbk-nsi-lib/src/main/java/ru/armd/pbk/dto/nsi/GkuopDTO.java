package ru.armd.pbk.dto.nsi;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.validation.IGkuopValidator;

import javax.validation.constraints.NotNull;

/**
 * DTO ГКУ ОП.
 *
 * @author nikita_chebotaryov
 */
@IGkuopValidator
public class GkuopDTO
	extends BaseVersionDTO {

   @NotBlank(message = "Поле \"Подразделение\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Подразделение\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String gkuopDeptName;

   @NotBlank(message = "Поле \"Фамилия\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Фамилия\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String surname;

   @NotBlank(message = "Поле \"Имя\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Имя\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String name;

   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Отчество\" не должно превышать "
	   + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String patronumic;

   @NotBlank(message = "Поле \"Должность\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Должность\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String positionName;

   @NotBlank(message = "Поле \"Табельный номер\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Табельный номер\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String personalNumber;

   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"ID в ВИС ГКУ ОП\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String visGkuopId;

   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Описание\" не должно превышать "
	   + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String description;

   @NotNull(message = "Поле \"Участвует в планировании\" должно быть заполнено.")
   private Boolean forPlanUse;

   public String getGkuopDeptName() {
	  return gkuopDeptName;
   }

   public void setGkuopDeptName(String gkuopDeptName) {
	  this.gkuopDeptName = gkuopDeptName;
   }

   public String getSurname() {
	  return surname;
   }

   public void setSurname(String surname) {
	  this.surname = surname;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getPatronumic() {
	  return patronumic;
   }

   public void setPatronumic(String patronumic) {
	  this.patronumic = patronumic;
   }

   public String getPositionName() {
	  return positionName;
   }

   public void setPositionName(String positionName) {
	  this.positionName = positionName;
   }

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }

   public String getVisGkuopId() {
	  return visGkuopId;
   }

   public void setVisGkuopId(String visGkuopId) {
	  this.visGkuopId = visGkuopId;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Boolean getForPlanUse() {
	  return forPlanUse;
   }

   public void setForPlanUse(Boolean forPlanUse) {
	  this.forPlanUse = forPlanUse;
   }

}
