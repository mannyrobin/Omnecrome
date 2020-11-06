package ru.armd.pbk.controllers.pbk.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.core.UserRole;
import ru.armd.pbk.dto.core.RoleDTO;
import ru.armd.pbk.dto.core.UserRoleDTO;
import ru.armd.pbk.enums.core.EmbeddedRole;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.services.core.RoleService;
import ru.armd.pbk.services.core.UserRoleService;

import java.util.List;

/**
 * Контроллер для ролей пользователя.
 */
@Controller
@RequestMapping(UserRolesControllerApi.RM_CONTROLLER_PATH)
public class UserRolesControllerApi
	extends BaseDomainControllerApi<UserRole, UserRoleDTO> {

   static final String RM_PATH = "/admin/users/roles";
   static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String SYSTEM_ROLE = "PBK_USERS_ROLE";

   @Autowired
   private RoleService roleService;

   /**
	* Создаёт контроллер для связок пользователей и ролей.
	*
	* @param service сервис  работы со связками.
	*/
   @Autowired
   UserRolesControllerApi(UserRoleService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, UsersControllerApi.PERM_READ_USERS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.ADD_DTO, UsersControllerApi.PERM_READ_EDIT_USERS);
	  this.auth.put(ControllerMethods.DELETE, UsersControllerApi.PERM_READ_EDIT_USERS);
   }

   @Override
   protected void customDeleteValidation(List<Long> ids)
	   throws PBKValidationException {
	  if (ids != null) {
		 for (Long id : ids) {
			UserRoleDTO userRole = service.getDTOById(id);
			if (userRole != null) {
			   RoleDTO role = roleService.getDTOById(userRole.getRoleId());
			   if (role != null) {
				  if (role.getId().equals(EmbeddedRole.PBK_USERS_ROLE.getId())) {
					 throw new PBKValidationException("role id", "Удаление системной роли недоступно!");
				  }
			   }
			}
		 }
	  }
   }
}
