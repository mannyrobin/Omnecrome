package ru.armd.pbk.controllers.viss;

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
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.dto.viss.VisExchangeConfigDTO;
import ru.armd.pbk.dto.viss.VisExchangeStartDTO;
import ru.armd.pbk.services.viss.VisExchangeConfigService;

import javax.validation.Valid;

/**
 * rest контроллер для работы с настройками соединения с ВИС.
 */
@Controller
@RequestMapping(VisExchangeConfigControllerApi.RM_CONTROLLER_PATH)
public class VisExchangeConfigControllerApi
	extends BaseDomainControllerApi<VisExchangeConfig, VisExchangeConfigDTO> {

   public static final String RM_PATH = "/vis/exchange-configs";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_VIS_EXCHANGES_CONF = "hasAnyRole('DEBUG_TO_REPLACE','VIS_EXCHANGES_CONF')";
   static final String PERM_EDIT_VIS_EXCHANGES_CONF = "hasAnyRole('DEBUG_TO_REPLACE','VIS_EXCHANGES_CONF_EDIT')";
   static final String PERM_READ_VIS_EXCHANGES = "hasAnyRole('DEBUG_TO_REPLACE','VIS_EXCHANGES')";


   @Autowired
   VisExchangeConfigControllerApi(VisExchangeConfigService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_VIS_EXCHANGES_CONF);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_VIS_EXCHANGES_CONF);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_VIS_EXCHANGES_CONF);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_VIS_EXCHANGES_CONF);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_VIS_EXCHANGES_CONF);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }

   /**
	* Запустить обмен для конфигурации {@code dto}.
	*
	* @param dto конфигурация.
	* @return
	*/
   @ResponseBody
   @RequestMapping(value = "/start", method = RequestMethod.POST)
   @PreAuthorize(PERM_READ_VIS_EXCHANGES)
   public ResponseEntity<?> repeat(@Valid @RequestBody VisExchangeStartDTO dto) {
	  ((VisExchangeConfigService) service).start(dto.getId(), dto.getParameter(), dto.getStart(), dto.getEnd());
	  return new ResponseEntity<>(HttpStatus.OK);
   }
}
