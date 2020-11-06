package ru.armd.pbk.dto.nsi;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.validation.IDriverValidator;

/**
 * Дто водителя.
 */
@IDriverValidator
public class DriverDTO
	extends BaseVersionDTO {

   @NotBlank(message = "Поле \"ID в ВИС АСДУ\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"ID в ВИС АСДУ\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String asduDriverId;

   @NotBlank(message = "Поле \"ID парка в ВИС АСДУ\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"ID парка в ВИС АСДУ\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String asduDepotId;

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

   @NotBlank(message = "Поле \"Табельный номер водителя\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Табельный номер водителя\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String personalNumber;

   private Long parkId;

   public String getAsduDriverId() {
	  return asduDriverId;
   }

   public void setAsduDriverId(String asduDriverId) {
	  this.asduDriverId = asduDriverId;
   }

   public String getAsduDepotId() {
	  return asduDepotId;
   }

   public void setAsduDepotId(String asduDepotId) {
	  this.asduDepotId = asduDepotId;
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

   public String getPersonalNumber() {
	  return personalNumber;
   }

   public void setPersonalNumber(String personalNumber) {
	  this.personalNumber = personalNumber;
   }

   public Long getParkId() {
	  return parkId;
   }

   public void setParkId(Long parkId) {
	  this.parkId = parkId;
   }
}
