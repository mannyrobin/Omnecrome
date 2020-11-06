package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.venue.VenueDTO;
import ru.armd.pbk.mappers.nsi.venue.VenueMapper;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация аннотации проверки Мест Встреч.
 */
public class VenueValidator
	extends BaseValidator
	implements
	ConstraintValidator<IVenueValidator, VenueDTO> {

   @Autowired
   private VenueMapper mapper;

   @Override
   public void initialize(IVenueValidator constraintAnnotation) {

   }

   @Override
   public boolean isValid(VenueDTO value, ConstraintValidatorContext context) {
	  boolean valid = true;
	  if (value != null) {
		 Map<String, Object> filter = new HashMap<String, Object>();
		 filter.put("headId", value.getHeadId());
		 filter.put("code", value.getCod());
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_COD);
			valid = false;
		 }

	  }
	  return valid;
   }

}
