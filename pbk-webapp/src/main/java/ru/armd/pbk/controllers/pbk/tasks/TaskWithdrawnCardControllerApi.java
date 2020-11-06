package ru.armd.pbk.controllers.pbk.tasks;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.tasks.TaskWithdrawnCard;
import ru.armd.pbk.dto.tasks.TaskWithdrawnCardDTO;
import ru.armd.pbk.enums.core.Legitimate;
import ru.armd.pbk.services.tasks.TaskWithdrawnCardService;
import ru.armd.pbk.utils.json.JsonResult;

import java.util.ArrayList;
import java.util.List;

/**
 * rest контроллер заданий.
 */
@Controller
@RequestMapping(TaskWithdrawnCardControllerApi.RM_CONTROLLER_PATH)
public class TaskWithdrawnCardControllerApi
	extends BaseDomainControllerApi<TaskWithdrawnCard, TaskWithdrawnCardDTO> {

   public static final String RM_PATH = "/pbk/tasks/withdrawn-cards";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_TASK_WITHDRAWN_CARD = "hasAnyRole('DEBUG_TO_REPLACE','WITHDRAWN_CARDS')";
   static final String PERM_EDIT_TASK_WITHDRAWN_CARD = "hasAnyRole('DEBUG_TO_REPLACE','WITHDRAWN_CARDS_EDIT')";

   @Autowired
   TaskWithdrawnCardControllerApi(TaskWithdrawnCardService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_TASK_WITHDRAWN_CARD);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_TASK_WITHDRAWN_CARD);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_TASK_WITHDRAWN_CARD);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_TASK_WITHDRAWN_CARD);
	  this.auth.put(ControllerMethods.DELETE, PERM_EDIT_TASK_WITHDRAWN_CARD);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }

   /**
	* Метод получения списка легитимности
	*
	* @return список легитимности
	*/
   @RequestMapping(value = "/legitimate/list", method = RequestMethod.GET)
   @PreAuthorize(PERM_READ_TASK_WITHDRAWN_CARD)
   @ResponseBody
   public ResponseEntity<?> getLegitimates() {
	  List<ImmutablePair> legitimates = new ArrayList<>();

	  for (Legitimate legitimate : Legitimate.values()) {
		 legitimates.add(new ImmutablePair(
			 String.valueOf(legitimate.getValue()),
			 legitimate.getCaption()));
	  }

	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(legitimates);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

}
