package ru.armd.pbk.dto.core;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.validation.ISettingValidator;

/**
 * Объект для передачи состояния системной настройки.
 */
@ISettingValidator
public class SettingDTO
	extends BaseDTO {

   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Код\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String cod;

   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Название\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String name;

   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Описание\" не должно превышать "
	   + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String description;

   @NotBlank(message = "Поле \"Значение свойства\" должно быть заполнено.")
   private String value;

   /**
	* Конструктор по умолчанию.
	*/
   public SettingDTO() {
   }

   public String getCod() {
	  return cod;
   }

   public void setCod(String cod) {
	  this.cod = cod;
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public String getValue() {
	  return value;
   }

   public void setValue(String value) {
	  this.value = value;
   }

}
