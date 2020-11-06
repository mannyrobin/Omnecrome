package ru.armd.pbk.controllers.pbk.nsi.branch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.branch.Branch;
import ru.armd.pbk.dto.nsi.branch.BranchDTO;
import ru.armd.pbk.services.nsi.branch.BranchService;

/**
 * rest контроллер филиалов.
 */
@Controller
@RequestMapping(BranchControllerApi.RM_CONTROLLER_PATH)
public class BranchControllerApi
	extends BaseDomainControllerApi<Branch, BranchDTO> {

   public static final String RM_PATH = "/nsi/branches";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   BranchControllerApi(BranchService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }
}
