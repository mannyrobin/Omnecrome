package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.So7Service;

/**
 * Контроллер стандартного отчёта "Количество перевезенных пассажиров по маршрутам".
 */
@Controller
@RequestMapping(So7ControllerApi.RM_CONTROLLER_PATH)
public class So7ControllerApi
	extends BaseDomainControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-7";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SO7 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD7')";

   @Autowired
   So7ControllerApi(So7Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO7);
   }
}
