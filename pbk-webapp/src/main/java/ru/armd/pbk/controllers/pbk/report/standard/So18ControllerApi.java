package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.So18Service;

/**
 * Контроллер стандартного отчёта "Сопоставительный анализ данных по наряд-заданию и из АСКП".
 */
@Controller
@RequestMapping(So18ControllerApi.RM_CONTROLLER_PATH)
public class So18ControllerApi
	extends SoControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-18";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SO18 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD18')";

   @Autowired
   So18ControllerApi(So18Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO18);
   }
}
