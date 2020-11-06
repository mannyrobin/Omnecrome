package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.So5Service;

/**
 * Контроллер стандартного отчёта "Посменная численность контролёров ГУП "Мосгортранс" и среднее
 * значение численности за период".
 */
@Controller
@RequestMapping(So5ControllerApi.RM_CONTROLLER_PATH)
public class So5ControllerApi
	extends SoControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-5";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SO5 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD5')";

   @Autowired
   So5ControllerApi(So5Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO5);
   }
}
