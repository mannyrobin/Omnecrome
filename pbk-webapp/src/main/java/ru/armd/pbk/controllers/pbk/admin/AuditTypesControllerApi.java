package ru.armd.pbk.controllers.pbk.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.core.AuditType;
import ru.armd.pbk.dto.core.AuditTypeDTO;
import ru.armd.pbk.services.core.AuditTypeService;

/**
 * Контроллер типов аудита.
 */
@Controller
@RequestMapping(AuditTypesControllerApi.RM_CONTROLLER_PATH)
public class AuditTypesControllerApi
	extends BaseDomainControllerApi<AuditType, AuditTypeDTO> {

   public static final String RM_PATH = "/admin/audit-types";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_AUDIT_TYPES = "hasAnyRole('DEBUG_TO_REPLACE','ADMIN_AUDIT_TYPES')";

   @Autowired
   AuditTypesControllerApi(AuditTypeService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_AUDIT_TYPES);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_AUDIT_TYPES);
   }

}
