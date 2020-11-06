package ru.armd.pbk.utils.validation;

import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.plans.schedules.ScheduleShiftDTO;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

/**
 * Реализация аннотации проверки смены расписения.
 */
public class ScheduleValidator
	extends BaseValidator
	implements ConstraintValidator<IScheduleValidator, ScheduleShiftDTO> {

   @Override
   public void initialize(IScheduleValidator constraintAnnotation) {
   }

   @Override
   public boolean isValid(ScheduleShiftDTO value, ConstraintValidatorContext context) {
	  boolean valid = true;
	  if (value != null) {
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(value.getPlanDate());
		 cal.set(Calendar.HOUR_OF_DAY, 0);
		 cal.set(Calendar.MINUTE, 0);
		 cal.set(Calendar.SECOND, 0);
		 cal.set(Calendar.MILLISECOND, 0);
		 value.setPlanDate(cal.getTime());
		 if (value.getDateFrom() == null && value.getDateTo() == null) {
			return true;
		 }
		 if (value.getDateFrom() == null) {
			value.setDateFrom(value.getPlanDate());
		 }
		 if (value.getDateTo() == null) {
			value.setDateTo(value.getPlanDate());
		 }
		 if (value.getDateFrom() != null && value.getDateTo() != null && value.getDateFrom().getTime() > value.getDateTo().getTime()) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_SCHEDULE_PERIOD);
			valid = false;
		 }
	  }
	  return valid;
   }

}