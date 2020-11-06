package ru.armd.pbk.controllers.pbk.nsi.bsotype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.bsotype.BsoType;
import ru.armd.pbk.dto.nsi.bsotype.BsoTypeDTO;
import ru.armd.pbk.services.nsi.bsotype.BsoTypesService;

/**
 * rest контроллер типов БСО.
 */
@Controller
@RequestMapping(BsoTypesControllerApi.RM_CONTROLLER_PATH)
public class BsoTypesControllerApi
	extends BaseDomainControllerApi<BsoType, BsoTypeDTO> {

   public static final String RM_PATH = "/nsi/bso-types";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   BsoTypesControllerApi(BsoTypesService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }
}
