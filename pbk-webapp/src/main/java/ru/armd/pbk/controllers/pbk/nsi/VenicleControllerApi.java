package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.Venicle;
import ru.armd.pbk.dto.nsi.VenicleDTO;
import ru.armd.pbk.services.nsi.VenicleService;

/**
 * rest контроллер для работы с ТС.
 */
@Controller
@RequestMapping(VenicleControllerApi.RM_CONTROLLER_PATH)
public class VenicleControllerApi
	extends BaseVersionControllerApi<Venicle, VenicleDTO> {

   public static final String RM_PATH = "/nsi/venicles";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_VENICLES = "hasAnyRole('DEBUG_TO_REPLACE','VENICLES')";
   static final String PERM_EDIT_VENICLES = "hasAnyRole('DEBUG_TO_REPLACE','VENICLES_EDIT')";


   @Autowired
   VenicleControllerApi(VenicleService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_VENICLES);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_VENICLES);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_VENICLES);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_VENICLES);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_VENICLES);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_HISTRORY, PERM_READ_VENICLES);
	  this.auth.put(ControllerMethods.EDIT_VERSION_DTO, PERM_EDIT_VENICLES);
   }
}
