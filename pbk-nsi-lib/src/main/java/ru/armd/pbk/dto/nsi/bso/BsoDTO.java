package ru.armd.pbk.dto.nsi.bso;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * DTO для БСО.
 */
public class BsoDTO
	extends BaseDTO {

   private Long bsoNumberRuleId;

   @NotBlank(message = "Поле \"Номер БСО\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Номер БСО\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String bsoNumber;

   @NotNull(message = "Поле \"Распечатано\" должно быть заполнено.")
   private Boolean isPrinted;

   @NotNull(message = "Поле \"Испорчено\" должно быть заполнено.")
   private Boolean isTrashed;

   @NotNull(message = "Поле \"Закреплено\" должно быть заполнено.")
   private Boolean isBound;

   @NotNull(message = "Поле \"Использовано\" должно быть заполнено.")
   private Boolean isUsed;

   public Long getBsoNumberRuleId() {
	  return bsoNumberRuleId;
   }

   public void setBsoNumberRuleId(Long bsoNumberRuleId) {
	  this.bsoNumberRuleId = bsoNumberRuleId;
   }

   public String getBsoNumber() {
	  return bsoNumber;
   }

   public void setBsoNumber(String bsoNumber) {
	  this.bsoNumber = bsoNumber;
   }

   public Boolean getIsPrinted() {
	  return isPrinted;
   }

   public void setIsPrinted(Boolean isPrinted) {
	  this.isPrinted = isPrinted;
   }

   public Boolean getIsTrashed() {
	  return isTrashed;
   }

   public void setIsTrashed(Boolean isTrashed) {
	  this.isTrashed = isTrashed;
   }

   public Boolean getIsBound() {
	  return isBound;
   }

   public void setIsBound(Boolean isBound) {
	  this.isBound = isBound;
   }

   public Boolean getIsUsed() {
	  return isUsed;
   }

   public void setIsUsed(Boolean isUsed) {
	  this.isUsed = isUsed;
   }
}
