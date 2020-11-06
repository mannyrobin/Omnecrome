package ru.armd.pbk.controllers.pbk.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.core.RolePermission;
import ru.armd.pbk.dto.core.RolePermissionDTO;
import ru.armd.pbk.services.core.RolePermissionService;

import java.util.List;

/**
 *
 */
@Controller
@RequestMapping(RolePermissionControllerApi.RM_CONTROLLER_PATH)
public class RolePermissionControllerApi
	extends BaseDomainControllerApi<RolePermission, RolePermissionDTO> {

   public static final String RM_ADD_PERMISSIONS = "/add";
   public static final String RM_REMOVE_PERMISSIONS = "/remove";

   static final String RM_PATH = "/admin/roles/permissions";
   static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;


   @Autowired
   RolePermissionControllerApi(RolePermissionService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, RolesControllerApi.PERM_READ_ROLE);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.ADD_DTO, RolesControllerApi.PERM_EDIT_ROLE);
	  this.auth.put(ControllerMethods.DELETE, RolesControllerApi.PERM_EDIT_ROLE);
   }

   /**
	* Добавить привилегии роли.
	*
	* @param id  - ИД роли.
	* @param ids - ИДС привелегий.
	* @return
	*/
   @RequestMapping(value = RM_ADD_PERMISSIONS, method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> addPermissions(@RequestParam(value = "id") Long id, @RequestParam("ids") List<Long> ids) {
	  ((RolePermissionService) service).addPermissions(id, ids);
	  return new ResponseEntity<>(HttpStatus.OK);
   }

   /**
	* Удалить привилегии роли.
	*
	* @param id  - ИД роли.
	* @param ids - ИДС привелегий.
	* @return
	*/
   @RequestMapping(value = RM_REMOVE_PERMISSIONS, method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> removePermissions(@RequestParam(value = "id") Long id, @RequestParam("ids") List<Long> ids) {
	  ((RolePermissionService) service).removePermissions(id, ids);
	  return new ResponseEntity<>(HttpStatus.OK);
   }

}
