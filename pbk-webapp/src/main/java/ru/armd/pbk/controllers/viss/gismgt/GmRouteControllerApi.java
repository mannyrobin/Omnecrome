package ru.armd.pbk.controllers.viss.gismgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.viss.gismgt.GmRoute;
import ru.armd.pbk.dto.viss.gismgt.GmRouteDTO;
import ru.armd.pbk.services.viss.gismgt.GmRouteService;

/**
 * Контроллер для работы со сущностью "ГИС МГТ Маршрут".
 */
@Controller
@RequestMapping(GmRouteControllerApi.RM_CONTROLLER_PATH)
public class GmRouteControllerApi
	extends BaseDomainControllerApi<GmRoute, GmRouteDTO> {

   public static final String RM_PATH = "/viss/gm/routes";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   GmRouteControllerApi(GmRouteService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }

}
