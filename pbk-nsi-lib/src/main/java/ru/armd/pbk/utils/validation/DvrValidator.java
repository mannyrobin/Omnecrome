package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.DvrDTO;
import ru.armd.pbk.mappers.nsi.DvrMapper;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация аннотации валидатора для Номера видеорегистратора.
 */
public class DvrValidator
	extends BaseValidator
	implements
	ConstraintValidator<IDvrValidator, DvrDTO> {

   @Autowired
   private DvrMapper mapper;

   @Override
   public void initialize(IDvrValidator constraintAnnotation) {
   }

   @Override
   public boolean isValid(DvrDTO value, ConstraintValidatorContext context) {
	  Boolean isValid = true;
	  if (value != null) {
		 Map<String, Object> filter = new HashMap<String, Object>();
		 filter.put("headId", value.getHeadId());
		 filter.put("num", value.getDvrNumber());
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_DVR_NUMBER);
			isValid = false;
		 }
	  }
	  return isValid;
   }

}
