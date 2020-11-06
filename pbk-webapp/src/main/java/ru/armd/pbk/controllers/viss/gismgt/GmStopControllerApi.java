package ru.armd.pbk.controllers.viss.gismgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.viss.gismgt.GmStop;
import ru.armd.pbk.dto.viss.gismgt.GmStopDTO;
import ru.armd.pbk.services.viss.gismgt.GmStopService;

/**
 * Контроллер для работы со сущностью "ГИС МГТ Остановочный пункт".
 */
@Controller
@RequestMapping(GmStopControllerApi.RM_CONTROLLER_PATH)
public class GmStopControllerApi
	extends BaseDomainControllerApi<GmStop, GmStopDTO> {

   public static final String RM_PATH = "/viss/gm/stops";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   GmStopControllerApi(GmStopService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }

}
