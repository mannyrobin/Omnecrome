package ru.armd.pbk.dto.nsi;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.IHasCod;
import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.validation.ICountyValidator;

import javax.validation.constraints.NotNull;

/**
 * ДТО округов.
 */
@ICountyValidator
public class CountyDTO
	extends BaseVersionDTO
	implements IHasCod, IHasName {

   @NotBlank(message = "Поле \"Название\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Название\" не должно превышать " + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String name;

   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Описание\" не должно превышать " + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String description;

   @NotBlank(message = "Поле \"Код\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Код\" не должно превышать " + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String cod;

   @NotNull(message = "\"ID Округа в ВИС ГИС МГТ\" должен быть выбран.")
   private Long gmDistrictId;

   private Long departmentId;

   private String shortName;

   public Long getDepartmentId() {
	  return departmentId;
   }

   public void setDepartmentId(Long departmentId) {
	  this.departmentId = departmentId;
   }

   public Long getGmDistrictId() {
	  return gmDistrictId;
   }

   public void setGmDistrictId(Long gmDistrictId) {
	  this.gmDistrictId = gmDistrictId;
   }

   @Override
   public String getName() {
	  return name;
   }

   @Override
   public void setName(String name) {
	  this.name = name;
   }

   @Override
   public String getCod() {
	  return cod;
   }

   @Override
   public void setCod(String cod) {
	  this.cod = cod;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public String getShortName() {
	  return shortName;
   }

   public void setShortName(String shortName) {
	  this.shortName = shortName;
   }

}
