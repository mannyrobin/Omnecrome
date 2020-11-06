package ru.armd.pbk.controllers.pbk.tasks;

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
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.tasks.TaskState;
import ru.armd.pbk.dto.tasks.TaskStateDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.tasks.TaskStateService;
import ru.armd.pbk.utils.json.JsonResult;

import java.util.List;

/**
 * rest контроллер состояний заданий.
 */
@Controller
@RequestMapping(TaskStatesControllerApi.RM_CONTROLLER_PATH)
public class TaskStatesControllerApi
	extends BaseDomainControllerApi<TaskState, TaskStateDTO> {

   public static final String RM_PATH = "/pbk/task-states";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   TaskStatesControllerApi(TaskStateService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }

   /**
	* Получить список объектов для выпадаюшего списка.
	*
	* @param taskIds задания.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/list/group", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
   public ResponseEntity<?> getSelectList(@RequestParam List<Long> taskIds)
	   throws PBKException {
	  List<SelectItem> selItems = ((TaskStateService) service).getSelectListGroup(taskIds);
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}