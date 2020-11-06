package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.department.DepartmentDTO;
import ru.armd.pbk.mappers.nsi.department.DepartmentMapper;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация аннотации валидатора для ID в ЕАСУ ФХД подразделений.
 */
public class DepartmentValidator
	extends BaseValidator
	implements
	ConstraintValidator<IDepartmentValidator, DepartmentDTO> {

   @Autowired
   private DepartmentMapper mapper;

   @Override
   public void initialize(IDepartmentValidator constraintAnnotation) {

   }

   @Override
   public boolean isValid(DepartmentDTO value,
						  ConstraintValidatorContext context) {
	  boolean valid = true;
	  if (value != null) {
		 Map<String, Object> filter = new HashMap<String, Object>();
		 filter.put("headId", value.getHeadId());
		 filter.put("easuFhdId", value.getEasuFhdId());
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_EASU_FHD_ID);
			valid = false;
		 }

	  }
	  return valid;
   }

}
