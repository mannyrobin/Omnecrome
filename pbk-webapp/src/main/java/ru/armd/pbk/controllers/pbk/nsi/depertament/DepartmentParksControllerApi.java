package ru.armd.pbk.controllers.pbk.nsi.depertament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.department.DepartmentPark;
import ru.armd.pbk.dto.nsi.department.DepartmentParkDTO;
import ru.armd.pbk.services.nsi.department.DepartmentParkService;

/**
 * Контроллер для работы с парками депортаментов.
 */
@Controller
@RequestMapping(DepartmentParksControllerApi.RM_CONTROLLER_PATH)
public class DepartmentParksControllerApi
	extends BaseDomainControllerApi<DepartmentPark, DepartmentParkDTO> {

   public static final String RM_PATH = "/nsi/departments/parks";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   DepartmentParksControllerApi(DepartmentParkService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, DepartmentsControllerApi.PERM_READ_DEPARTMENTS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.ADD_DTO, DepartmentsControllerApi.PERM_EDIT_DEPARTMENTS);
	  this.auth.put(ControllerMethods.DELETE, DepartmentsControllerApi.PERM_EDIT_DEPARTMENTS);
   }

}
