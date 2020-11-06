package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.So10Service;

/**
 * Контроллер стандартного отчёта "Сводная форма по эффективности работы контролеров".
 */
@Controller
@RequestMapping(So10ControllerApi.RM_CONTROLLER_PATH)
public class So10ControllerApi
	extends BaseDomainControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-10";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SO10 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD10')";

   @Autowired
   So10ControllerApi(So10Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO10);
   }
}
