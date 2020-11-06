package ru.armd.pbk.dto.nsi;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.validation.IPuskValidator;

/**
 * ДТО ПУсК.
 */
@IPuskValidator
public class PuskDTO
	extends BaseVersionDTO {

   @NotBlank(message = "Поле \"Номер ПУсК\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Номер ПУсК\" не должно превышать " + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String puskNumber;

   @NotBlank(message = "Поле \"Модель ПУсК\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Модель ПУсК\" не должно превышать " + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String puskModel;

   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Описание\" не должно превышать " + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String description;

   public String getPuskModel() {
	  return puskModel;
   }

   public void setPuskModel(String puskModel) {
	  this.puskModel = puskModel;
   }

   public String getPuskNumber() {
	  return puskNumber;
   }

   public void setPuskNumber(String puskNumber) {
	  this.puskNumber = puskNumber;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

}
