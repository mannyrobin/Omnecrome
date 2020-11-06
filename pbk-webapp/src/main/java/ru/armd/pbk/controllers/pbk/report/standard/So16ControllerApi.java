package ru.armd.pbk.controllers.pbk.report.standard;

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
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.So16Service;

import java.util.List;

/**
 * Контроллер стандартного отчёта "Сравнительный анализ данных пассажиропотока (АСКП/АСМ-ПП) поостановочно".
 */
@Controller
@RequestMapping(So16ControllerApi.RM_CONTROLLER_PATH)
public class So16ControllerApi
	extends BaseDomainControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-16";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SO16 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD16')";

   @Autowired
   So16ControllerApi(So16Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO16);
   }

   /**
	* Удалить АСМПП для идентификаторов {@code ids}.
	*
	* @param ids идентификаторы
	* @return
	*/
   @RequestMapping(value = "/asmpp", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize(PERM_READ_SO16)
   public ResponseEntity<?> delete(@RequestParam("ids") List<Long> ids)
	   throws PBKException {
	  ((So16Service) service).downloadAsmppData(ids);
	  return new ResponseEntity<>(HttpStatus.OK);
   }
}
