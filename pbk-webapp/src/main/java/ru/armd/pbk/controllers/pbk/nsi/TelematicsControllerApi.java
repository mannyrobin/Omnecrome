package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.So17Service;

/**
 * Контроллер телематики.
 */
@Controller
@RequestMapping(TelematicsControllerApi.RM_CONTROLLER_PATH)
public class TelematicsControllerApi
	extends BaseDomainControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/analis/passengerflow";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_TELEMATICS = "hasAnyRole('DEBUG_TO_REPLACE','TELEMATICS')";
   static final String PERM_EDIT_TELEMATICS = "hasAnyRole('DEBUG_TO_REPLACE','TELEMATICS_EDIT')";


   @Autowired
   TelematicsControllerApi(So17Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_TELEMATICS);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_TELEMATICS);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_TELEMATICS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_TELEMATICS);
	  this.auth.put(ControllerMethods.DELETE, PERM_EDIT_TELEMATICS);
   }

}
