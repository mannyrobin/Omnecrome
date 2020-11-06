package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.bonus.BonusDTO;
import ru.armd.pbk.mappers.nsi.bonus.BonusMapper;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация аннотации проверки премирования.
 */
public class BonusValidator
	extends BaseValidator
	implements ConstraintValidator<IBonusValidator, BonusDTO> {

   @Autowired
   private BonusMapper mapper;

   @Override
   public void initialize(IBonusValidator constraintAnnotation) {

   }

   @Override
   public boolean isValid(BonusDTO value, ConstraintValidatorContext context) {
	  boolean valid = true;
	  if (value != null) {
		 if (value.getPeriodStartDate().getTime() > value.getPeriodEndDate().getTime()) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_BONUS_PERIOD_START_END);
			valid = false;
		 }
		 Map<String, Object> filter = new HashMap<String, Object>();
		 filter.put("id", value.getId());
		 filter.put("dateFrom", value.getPeriodStartDate());
		 filter.put("dateTo", value.getPeriodEndDate());
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_BONUS_PERIOD);
			valid = false;
		 }
	  }
	  return valid;
   }
}
