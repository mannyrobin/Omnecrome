package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.Sex;
import ru.armd.pbk.dto.nsi.SexDTO;
import ru.armd.pbk.services.nsi.SexService;

/**
 * Контроллер для "Пол".
 */
@Controller
@RequestMapping(SexControllerApi.RM_CONTROLLER_PATH)
public class SexControllerApi
	extends BaseDomainControllerApi<Sex, SexDTO> {

   public static final String RM_PATH = "/nsi/sexes";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   static final String PERM_READ_EMPLOYEES_SEX = "hasAnyRole('DEBUG_TO_REPLACE','EMPLOYEES_SEX')";


   @Autowired
   SexControllerApi(SexService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_EMPLOYEES_SEX);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_EMPLOYEES_SEX);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }
}
