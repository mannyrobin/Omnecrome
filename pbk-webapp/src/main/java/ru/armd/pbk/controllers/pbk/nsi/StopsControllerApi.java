package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.stop.Stop;
import ru.armd.pbk.dto.nsi.StopDTO;
import ru.armd.pbk.services.nsi.StopService;

/**
 * Контроллер остановочных пунктов.
 */
@Controller
@RequestMapping(StopsControllerApi.RM_CONTROLLER_PATH)
public class StopsControllerApi
	extends BaseVersionControllerApi<Stop, StopDTO> {

   public static final String RM_PATH = "/nsi/stops";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_STOPS = "hasAnyRole('DEBUG_TO_REPLACE','STOPS')";
   static final String PERM_EDIT_STOPS = "hasAnyRole('DEBUG_TO_REPLACE','STOPS_EDIT')";

   @Autowired
   StopsControllerApi(StopService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_STOPS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_STOPS);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_STOPS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_STOPS);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_STOPS);
	  this.auth.put(ControllerMethods.GET_HISTRORY, PERM_READ_STOPS);
	  this.auth.put(ControllerMethods.EDIT_VERSION_DTO, PERM_EDIT_STOPS);
   }

}
