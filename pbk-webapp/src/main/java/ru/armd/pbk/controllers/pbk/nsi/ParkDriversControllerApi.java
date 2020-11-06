package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.ParkDriver;
import ru.armd.pbk.dto.nsi.ParkDriverDTO;
import ru.armd.pbk.services.nsi.ParkDriverService;

/**
 * Контроллер для работы с водителями парков.
 */
@Controller
@RequestMapping(ParkDriversControllerApi.RM_CONTROLLER_PATH)
public class ParkDriversControllerApi
	extends BaseVersionControllerApi<ParkDriver, ParkDriverDTO> {

   public static final String RM_PATH = "/nsi/parks/drivers";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   ParkDriversControllerApi(ParkDriverService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, ParksControllerApi.PERM_READ_PARKS);
	  this.auth.put(ControllerMethods.ADD_DTO, ParksControllerApi.PERM_EDIT_PARKS);
	  this.auth.put(ControllerMethods.DELETE_SOFT, ParksControllerApi.PERM_EDIT_PARKS);
   }

}