package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.So12Service;

/**
 * Контроллер стандартного отчёта "Совместный график по местам встреч".
 */
@Controller
@RequestMapping(So12ControllerApi.RM_CONTROLLER_PATH)
public class So12ControllerApi
	extends SoControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-12";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   public static final String RM_BASE_TOTAL = "/total";

   static final String PERM_READ_SO12 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD12')";

   @Autowired
   So12ControllerApi(So12Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO12);
   }

}
