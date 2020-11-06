package ru.armd.pbk.controllers.viss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.viss.VisExchangeState;
import ru.armd.pbk.services.viss.VisExchangeStateService;

/**
 * rest контроллер для работы со статусами взаимодействий с ВИС.
 */
@Controller
@RequestMapping(VisExchangeStateControllerApi.RM_CONTROLLER_PATH)
public class VisExchangeStateControllerApi
	extends BaseDomainControllerApi<VisExchangeState, BaseDTO> {

   public static final String RM_PATH = "/vis/exchange-states";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   VisExchangeStateControllerApi(VisExchangeStateService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }
}
