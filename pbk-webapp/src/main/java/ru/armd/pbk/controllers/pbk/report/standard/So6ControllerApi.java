package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.So6Service;

/**
 * Контроллер стандартного отчёта "Результаты контроля (мотивация)".
 */
@Controller
@RequestMapping(So6ControllerApi.RM_CONTROLLER_PATH)
public class So6ControllerApi
	extends SoControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-6";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   static final String PERM_READ_SO6 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD6')";

   @Autowired
   So6ControllerApi(So6Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO6);
   }
}
