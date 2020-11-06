package ru.armd.pbk.controllers.pbk.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.core.Permission;
import ru.armd.pbk.services.core.PermissionService;

/**
 * rest контроллер для работы с правами.
 */
@Controller
@RequestMapping(PermissionsControllerApi.RM_CONTROLLER_PATH)
public class PermissionsControllerApi
	extends BaseDomainControllerApi<Permission, BaseDTO> {

   public static final String RM_PATH = "/admin/permissions";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   PermissionsControllerApi(PermissionService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_ALLOW_ALL);
   }

}
