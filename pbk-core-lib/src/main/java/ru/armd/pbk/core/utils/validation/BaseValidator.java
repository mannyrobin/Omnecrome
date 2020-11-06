package ru.armd.pbk.core.utils.validation;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

/**
 * Базовый валидатор.
 */
public class BaseValidator {

   /**
	* Метод добавления валидационного сообщения.
	*
	* @param constraintContext constraintContext
	* @param message           сообщение
	*/
   protected void addConstraintViolation(ConstraintValidatorContext constraintContext, String message) {
	  constraintContext.disableDefaultConstraintViolation();
	  ConstraintViolationBuilder builder = constraintContext.
		  buildConstraintViolationWithTemplate(message);
	  builder.addConstraintViolation();
   }
}
