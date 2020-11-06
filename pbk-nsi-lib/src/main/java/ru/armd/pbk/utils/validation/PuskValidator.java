package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.PuskDTO;
import ru.armd.pbk.mappers.nsi.PuskMapper;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;


/**
 * Реализация аннотации проверки устройств ПУсК.
 */
public class PuskValidator
	extends BaseValidator
	implements
	ConstraintValidator<IPuskValidator, PuskDTO> {

   @Autowired
   private PuskMapper mapper;

   @Override
   public void initialize(IPuskValidator constraintAnnotation) {

   }

   @Override
   public boolean isValid(PuskDTO value, ConstraintValidatorContext context) {
	  boolean valid = true;
	  if (value != null) {
		 Map<String, Object> filter = new HashMap<String, Object>();
		 filter.put("headId", value.getHeadId());
		 filter.put("number", value.getPuskNumber());
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_PUSK_NUMBER);
			valid = false;
		 }

	  }
	  return valid;
   }

}
