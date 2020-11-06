package ru.armd.pbk.utils.validation;

import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.CalendarDTO;
import ru.armd.pbk.utils.ValidationUtilMessages;
import ru.armd.pbk.utils.date.DateUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * Реализация аннотации проверки календаря.
 */
public class CalendarValidator
	extends BaseValidator
	implements ConstraintValidator<CalendarCheck, CalendarDTO> {


   @Override
   public void initialize(CalendarCheck constraintAnnotation) {

   }

   @Override
   public boolean isValid(CalendarDTO value,
						  ConstraintValidatorContext context) {

	  if (value != null && value.getWorkDate().before(DateUtils.shiftToDayStart(new Date()))) {
		 addConstraintViolation(context, ValidationUtilMessages.INCORRECT_CALENDAR_DATE);
		 return false;
	  }
	  return true;
   }
}
