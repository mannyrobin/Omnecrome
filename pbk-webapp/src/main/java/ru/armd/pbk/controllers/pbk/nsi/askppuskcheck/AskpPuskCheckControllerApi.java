package ru.armd.pbk.controllers.pbk.nsi.askppuskcheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.askppuskcheck.AskpPuskCheck;
import ru.armd.pbk.dto.nsi.askppuskcheck.AskpPuskCheckDTO;
import ru.armd.pbk.services.nsi.askppuskcheck.AskpPuskCheckService;
import ru.armd.pbk.services.tasks.TasksService;

/**
 * rest контроллер данных из АСКП.
 */
@Controller
@RequestMapping(AskpPuskCheckControllerApi.RM_CONTROLLER_PATH)
public class AskpPuskCheckControllerApi
	extends BaseDomainControllerApi<AskpPuskCheck, AskpPuskCheckDTO> {

   public static final String RM_PATH = "/nsi/askp-pusk-checks";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   public static final String RM_BIND = "/bind";

   static final String PERM_PUSK_LIST_CHECKS = "hasAnyRole('DEBUG_TO_REPLACE','PBK_TASKS')";
   static final String PERM_PUSK_GRID_CHECKS = "hasAnyRole('DEBUG_TO_REPLACE','PBK_TASKS','ASKP_PUSK_CHECKS')";

   @Autowired
   private TasksService taskService;

   @Autowired
   AskpPuskCheckControllerApi(AskpPuskCheckService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_PUSK_GRID_CHECKS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_PUSK_LIST_CHECKS);
   }

   /**
	* Привязать проверку ПУсК к заданию.
	*
	* @param taskId - ИД задания.
	*/
   @RequestMapping(value = RM_BIND, method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> bind(@RequestParam("taskId") Long taskId) {
	  // Если нужна проверка на аскп > 0, то лучше это вынести в метод изменеия статуса
	  ((AskpPuskCheckService) service).bind(taskId);
	  taskService.processASKP(taskId);
	  return new ResponseEntity<>(HttpStatus.OK);
   }
}
