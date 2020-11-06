package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.So14Service;

/**
 * Контроллер стандартного отчёта "Результаты ПБК за период".
 */
@Controller
@RequestMapping(So14ControllerApi.RM_CONTROLLER_PATH)
public class So14ControllerApi
	extends SoControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-14";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SO14 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD14')";

   @Autowired
   So14ControllerApi(So14Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO14);
   }
}
