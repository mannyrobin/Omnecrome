package ru.armd.pbk.controllers.pbk.nsi.askp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.nsi.askp.AskpAnalysisByRoutesService;

/**
 * rest контроллер данных из АСКП.
 */
@Controller
@RequestMapping(AskpAnalysisByRoutesControllerApi.RM_CONTROLLER_PATH)
public class AskpAnalysisByRoutesControllerApi
	extends BaseDomainControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/nsi/askp/analysis/routes";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERMS = "hasAnyRole('DEBUG_TO_REPLACE','TELEMATICS')";

   @Autowired
   AskpAnalysisByRoutesControllerApi(AskpAnalysisByRoutesService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERMS);
   }
}
