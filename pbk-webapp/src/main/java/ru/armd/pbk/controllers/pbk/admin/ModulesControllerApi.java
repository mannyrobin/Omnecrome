package ru.armd.pbk.controllers.pbk.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.core.Module;
import ru.armd.pbk.dto.core.ModuleDTO;
import ru.armd.pbk.services.core.ModuleService;

/**
 * Контроллер модулей.
 */
@Controller
@RequestMapping(ModulesControllerApi.RM_CONTROLLER_PATH)
public class ModulesControllerApi
	extends BaseDomainControllerApi<Module, ModuleDTO> {

   public static final String RM_PATH = "/admin/modules";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_AUDIT_TYPES = "hasAnyRole('DEBUG_TO_REPLACE','ADMIN_AUDIT_TYPES')";

   @Autowired
   ModulesControllerApi(ModuleService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_ALLOW_ALL);
   }

}
