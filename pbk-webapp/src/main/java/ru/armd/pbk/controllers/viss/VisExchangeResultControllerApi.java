package ru.armd.pbk.controllers.viss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.viss.VisExchangeResult;
import ru.armd.pbk.services.viss.VisExchangeResultService;

/**
 * rest контроллер для работы с записью журнала взаимодействия с ВИС.
 */
@Controller
@RequestMapping(VisExchangeResultControllerApi.RM_CONTROLLER_PATH)
public class VisExchangeResultControllerApi
	extends BaseDomainControllerApi<VisExchangeResult, BaseDTO> {

   public static final String RM_PATH = "/vis/exchanges/results";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   VisExchangeResultControllerApi(VisExchangeResultService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_ALLOW_ALL);
   }
}
