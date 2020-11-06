package ru.armd.pbk.controllers.viss.gismgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.viss.gismgt.GmDistrict;
import ru.armd.pbk.dto.viss.gismgt.GmDistrictDTO;
import ru.armd.pbk.services.viss.gismgt.GmDistrictService;

/**
 * Контроллер для работы со сущностью "ГИС МГТ Округ".
 */
@Controller
@RequestMapping(GmDistrictControllerApi.RM_CONTROLLER_PATH)
public class GmDistrictControllerApi
	extends BaseDomainControllerApi<GmDistrict, GmDistrictDTO> {

   public static final String RM_PATH = "/viss/gm/districts";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   GmDistrictControllerApi(GmDistrictService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }

}
