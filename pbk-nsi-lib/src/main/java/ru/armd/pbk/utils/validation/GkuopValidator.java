package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.GkuopDTO;
import ru.armd.pbk.mappers.nsi.GkuopMapper;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация аннотации проверки ГКУ ОП.
 */
public class GkuopValidator
	extends BaseValidator
	implements ConstraintValidator<IGkuopValidator, GkuopDTO> {

   @Autowired
   private GkuopMapper mapper;

   @Override
   public void initialize(IGkuopValidator constraintAnnotation) {
   }

   @Override
   public boolean isValid(GkuopDTO value, ConstraintValidatorContext context) {
	  boolean valid = true;
	  if (value != null) {
		 Map<String, Object> filter = new HashMap<String, Object>();
		 filter.put("headId", value.getHeadId());
		 filter.put("personalNumber", value.getPersonalNumber());
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_GKUOP_PERSONAL_NUMBER);
			valid = false;
		 }

	  }
	  return valid;
   }

}