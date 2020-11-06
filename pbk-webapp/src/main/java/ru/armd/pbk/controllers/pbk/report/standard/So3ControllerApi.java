package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.So3Service;

/**
 * Контроллер стандартного отчёта "Количество бригад по местам встречи".
 */
@Controller
@RequestMapping(So3ControllerApi.RM_CONTROLLER_PATH)
public class So3ControllerApi
	extends SoControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-3";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SO3 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD3')";

   @Autowired
   So3ControllerApi(So3Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO3);
   }
}
