package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.Gkuop;
import ru.armd.pbk.dto.nsi.GkuopDTO;
import ru.armd.pbk.services.nsi.GkuopService;

/**
 * Контроллер ГКУ ОП.
 */

@Controller
@RequestMapping(GkuopControllerApi.RM_CONTROLLER_PATH)
public class GkuopControllerApi
	extends BaseVersionControllerApi<Gkuop, GkuopDTO> {

   public static final String RM_PATH = "/nsi/gkuops";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_GKUOPS_EMPLOYEES = "hasAnyRole('DEBUG_TO_REPLACE','GKUOPS_EMPLOYEES')";
   static final String PERM_EDIT_GKUOPS_EMPLOYEES = "hasAnyRole('DEBUG_TO_REPLACE','GKUOPS_EMPLOYEES_EDIT')";


   @Autowired
   GkuopControllerApi(GkuopService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_GKUOPS_EMPLOYEES);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_GKUOPS_EMPLOYEES);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_GKUOPS_EMPLOYEES);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_GKUOPS_EMPLOYEES);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_GKUOPS_EMPLOYEES);
	  this.auth.put(ControllerMethods.GET_HISTRORY, PERM_READ_GKUOPS_EMPLOYEES);
	  this.auth.put(ControllerMethods.EDIT_VERSION_DTO, PERM_EDIT_GKUOPS_EMPLOYEES);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }
}
