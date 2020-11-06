package ru.armd.pbk.dto.nsi;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.validation.IContactlessCardValidator;

/**
 * ДТО БСК.
 */
@IContactlessCardValidator
public class ContactlessCardDTO
	extends BaseVersionDTO {

   @NotBlank(message = "Поле \"UID\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"UID\" не должно превышать " + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String uid;

   @NotBlank(message = "Поле \"Номер карты\" должно быть заполнено.")
   @Length(max = StringUtils.SHORT_INPUT_SIZE, message = "Число символов в поле \"Номер карты\" не должно превышать " + StringUtils.SHORT_INPUT_SIZE + " символов.")
   private String cardNumber;
   private String description;

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public String getCardNumber() {
	  return cardNumber;
   }

   public void setCardNumber(String cardNumber) {
	  this.cardNumber = cardNumber;
   }

   public String getUid() {
	  return uid;
   }

   public void setUid(String uid) {
	  this.uid = uid;
   }

}
