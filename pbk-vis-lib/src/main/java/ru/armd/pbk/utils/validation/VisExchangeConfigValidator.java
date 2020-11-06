package ru.armd.pbk.utils.validation;

import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.viss.VisExchangeConfigDTO;
import ru.armd.pbk.enums.viss.VisTransportTypes;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Реализация аннотации проверки настройки соединения с ВИС.
 */
public class VisExchangeConfigValidator
	extends BaseValidator
	implements ConstraintValidator<IVisExchangeConfigValidator, VisExchangeConfigDTO> {

   @Override
   public void initialize(IVisExchangeConfigValidator constraintAnnotation) {
   }

   @Override
   public boolean isValid(VisExchangeConfigDTO value, ConstraintValidatorContext context) {
	  if (value != null && value.getTransportTypeId().equals(VisTransportTypes.FTP.getId())) {
		 if (value.getPassword() != null && !value.getPassword().equals(value.getConfirmPassword())) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_PASSWORD);
			return false;
		 }
	  }
	  return true;
   }

}