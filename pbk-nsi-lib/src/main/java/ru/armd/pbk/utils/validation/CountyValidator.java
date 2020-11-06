package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.CountyDTO;
import ru.armd.pbk.mappers.nsi.CountyMapper;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация аннотации проверки округа.
 */
public class CountyValidator
	extends BaseValidator
	implements ConstraintValidator<ICountyValidator, CountyDTO> {

   @Autowired
   private CountyMapper mapper;

   @Override
   public void initialize(ICountyValidator constraintAnnotation) {
   }

   @Override
   public boolean isValid(CountyDTO value, ConstraintValidatorContext context) {
	  boolean valid = true;
	  if (value != null) {
		 Map<String, Object> filter = new HashMap<String, Object>();
		 filter.put("headId", value.getHeadId());
		 filter.put("cod", value.getCod());
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_COD);
			valid = false;
		 }

	  }
	  return valid;
   }

}