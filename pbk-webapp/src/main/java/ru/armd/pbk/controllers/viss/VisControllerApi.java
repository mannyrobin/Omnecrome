package ru.armd.pbk.controllers.viss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.viss.Vis;
import ru.armd.pbk.dto.viss.VisDTO;
import ru.armd.pbk.services.viss.VisService;

/**
 * rest контроллер для работы с ВИС.
 */
@Controller
@RequestMapping(VisControllerApi.RM_CONTROLLER_PATH)
public class VisControllerApi
	extends BaseDomainControllerApi<Vis, VisDTO> {

   public static final String RM_PATH = "/vis/vis";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_VIS = "hasAnyRole('DEBUG_TO_REPLACE','VIS')";
   static final String PERM_EDIT_VIS = "hasAnyRole('DEBUG_TO_REPLACE',VIS_EDIT')";

   @Autowired
   VisControllerApi(VisService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_VIS);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_VIS);
   }
}
