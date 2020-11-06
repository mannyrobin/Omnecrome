package ru.armd.pbk.dto.nsi;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.IHasCod;
import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * ДТО вместимость ТС.
 */
public class TsCapacityDTO
	extends BaseDTO
	implements IHasCod, IHasName {

   @NotBlank(message = "Поле \"Название\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Название\" не должно превышать " + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String name;

   @Length(max = StringUtils.MIDDLE_INPUT_SIZE, message = "Число символов в поле \"Описание\" не должно превышать " + StringUtils.MIDDLE_INPUT_SIZE + " символов.")
   private String description;

   @NotBlank(message = "Поле \"Код\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Код\" не должно превышать " + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String cod;

   @NotNull(message = "\"Тип ТС\" должен быть выбран.")
   private Long tsTypeId;

   private Long gmCapacityId;

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

   public Long getTsTypeId() {
	  return tsTypeId;
   }

   public void setTsTypeId(Long tsTypeId) {
	  this.tsTypeId = tsTypeId;
   }

   public Long getGmCapacityId() {
	  return gmCapacityId;
   }

   public void setGmCapacityId(Long gmCapacityId) {
	  this.gmCapacityId = gmCapacityId;
   }

}
