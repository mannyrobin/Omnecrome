package ru.armd.pbk.controllers.pbk.report.askp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.askp.AskpReportByStopService;

/**
 * rest контроллер отчета сводных данных.
 */
@Controller
@RequestMapping(AskpReportByStopControllerApi.RM_CONTROLLER_PATH)
public class AskpReportByStopControllerApi
	extends BaseDomainControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/askp/stops";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERMS = "hasAnyRole('DEBUG_TO_REPLACE','TELEMATICS')";

   @Autowired
   AskpReportByStopControllerApi(AskpReportByStopService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERMS);
   }

}
