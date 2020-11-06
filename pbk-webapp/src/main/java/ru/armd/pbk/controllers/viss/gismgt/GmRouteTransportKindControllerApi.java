package ru.armd.pbk.controllers.viss.gismgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.viss.gismgt.GmRouteTransportKind;
import ru.armd.pbk.dto.viss.gismgt.GmRouteTransportKindDTO;
import ru.armd.pbk.services.viss.gismgt.GmRouteTransportKindService;

/**
 * Контроллер для работы со сущностью "ГИС МГТ Тип ТС маршрута".
 */
@Controller
@RequestMapping(GmRouteTransportKindControllerApi.RM_CONTROLLER_PATH)
public class GmRouteTransportKindControllerApi
	extends BaseDomainControllerApi<GmRouteTransportKind, GmRouteTransportKindDTO> {

   public static final String RM_PATH = "/viss/gm/route-ts-kinds";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   GmRouteTransportKindControllerApi(GmRouteTransportKindService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }

}
