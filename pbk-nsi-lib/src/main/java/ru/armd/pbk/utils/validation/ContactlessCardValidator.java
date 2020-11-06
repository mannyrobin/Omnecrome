package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.ContactlessCardDTO;
import ru.armd.pbk.mappers.nsi.ContactlessCardMapper;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация аннотации проверки БСК.
 */
public class ContactlessCardValidator
	extends BaseValidator
	implements
	ConstraintValidator<IContactlessCardValidator, ContactlessCardDTO> {

   @Autowired
   private ContactlessCardMapper mapper;

   @Override
   public void initialize(IContactlessCardValidator constraintAnnotation) {
   }

   @Override
   public boolean isValid(ContactlessCardDTO value,
						  ConstraintValidatorContext context) {
	  boolean valid = true;
	  if (value != null) {
		 Map<String, Object> filter = new HashMap<String, Object>();
		 filter.put("headId", value.getHeadId());
		 filter.put("cardNumber", value.getCardNumber());
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_CARD_NUMBER);
			valid = false;
		 }

	  }
	  return valid;
   }

}
