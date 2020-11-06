package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.So9Service;

/**
 * Контроллер стандартного отчёта "Сводные данные по работе контролеров по подразделениям".
 */
@Controller
@RequestMapping(So9ControllerApi.RM_CONTROLLER_PATH)
public class So9ControllerApi
	extends SoControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-9";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SO9 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD9')";

   @Autowired
   So9ControllerApi(So9Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO9);
   }
}
