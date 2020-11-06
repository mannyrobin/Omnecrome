package ru.armd.pbk.controllers.pbk.nsi.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.dto.nsi.employee.EmployeeDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.nsi.employee.EmployeeService;
import ru.armd.pbk.services.plans.employees.PlanEmployeeService;
import ru.armd.pbk.utils.json.JsonResult;
import ru.armd.pbk.views.nsi.route.RouteSelectItem;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.List;

/**
 * Контроллер сотрудников.
 */

@Controller
@RequestMapping(EmployeesControllerApi.RM_CONTROLLER_PATH)
public class EmployeesControllerApi
	extends BaseVersionControllerApi<Employee, EmployeeDTO> {

   public static final String RM_PATH = "/nsi/employees";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_EMPLOYEES = "hasAnyRole('DEBUG_TO_REPLACE','EMPLOYEES')";
   static final String PERM_EDIT_EMPLOYEES = "hasAnyRole('DEBUG_TO_REPLACE','EMPLOYEES_EDIT')";
   static final String PERM_READ_EDIT_USERS = "hasAnyRole('DEBUG_TO_REPLACE','ADMIN_USER_EDIT')";

   @Autowired
   private PlanEmployeeService planEmployeeService;

   @Autowired
   EmployeesControllerApi(EmployeeService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_EMPLOYEES);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_EMPLOYEES);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_EMPLOYEES);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_EMPLOYEES);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_EMPLOYEES);
	  this.auth.put(ControllerMethods.GET_HISTRORY, PERM_READ_EMPLOYEES);
	  this.auth.put(ControllerMethods.EDIT_VERSION_DTO, PERM_EDIT_EMPLOYEES);
	  this.auth.put(ControllerMethods.ADD_DELETE_PHOTO, PERM_READ_EDIT_USERS);
   }

   /**
	* Получить смену расписания по id задания.
	*
	* @param id id задания
	* @return смену расписания по параметрам
	* @throws PBKException
	*/
   @RequestMapping(value = "/get-by-task/{id}", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getScheduleByTaskId(@PathVariable("id") Long id)
	   throws PBKException {
	  EmployeeDTO result = ((EmployeeService) service).getEmployeeByTaskId(id);
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(result);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Возвращает список сотрудников МГТ, работающих в тот же день.
	*
	* @param id id задания
	* @return список сотрудников
	* @throws PBKException
	*/
   @RequestMapping(value = "/get-on-task-date/{id}", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getEmployeesOnDate(@PathVariable("id") Long id)
	   throws PBKException {
	  List<RouteSelectItem> result = ((EmployeeService) service).getEmployeesOnDate(id);
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(result);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   @Override
   @RequestMapping(value = RM_BASE_VERSION, method = RequestMethod.PUT)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).EDIT_VERSION_DTO)")
   public ResponseEntity<?> updateVersionDTO(@Valid @RequestBody EmployeeDTO dto)
	   throws PBKException {
	   Calendar startDate = Calendar.getInstance();
	   startDate.setTime(dto.getVersionStartDate());
	   startDate.set(Calendar.HOUR_OF_DAY, 23);
	   Calendar today = Calendar.getInstance();
   	  if (startDate.before(today)) {
   	  	throw new PBKException("Изменения карточки задним числом запрещены");
	  }
	  ResponseEntity<?> result = super.updateVersionDTO(dto);
	  fireEmployee(dto);
	  return result;
   }

   @Override
   @RequestMapping(value = RM_BASE, method = RequestMethod.PUT)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).EDIT_DTO)")
   public ResponseEntity<?> updateDTO(@Valid @RequestBody EmployeeDTO dto)
	   throws PBKException {
	  ResponseEntity<?> result = super.updateDTO(dto);
	  fireEmployee(dto);
	  return result;
   }

   protected void fireEmployee(EmployeeDTO dto) {
	  if (dto.getFireDate() != null) {
		 planEmployeeService.fireEmployee(dto.getHeadId(), dto.getDepartmentId(), dto.getFireDate());
	  }
   }

   /**
	* Получить список объектов для выпадаюшего списка поля подписант.
	*
	* @param params фильтры.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = RM_BASE_SLIST + "_sign", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
   public ResponseEntity<?> getSelectItemsForSign(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<SelectItem> selItems = ((EmployeeService) service).getSelectItemsForSign(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

	@RequestMapping(value = "/get-photo-by-id/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult getPhotoByDomainId(@PathVariable("id") Long id, HttpServletResponse response)
			throws PBKException, IOException {
		Employee employee = ((EmployeeService) service).getPhotoByDomainId(id);
		JsonResult jsonResult = new JsonResult();
		if (employee != null)
			jsonResult.setResult(new String(employee.getPhoto(), StandardCharsets.UTF_8));
		return jsonResult;
	}

	@RequestMapping(value = "/delete-photo/{id}", method = RequestMethod.GET)
	@ResponseBody
	@PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).ADD_DELETE_PHOTO)")
	public void deletePhotoByDomainId(@PathVariable("id") Long id){
		((EmployeeService) service).deletePhoto(id);
	}

	@RequestMapping(value = "/add-photo/{id}", method = RequestMethod.POST)
	@ResponseBody
	@PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).ADD_DELETE_PHOTO)")
	public void addPhotoByDomainId(@PathVariable("id") Long id, @RequestBody String photo) throws IOException {
		byte[] bytePhoto = photo.getBytes();

		((EmployeeService) service).addPhoto(id, bytePhoto);
	}
}
