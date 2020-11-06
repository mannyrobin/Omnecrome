package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.TaskReportField;
import ru.armd.pbk.dto.nsi.TaskReportFieldDTO;
import ru.armd.pbk.services.nsi.TaskReportFieldService;

/**
 * Контроллер для "Виды операций".
 */
@Controller
@RequestMapping(TaskReportFieldControllerApi.RM_CONTROLLER_PATH)
public class TaskReportFieldControllerApi
	extends BaseDomainControllerApi<TaskReportField, TaskReportFieldDTO> {

   public static final String RM_PATH = "/nsi/task-report-fields";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   static final String PERM_READ_OPERATION_TYPES = "hasAnyRole('DEBUG_TO_REPLACE','OPERATION_TYPES')";


   @Autowired
   TaskReportFieldControllerApi(TaskReportFieldService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_OPERATION_TYPES);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_OPERATION_TYPES);
   }
}

