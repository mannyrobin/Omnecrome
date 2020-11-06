package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.Duty;
import ru.armd.pbk.dto.nsi.DutyDTO;
import ru.armd.pbk.services.nsi.DutyService;

/**
 * Контроллер нарядов.
 */
@Controller
@RequestMapping(DutyControllerApi.RM_CONTROLLER_PATH)
public class DutyControllerApi
	extends BaseDomainControllerApi<Duty, DutyDTO> {

   public static final String RM_PATH = "/nsi/duties";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_DUTIES = "hasAnyRole('DEBUG_TO_REPLACE','DUTIES')";
   static final String PERM_EDIT_DUTIES = "hasAnyRole('DEBUG_TO_REPLACE','DUTIES_EDIT')";


   @Autowired
   DutyControllerApi(DutyService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_DUTIES);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_DUTIES);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_DUTIES);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_DUTIES);
	  this.auth.put(ControllerMethods.DELETE, PERM_EDIT_DUTIES);
   }

}
