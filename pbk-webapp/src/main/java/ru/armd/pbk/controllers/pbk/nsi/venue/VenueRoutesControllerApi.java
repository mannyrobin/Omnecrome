package ru.armd.pbk.controllers.pbk.nsi.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.venue.VenueRoute;
import ru.armd.pbk.dto.nsi.venue.VenueRouteDTO;
import ru.armd.pbk.services.nsi.venue.VenueRouteService;

/**
 * Контроллер для работы с "Сопутствующие маршруты".
 */
@Controller
@RequestMapping(VenueRoutesControllerApi.RM_CONTROLLER_PATH)
public class VenueRoutesControllerApi
	extends BaseVersionControllerApi<VenueRoute, VenueRouteDTO> {

   public static final String RM_PATH = "/nsi/venues/routes";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;


   @Autowired
   VenueRoutesControllerApi(VenueRouteService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, VenuesControllerApi.PERM_READ_VENUES);
	  this.auth.put(ControllerMethods.ADD_DTO, VenuesControllerApi.PERM_EDIT_VENUES);
	  this.auth.put(ControllerMethods.DELETE_SOFT, VenuesControllerApi.PERM_EDIT_VENUES);
   }

}