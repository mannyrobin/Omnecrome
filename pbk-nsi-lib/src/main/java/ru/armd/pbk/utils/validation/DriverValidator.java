package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.DriverDTO;
import ru.armd.pbk.mappers.nsi.DriverMapper;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация аннотации проверки водителя.
 */
public class DriverValidator
	extends BaseValidator
	implements ConstraintValidator<IDriverValidator, DriverDTO> {

   @Autowired
   private DriverMapper mapper;

   @Override
   public void initialize(IDriverValidator constraintAnnotation) {
   }

   @Override
   public boolean isValid(DriverDTO value, ConstraintValidatorContext context) {
	  boolean valid = true;
	  if (value != null) {
		 Map<String, Object> filter = new HashMap<String, Object>();
		 filter.put("headId", value.getHeadId());
		 filter.put("asduDriverId", value.getAsduDriverId());
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_DRIVER_ASDU_ID);
			valid = false;
		 }

	  }
	  return valid;
   }

}