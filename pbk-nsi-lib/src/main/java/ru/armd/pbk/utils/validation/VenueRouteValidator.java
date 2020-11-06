package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.domain.nsi.Route;
import ru.armd.pbk.dto.nsi.venue.VenueRouteDTO;
import ru.armd.pbk.enums.nsi.VenueRouteType;
import ru.armd.pbk.mappers.nsi.RouteMapper;
import ru.armd.pbk.mappers.nsi.venue.VenueRouteMapper;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Валидатор маршрута места встречи.
 */
public class VenueRouteValidator
	extends BaseValidator
	implements
	ConstraintValidator<IVenueRouteValidator, VenueRouteDTO> {

   @Autowired
   private VenueRouteMapper mapper;

   @Autowired
   private RouteMapper routeMapper;

   @Override
   public void initialize(IVenueRouteValidator constraintAnnotation) {
   }

   @Override
   public boolean isValid(VenueRouteDTO value,
						  ConstraintValidatorContext context) {
	  boolean valid = true;
	  String venueRouteType = value.getVenueRouteTypeId().equals(1L) ? VenueRouteType.TO_BASIC.getName() : VenueRouteType.FROM_BASIC.getName();
	  Map<String, Object> filter = new HashMap<String, Object>();
	  filter.put("venueId", value.getVenueId());
	  filter.put("venueRouteTypeId", value.getVenueRouteTypeId());
	  filter.put("districtId", value.getDistrictId());
	  if (value != null) {
		 for (Long routeId : value.getRouteIds()) {
			filter.put("routeId", routeId);
			if (mapper.isExist(filter)) {
			   Route route = routeMapper.getActual(routeId);
			   addConstraintViolation(context, ValidationUtilMessages.INCORRECT_VENUE_ROUTE + "(" + route.getRouteNumber() + ";" + venueRouteType + ")");
			   valid = false;
			}
		 }
	  }
	  return valid;
   }

}
