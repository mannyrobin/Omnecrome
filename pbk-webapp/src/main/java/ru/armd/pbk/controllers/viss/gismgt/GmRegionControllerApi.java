package ru.armd.pbk.controllers.viss.gismgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.viss.gismgt.GmRegion;
import ru.armd.pbk.dto.viss.gismgt.GmRegionDTO;
import ru.armd.pbk.services.viss.gismgt.GmRegionService;

/**
 * Контроллер для работы со сущностью "ГИС МГТ Район".
 */
@Controller
@RequestMapping(GmRegionControllerApi.RM_CONTROLLER_PATH)
public class GmRegionControllerApi
	extends BaseDomainControllerApi<GmRegion, GmRegionDTO> {

   public static final String RM_PATH = "/viss/gm/regions";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   GmRegionControllerApi(GmRegionService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }

}
