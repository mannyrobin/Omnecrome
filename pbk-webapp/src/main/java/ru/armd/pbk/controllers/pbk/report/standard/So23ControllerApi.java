package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;

import ru.armd.pbk.services.So23Service;

/**
 * Контроллер стандартного отчёта "Сводные данные по работе контролеров за период".
 */
@Controller
@RequestMapping(So23ControllerApi.RM_CONTROLLER_PATH)
public class So23ControllerApi
	extends SoControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-23";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SO23 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD23')";

   @Autowired
   So23ControllerApi(So23Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO23);
   }
}
