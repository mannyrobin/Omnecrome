package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.dto.nsi.RouteDTO;
import ru.armd.pbk.mappers.nsi.RouteMapper;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация аннотации проверки водителя.
 */
public class RouteValidator
	extends BaseValidator
	implements ConstraintValidator<IRouteValidator, RouteDTO> {

   @Autowired
   private RouteMapper mapper;

   @Override
   public void initialize(IRouteValidator constraintAnnotation) {
   }

   @Override
   public boolean isValid(RouteDTO value, ConstraintValidatorContext context) {
	  boolean valid = true;
	  if (value != null) {
		 Map<String, Object> filter = new HashMap<String, Object>();
		 filter.put("headId", value.getHeadId());
		 filter.put("asduId", value.getAsduRouteId());
		 filter.put("askpCod", value.getAskpRouteCode());
		 filter.put("asmppCod", value.getAsmppRouteCode());
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_ROUTE_ASDU_ID);
			valid = false;
		 }
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_ROUTE_ASKP_COD);
			valid = false;
		 }
		 if (mapper.isExist(filter)) {
			addConstraintViolation(context, ValidationUtilMessages.INCORRECT_ROUTE_ASMPP_COD);
			valid = false;
		 }
	  }
	  return valid;
   }

}