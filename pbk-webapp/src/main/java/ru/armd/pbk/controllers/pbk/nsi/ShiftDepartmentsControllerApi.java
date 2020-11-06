package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.ShiftDepartment;
import ru.armd.pbk.dto.nsi.ShiftDepartmentDTO;
import ru.armd.pbk.services.nsi.ShiftDepartmentService;

/**
 * Контроллер для "Смены подразделения".
 */
@Controller
@RequestMapping(ShiftDepartmentsControllerApi.RM_CONTROLLER_PATH)
public class ShiftDepartmentsControllerApi
	extends BaseDomainControllerApi<ShiftDepartment, ShiftDepartmentDTO> {

   public static final String RM_PATH = "/nsi/shift-departmentes";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SHIFT_DEPARTMENT = "hasAnyRole('DEBUG_TO_REPLACE','SHIFTS')";
   static final String PERM_EDIT_SHIFT_DEPARTMENT = "hasAnyRole('DEBUG_TO_REPLACE','SHIFTS_EDIT')";

   @Autowired
   ShiftDepartmentsControllerApi(ShiftDepartmentService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SHIFT_DEPARTMENT);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_SHIFT_DEPARTMENT);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_SHIFT_DEPARTMENT);
   }
}
