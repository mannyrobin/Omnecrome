package ru.armd.pbk.utils.validation;

import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.ShiftDTO;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Реализация аннотации проверки смены.
 */
public class ShiftValidator
	extends BaseValidator
	implements ConstraintValidator<IShiftValidator, ShiftDTO> {

   @Override
   public void initialize(IShiftValidator constraintAnnotation) {
   }

   @Override
   public boolean isValid(ShiftDTO value, ConstraintValidatorContext context) {
	  boolean valid = true;
	  if (value != null) {
		 if (value.getWorkStartTime() != null && value.getWorkEndTime() != null && value.getWorkStartTime().getTime() > value.getWorkEndTime().getTime()) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_SHIFT_WORK_PERIOD);
			valid = false;
		 }
		 if (value.getBreakStartTime() != null && value.getBreakEndTime() != null && value.getBreakStartTime().getTime() > value.getBreakEndTime().getTime()) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_SHIFT_BREAK_PERIOD);
			valid = false;
		 }
	  }
	  return valid;
   }

}