package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.Park;
import ru.armd.pbk.dto.nsi.ParkDTO;
import ru.armd.pbk.services.nsi.ParkService;

/**
 * Контроллер парков.
 */
@Controller
@RequestMapping(ParksControllerApi.RM_CONTROLLER_PATH)
public class ParksControllerApi
	extends BaseVersionControllerApi<Park, ParkDTO> {

   public static final String RM_PATH = "/nsi/parks";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_PARKS = "hasAnyRole('DEBUG_TO_REPLACE','PARKS')";
   static final String PERM_EDIT_PARKS = "hasAnyRole('DEBUG_TO_REPLACE','PARKS_EDIT')";

   @Autowired
   ParksControllerApi(ParkService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_PARKS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_PARKS);
	  this.auth.put(ControllerMethods.DELETE, PERM_EDIT_PARKS);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_PARKS);
	  this.auth.put(ControllerMethods.GET_HISTRORY, PERM_READ_PARKS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_PARKS);
	  this.auth.put(ControllerMethods.EDIT_VERSION_DTO, PERM_EDIT_PARKS);
   }

}
