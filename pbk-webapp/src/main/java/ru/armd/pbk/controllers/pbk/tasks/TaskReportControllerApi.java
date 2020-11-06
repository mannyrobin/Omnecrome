package ru.armd.pbk.controllers.pbk.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.tasks.TaskReport;
import ru.armd.pbk.dto.tasks.TaskReportDTO;
import ru.armd.pbk.services.tasks.TaskReportService;

/**
 * rest контроллер отчётов по заданиям.
 */
@Controller
@RequestMapping(TaskReportControllerApi.RM_CONTROLLER_PATH)
public class TaskReportControllerApi
	extends BaseDomainControllerApi<TaskReport, TaskReportDTO> {

   public static final String RM_PATH = "/pbk/task-reports";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   TaskReportControllerApi(TaskReportService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_DTO, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_ALLOW_ALL);
   }
}
