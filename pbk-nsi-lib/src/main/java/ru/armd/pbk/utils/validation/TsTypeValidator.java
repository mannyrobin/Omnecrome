package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.TsTypeDTO;
import ru.armd.pbk.mappers.nsi.TsTypeMapper;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация аннотации проверки типов ТС.
 */
public class TsTypeValidator
	extends BaseValidator
	implements ConstraintValidator<ITsTypeValidator, TsTypeDTO> {

   @Autowired
   private TsTypeMapper mapper;

   @Override
   public void initialize(ITsTypeValidator constraintAnnotation) {
   }

   @Override
   public boolean isValid(TsTypeDTO value, ConstraintValidatorContext context) {
	  Boolean isValid = true;
	  if (value != null) {
		 Map<String, Object> filter = new HashMap<>();
		 filter.put("ID", value.getId());
		 filter.put("code", value.getCod());
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_COD);
			isValid = false;
		 }
	  }
	  return isValid;
   }

}
