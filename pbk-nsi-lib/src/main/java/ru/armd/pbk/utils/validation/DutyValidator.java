package ru.armd.pbk.utils.validation;

import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.DutyDTO;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Реализация аннотации проверки наряда.
 */
public class DutyValidator
	extends BaseValidator
	implements ConstraintValidator<IDutyValidator, DutyDTO> {

   @Override
   public void initialize(IDutyValidator constraintAnnotation) {
   }

   @Override
   public boolean isValid(DutyDTO value, ConstraintValidatorContext context) {
	  if (value != null) {
		 if (value.getMoveStartTime() != null && value.getMoveEndTime() != null && (value.getMoveStartTime().getTime() > value.getMoveEndTime().getTime())) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_DUTY_WORK_PERIOD);
			return false;
		 }
	  }
	  return true;
   }

}