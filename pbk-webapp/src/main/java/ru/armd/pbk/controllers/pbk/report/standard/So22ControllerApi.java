package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.So22Service;

/**
 * Контроллер стандартного отчёта "Сверка с ГКУ ОП".
 */
@Controller
@RequestMapping(So22ControllerApi.RM_CONTROLLER_PATH)
public class So22ControllerApi
	extends SoControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-22";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SO22 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD22')";

   @Autowired
   So22ControllerApi(So22Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO22);
   }
}
