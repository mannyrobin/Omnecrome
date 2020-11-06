package ru.armd.pbk.controllers.viss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.dto.viss.VisExchangeDTO;
import ru.armd.pbk.services.viss.VisExchangeService;

/**
 * rest контроллер для работы с журналом взаимодействия с ВИС.
 */
@Controller
@RequestMapping(VisExchangeControllerApi.RM_CONTROLLER_PATH)
public class VisExchangeControllerApi
	extends BaseDomainControllerApi<VisExchange, VisExchangeDTO> {

   public static final String RM_PATH = "/vis/exchanges";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_VIS_EXCHANGES = "hasAnyRole('DEBUG_TO_REPLACE','VIS_EXCHANGES')";


   @Autowired
   VisExchangeControllerApi(VisExchangeService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_VIS_EXCHANGES);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_VIS_EXCHANGES);
   }

   /**
	* Запустить обмен повторно с ИД {@code id}.
	*
	* @param id ид обмена.
	* @return
	*/
   @ResponseBody
   @RequestMapping(value = "/repeat", method = RequestMethod.GET)
   @PreAuthorize(PERM_READ_VIS_EXCHANGES)
   public ResponseEntity<?> repeat(@RequestParam(value = "id", required = true) Long id) {
	  ((VisExchangeService) service).repeat(id);
	  return new ResponseEntity<>(HttpStatus.OK);
   }
}
