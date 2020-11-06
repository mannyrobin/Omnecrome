package ru.armd.pbk.controllers.viss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.viss.VisExchangeOperation;
import ru.armd.pbk.services.viss.VisExchangeOperationService;

/**
 * rest контроллер для работы с типами операций с ВИС.
 */
@Controller
@RequestMapping(VisExchangeOperationControllerApi.RM_CONTROLLER_PATH)
public class VisExchangeOperationControllerApi
	extends BaseDomainControllerApi<VisExchangeOperation, BaseDTO> {

   public static final String RM_PATH = "/vis/exchange-operations";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   VisExchangeOperationControllerApi(VisExchangeOperationService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }
}
