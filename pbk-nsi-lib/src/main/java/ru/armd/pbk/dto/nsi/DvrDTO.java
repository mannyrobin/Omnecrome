package ru.armd.pbk.dto.nsi;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.validation.IDvrValidator;

/**
 * DTO видеорегистраторов.
 */
@IDvrValidator
public class DvrDTO
	extends BaseVersionDTO {

   @NotBlank(message = "Поле \"Номер видеорегистратора\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Номер видеорегистратора\" не должно превышать " + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String dvrNumber;

   @NotBlank(message = "Поле \"Модель видеорегистратора\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Модель видеорегистратора\" не должно превышать " + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String dvrModel;

   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Описание\" не должно превышать " + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String description;

   private Long deptId;

   public String getDvrNumber() {
	  return dvrNumber;
   }

   public void setDvrNumber(String dvrNumber) {
	  this.dvrNumber = dvrNumber;
   }

   public String getDvrModel() {
	  return dvrModel;
   }

   public void setDvrModel(String dvrModel) {
	  this.dvrModel = dvrModel;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public Long getDeptId() {
	  return deptId;
   }

   public void setDeptId(Long deptId) {
	  this.deptId = deptId;
   }

}
