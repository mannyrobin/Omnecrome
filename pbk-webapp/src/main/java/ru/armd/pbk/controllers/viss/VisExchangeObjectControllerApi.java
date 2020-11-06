package ru.armd.pbk.controllers.viss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.viss.VisExchangeObject;
import ru.armd.pbk.dto.viss.VisExchangeObjectDTO;
import ru.armd.pbk.services.viss.VisExchangeObjectService;

/**
 * rest контроллер для работы с типами взаимодействий с ВИС.
 */
@Controller
@RequestMapping(VisExchangeObjectControllerApi.RM_CONTROLLER_PATH)
public class VisExchangeObjectControllerApi
	extends BaseDomainControllerApi<VisExchangeObject, VisExchangeObjectDTO> {

   public static final String RM_PATH = "/vis/exchange-objects";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_VIS_EXCHANGES = "hasAnyRole('DEBUG_TO_REPLACE','VIS_EXCHANGES_OBJ')";
   static final String PERM_EDIT_VIS_EXCHANGES = "hasAnyRole('DEBUG_TO_REPLACE','VIS_EXCHANGES_OBJ_EDIT')";


   @Autowired
   VisExchangeObjectControllerApi(VisExchangeObjectService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_VIS_EXCHANGES);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_VIS_EXCHANGES);
   }
}
