package ru.armd.pbk.controllers.pbk.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.tasks.TaskTypeService;

/**
 * rest контроллер типов заданий.
 */
@Controller
@RequestMapping(TasksTypeControllerApi.RM_CONTROLLER_PATH)
public class TasksTypeControllerApi
	extends BaseDomainControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/pbk/tasks/types";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   TasksTypeControllerApi(TaskTypeService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }
}
