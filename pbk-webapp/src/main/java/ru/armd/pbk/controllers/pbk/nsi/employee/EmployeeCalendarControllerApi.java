package ru.armd.pbk.controllers.pbk.nsi.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseControllerApi;
import ru.armd.pbk.services.nsi.employee.EmployeeCalendarService;
import ru.armd.pbk.utils.json.JsonResult;

import java.util.Date;

/**
 * Рест контроллер режима работы сотрудника.
 */
@Controller
@RequestMapping(EmployeeCalendarControllerApi.RM_CONTROLLER_PATH)
public class EmployeeCalendarControllerApi
	extends BaseControllerApi {

   public static final String RM_CONTROLLER_PATH = "/pbk/nsi/employee/calendar";

   @Autowired
   private EmployeeCalendarService employeeCalendarService;

   /**
	* Календарь на период для сотрудника.
	*
	* @param employeeId id сотрудника
	* @param from       с
	* @param to         по
	* @return
	*/
   @RequestMapping(method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize(EmployeesControllerApi.PERM_READ_EMPLOYEES)
   public ResponseEntity<?> getCalendarForPeriod(
	   @RequestParam(value = "employeeId", required = true) Long employeeId,
	   @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") Date from,
	   @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") Date to) {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(employeeCalendarService.getCalendarForPeriod(employeeId, from, to));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}
