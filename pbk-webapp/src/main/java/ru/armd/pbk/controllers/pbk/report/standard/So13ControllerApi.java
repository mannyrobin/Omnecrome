package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.So13Service;

/**
 * Контроллер стандартного отчёта "Маршруты и выходы".
 */
@Controller
@RequestMapping(So13ControllerApi.RM_CONTROLLER_PATH)
public class So13ControllerApi
	extends BaseDomainControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-13";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SO13 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD13')";

   @Autowired
   So13ControllerApi(So13Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO13);
   }
}
