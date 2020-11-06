package ru.armd.pbk.controllers.pbk.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.core.AuditLog;
import ru.armd.pbk.services.core.AuditLogService;

/**
 * Контроллер очистки логов аудита.
 */
@Controller
@RequestMapping(AuditsLogControllerApi.RM_CONTROLLER_PATH)
public class AuditsLogControllerApi
	extends BaseDomainControllerApi<AuditLog, BaseDTO> {

   public static final String RM_PATH = "/admin/audit-logs";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   static final String PERM_READ_AUDIT = "hasAnyRole('DEBUG_TO_REPLACE','ADMIN_AUDIT')";

   @Autowired
   AuditsLogControllerApi(AuditLogService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_AUDIT);
   }
}
