package ru.armd.pbk.utils.validation;

import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.core.SettingDTO;
import ru.armd.pbk.enums.core.Settings;
import ru.armd.pbk.enums.core.ShowDeleteSetting;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Реализация аннотации проверки настройки.
 */
public class SettingValidator
	extends BaseValidator
	implements ConstraintValidator<ISettingValidator, SettingDTO> {

   @Override
   public void initialize(ISettingValidator constraintAnnotation) {
   }

   @Override
   public boolean isValid(SettingDTO value, ConstraintValidatorContext context) {
	  boolean valid = true;
	  if (value != null) {
		 switch (Settings.getEnumById(value.getId())) {

			case SHOW_DELETE:
			   if (value.getValue() == null || value.getValue().isEmpty() || (!value.getValue().equals(ShowDeleteSetting.NO.getName()) && !value.getValue().equals(ShowDeleteSetting.YES.getName()))) {
				  addConstraintViolation(context, ValidationUtilMessages.INCORRECT_SHOW_DELETE_SETTING);
				  valid = false;
			   }
			   break;


			case CLEAN_TIME_LOGS:
			   if (value.getValue() != null && !value.getValue().isEmpty()) {
				  try {
					 Integer intValue = new Integer(value.getValue());
					 if (intValue < 0) {
						addConstraintViolation(context, ValidationUtilMessages.INCORRECT_CLEAN_TIME_LOGS_MORE);
						valid = false;
					 }
				  } catch (NumberFormatException ex) {
					 addConstraintViolation(context, ValidationUtilMessages.INCORRECT_CLEAN_TIME_LOGS);
					 valid = false;
				  }
			   }
			   break;

			case PLAN_PERIOD:
			   if (value.getValue() != null && !value.getValue().isEmpty()) {
				  try {
					 Integer intValue = new Integer(value.getValue());
					 if (intValue <= 0) {
						addConstraintViolation(context, ValidationUtilMessages.INCORRECT_PLAN_PERIOD_SETTING);
						valid = false;
					 }
				  } catch (NumberFormatException ex) {
					 addConstraintViolation(context, ValidationUtilMessages.INCORRECT_PLAN_PERIOD_SETTING);
					 valid = false;
				  }
			   }
			   break;
			default:
			   break;
		 }
	  }
	  return valid;
   }

}