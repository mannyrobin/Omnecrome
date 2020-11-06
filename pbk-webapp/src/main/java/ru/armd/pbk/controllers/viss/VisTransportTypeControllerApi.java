package ru.armd.pbk.controllers.viss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.viss.VisTransportType;
import ru.armd.pbk.services.viss.VisTransportTypeService;

/**
 * rest контроллер для работы с видами транспорта ВИС.
 */
@Controller
@RequestMapping(VisTransportTypeControllerApi.RM_CONTROLLER_PATH)
public class VisTransportTypeControllerApi
	extends BaseDomainControllerApi<VisTransportType, BaseDTO> {

   public static final String RM_PATH = "/vis/transport-types";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   VisTransportTypeControllerApi(VisTransportTypeService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }
}
