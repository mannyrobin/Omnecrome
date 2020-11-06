package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.bonus.BonusPeriodDTO;
import ru.armd.pbk.mappers.nsi.bonus.BonusPeriodMapper;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация аннотации проверки коэфициента премирования.
 */
public class BonusPeriodValidator
	extends BaseValidator
	implements ConstraintValidator<IBonusPeriodValidator, BonusPeriodDTO> {

   @Autowired
   private BonusPeriodMapper mapper;

   @Override
   public void initialize(IBonusPeriodValidator constraintAnnotation) {

   }

   @Override
   public boolean isValid(BonusPeriodDTO value, ConstraintValidatorContext context) {
	  boolean valid = true;
	  if (value != null) {
		 if (value.getCountTo() != null && value.getCountFrom() > value.getCountTo()) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_BONUS_FROM_TO);
			valid = false;
		 }
		 Map<String, Object> filter = new HashMap<String, Object>();
		 filter.put("id", value.getId());
		 filter.put("bonusId", value.getBonusId());
		 filter.put("countFrom", value.getCountFrom());
		 filter.put("countTo", value.getCountTo());
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_BONUS_FROM_TO_OTHER);
			valid = false;
		 }

	  }
	  return valid;
   }
}
