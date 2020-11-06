package ru.armd.pbk.controllers.viss.gismgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.viss.gismgt.GmPark;
import ru.armd.pbk.dto.viss.gismgt.GmParkDTO;
import ru.armd.pbk.services.viss.gismgt.GmParkService;

/**
 * Контроллер для работы со сущностью "ГИС МГТ парк".
 */
@Controller
@RequestMapping(GmParkControllerApi.RM_CONTROLLER_PATH)
public class GmParkControllerApi
	extends BaseDomainControllerApi<GmPark, GmParkDTO> {

   public static final String RM_PATH = "/viss/gm/parks";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   GmParkControllerApi(GmParkService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }

}
