package ru.armd.pbk.controllers.pbk.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.core.Role;
import ru.armd.pbk.dto.core.RoleDTO;
import ru.armd.pbk.enums.core.EmbeddedRole;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.services.core.RoleService;

import java.util.List;

/**
 * rest контроллер для работы с ролями пользователя.
 */
@Controller
@RequestMapping(RolesControllerApi.RM_CONTROLLER_PATH)
public class RolesControllerApi
	extends BaseDomainControllerApi<Role, RoleDTO> {

   public static final String RM_PATH = "/admin/roles";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_ROLE = "hasAnyRole('DEBUG_TO_REPLACE','ADMIN_ROLE')";
   static final String PERM_EDIT_ROLE = "hasAnyRole('DEBUG_TO_REPLACE','ADMIN_ROLE_EDIT')";


   @Autowired
   RolesControllerApi(RoleService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_ROLE);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_ROLE);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_ROLE);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_ROLE);
	  this.auth.put(ControllerMethods.DELETE, PERM_EDIT_ROLE);
   }

   @Override
   protected void customDeleteValidation(List<Long> ids)
	   throws PBKValidationException {
	  if (ids != null) {
		 for (Long id : ids) {
			RoleDTO role = service.getDTOById(id);
			if (role != null) {
			   if (role.getId().equals(EmbeddedRole.PBK_USERS_ROLE.getId())) {
				  throw new PBKValidationException("role id", "Удаление системной роли недоступно!");
			   }
			}
		 }
	  }
   }
}
