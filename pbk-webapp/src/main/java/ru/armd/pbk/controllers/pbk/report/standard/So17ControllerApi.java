package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.So17Service;

/**
 * Контроллер стандартного отчёта "Сводный сравнительный анализ данных пассажиропотока (АСКП/АСМ-ПП)".
 */
@Controller
@RequestMapping(So17ControllerApi.RM_CONTROLLER_PATH)
public class So17ControllerApi
	extends BaseDomainControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-17";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SO17 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD17')";

   @Autowired
   So17ControllerApi(So17Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO17);
   }
}
