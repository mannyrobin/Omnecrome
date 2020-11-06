package ru.armd.pbk.controllers.viss.gismgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.viss.gismgt.GmTransportKind;
import ru.armd.pbk.dto.viss.gismgt.GmTransportKindDTO;
import ru.armd.pbk.services.viss.gismgt.GmTransportKindService;

/**
 * Контроллер для работы со сущностью "ГИС МГТ Тип ТС".
 */
@Controller
@RequestMapping(GmTsTypeControllerApi.RM_CONTROLLER_PATH)
public class GmTsTypeControllerApi
	extends BaseDomainControllerApi<GmTransportKind, GmTransportKindDTO> {

   public static final String RM_PATH = "/viss/gm/ts-types";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   GmTsTypeControllerApi(GmTransportKindService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }

}
