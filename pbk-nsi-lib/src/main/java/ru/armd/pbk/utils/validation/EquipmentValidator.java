package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.EquipmentDTO;
import ru.armd.pbk.mappers.nsi.EquipmentMapper;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация аннотации проверки Бортового оборудования.
 */
public class EquipmentValidator
	extends BaseValidator
	implements ConstraintValidator<IEquipmentValidator, EquipmentDTO> {

   @Autowired
   private EquipmentMapper mapper;

   @Override
   public void initialize(IEquipmentValidator constraintAnnotation) {
   }

   @Override
   public boolean isValid(EquipmentDTO value,
						  ConstraintValidatorContext context) {
	  boolean valid = true;
	  if (value != null) {
		 Map<String, Object> filter = new HashMap<String, Object>();
		 filter.put("headId", value.getHeadId());
		 filter.put("asduEquipmentId", value.getAsduEquipmentId());
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_EQUIPMENT_ASDU_ID);
			valid = false;
		 }

	  }
	  return valid;
   }

}
