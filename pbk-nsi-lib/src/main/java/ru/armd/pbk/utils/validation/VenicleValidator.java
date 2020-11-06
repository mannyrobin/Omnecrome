package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.VenicleDTO;
import ru.armd.pbk.mappers.nsi.VenicleMapper;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация аннотации проверки ТС.
 */
public class VenicleValidator
	extends BaseValidator
	implements ConstraintValidator<IVenicleValidator, VenicleDTO> {

   @Autowired
   private VenicleMapper mapper;

   @Override
   public void initialize(IVenicleValidator constraintAnnotation) {
   }

   @Override
   public boolean isValid(VenicleDTO value, ConstraintValidatorContext context) {
	  boolean valid = true;
	  if (value != null) {
		 Map<String, Object> filter = new HashMap<String, Object>();
		 filter.put("headId", value.getHeadId());
		 filter.put("asduVenicleId", value.getAsduVenicleId());
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_TS_VIS_ASDU_ID);
			valid = false;
		 }

	  }
	  return valid;
   }

}