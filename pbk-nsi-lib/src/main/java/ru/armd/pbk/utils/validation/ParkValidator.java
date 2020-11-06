package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.ParkDTO;
import ru.armd.pbk.mappers.nsi.ParkMapper;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация аннотации проверки парка.
 */
public class ParkValidator
	extends BaseValidator
	implements ConstraintValidator<IParkValidator, ParkDTO> {

   @Autowired
   private ParkMapper mapper;

   @Override
   public void initialize(IParkValidator constraintAnnotation) {
   }

   @Override
   public boolean isValid(ParkDTO value, ConstraintValidatorContext context) {
	  boolean valid = true;
	  if (value != null) {
		 Map<String, Object> filter = new HashMap<String, Object>();
		 filter.put("headId", value.getHeadId());
		 filter.put("asduDepotId", value.getAsduDepotId());
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_PARK_ASDU_ID);
			valid = false;
		 }

	  }
	  return valid;
   }

}