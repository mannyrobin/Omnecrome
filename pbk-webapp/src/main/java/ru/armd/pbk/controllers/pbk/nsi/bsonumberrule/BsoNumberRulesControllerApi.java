package ru.armd.pbk.controllers.pbk.nsi.bsonumberrule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.bsonumberrule.BsoNumberRule;
import ru.armd.pbk.dto.nsi.bsonumberrule.BsoNumberRuleDTO;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.services.nsi.bsonumberrule.BsoNumberRulesService;

import java.util.List;

/**
 * rest контроллер правил формирования номеров БСО.
 */
@Controller
@RequestMapping(BsoNumberRulesControllerApi.RM_CONTROLLER_PATH)
public class BsoNumberRulesControllerApi
	extends BaseDomainControllerApi<BsoNumberRule, BsoNumberRuleDTO> {

   public static final String RM_PATH = "/nsi/bso-number-rules";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_BSO_NUM_RULES = "hasAnyRole('DEBUG_TO_REPLACE','BSO_NUMBERS')";
   static final String PERM_EDIT_BSO_NUM_RULES = "hasAnyRole('DEBUG_TO_REPLACE','BSO_NUMBERS_EDIT')";

   BsoNumberRulesService bsoRuleService;

   @Autowired
   BsoNumberRulesControllerApi(BsoNumberRulesService service) {
	  this.service = service;
	  this.bsoRuleService = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_BSO_NUM_RULES);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_BSO_NUM_RULES);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_BSO_NUM_RULES);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_BSO_NUM_RULES);
	  this.auth.put(ControllerMethods.DELETE, PERM_EDIT_BSO_NUM_RULES);
   }

   @Override
   protected void customDeleteValidation(List<Long> ids)
	   throws PBKValidationException {
	  if (ids != null) {
		 for (Long id : ids) {
			if (bsoRuleService.getCountBsoForRule(id) != 0) {
			   throw new PBKValidationException("bsoNumberRule id", "У правила есть сгенерированные БСО!");
			}
		 }
	  }
   }
}
