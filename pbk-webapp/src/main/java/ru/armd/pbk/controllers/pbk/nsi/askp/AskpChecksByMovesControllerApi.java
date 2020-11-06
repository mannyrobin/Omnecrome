package ru.armd.pbk.controllers.pbk.nsi.askp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.dto.nsi.askp.AskpChecksUpdateDTO;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.services.nsi.askp.AskpChecksByMovesService;
import ru.armd.pbk.utils.json.JsonResult;

import javax.validation.Valid;

/**
 * rest контроллер данных из АСКП.
 */
@Controller
@RequestMapping(AskpChecksByMovesControllerApi.RM_CONTROLLER_PATH)
public class AskpChecksByMovesControllerApi
	extends BaseDomainControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/nsi/askp/checks/moves";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERMS = "hasAnyRole('DEBUG_TO_REPLACE','TELEMATICS')";

   @Autowired
   AskpChecksByMovesControllerApi(AskpChecksByMovesService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERMS);
	  this.auth.put(ControllerMethods.ADD_DTO, PERMS);
   }

   /**
	* Пересчитать аскп.
	*
	* @param dto дто.
	* @return
	* @throws PBKValidationException
	*/
   @RequestMapping(value = "/update", method = RequestMethod.POST)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).ADD_DTO)")
   public ResponseEntity<?> update(@RequestBody @Valid AskpChecksUpdateDTO dto)
	   throws PBKValidationException {
	  ((AskpChecksByMovesService) service).update(dto);
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}
