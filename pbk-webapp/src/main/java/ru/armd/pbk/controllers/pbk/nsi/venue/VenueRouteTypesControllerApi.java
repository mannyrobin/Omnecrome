package ru.armd.pbk.controllers.pbk.nsi.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.venue.VenueRouteType;
import ru.armd.pbk.dto.nsi.venue.VenueRouteTypeDTO;
import ru.armd.pbk.services.nsi.venue.VenueRouteTypeService;

/**
 * Контроллер для работы с "Тип сопутствующие маршруты".
 */
@Controller
@RequestMapping(VenueRouteTypesControllerApi.RM_CONTROLLER_PATH)
public class VenueRouteTypesControllerApi
	extends BaseDomainControllerApi<VenueRouteType, VenueRouteTypeDTO> {

   public static final String RM_PATH = "/nsi/venues/route-types";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   VenueRouteTypesControllerApi(VenueRouteTypeService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }

}