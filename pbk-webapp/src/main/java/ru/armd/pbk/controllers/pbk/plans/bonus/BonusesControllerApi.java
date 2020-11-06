package ru.armd.pbk.controllers.pbk.plans.bonus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.bonus.Bonus;
import ru.armd.pbk.dto.nsi.bonus.BonusDTO;
import ru.armd.pbk.services.nsi.bonus.BonusService;


/**
 * Контроллер премирования.
 */
@Controller
@RequestMapping(BonusesControllerApi.RM_CONTROLLER_PATH)
public class BonusesControllerApi
	extends BaseDomainControllerApi<Bonus, BonusDTO> {

   public static final String RM_PATH = "/pbk/bonuses";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_BONUSES = "hasAnyRole('DEBUG_TO_REPLACE','BONUSES')";
   static final String PERM_EDIT_BONUSES = "hasAnyRole('DEBUG_TO_REPLACE','BONUSES_EDIT')";


   @Autowired
   BonusesControllerApi(BonusService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_BONUSES);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_READ_BONUSES);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_BONUSES);
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_BONUSES);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_BONUSES);
	  this.auth.put(ControllerMethods.DELETE, PERM_EDIT_BONUSES);
   }

}
