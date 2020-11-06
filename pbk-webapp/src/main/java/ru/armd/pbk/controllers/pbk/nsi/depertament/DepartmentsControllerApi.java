package ru.armd.pbk.controllers.pbk.nsi.depertament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.department.Department;
import ru.armd.pbk.dto.nsi.department.DepartmentDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.nsi.department.DepartmentService;
import ru.armd.pbk.utils.json.JsonResult;

import java.util.List;

/**
 * Контроллер для работы с департаментами.
 */
@Controller
@RequestMapping(DepartmentsControllerApi.RM_CONTROLLER_PATH)
public class DepartmentsControllerApi
	extends BaseVersionControllerApi<Department, DepartmentDTO> {

   public static final String RM_PATH = "/nsi/departments";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_DEPARTMENTS = "hasAnyRole('DEBUG_TO_REPLACE','DEPARTMENTS')";
   static final String PERM_EDIT_DEPARTMENTS = "hasAnyRole('DEBUG_TO_REPLACE','DEPARTMENTS_EDIT')";

   private DepartmentService depService;

   @Autowired
   DepartmentsControllerApi(DepartmentService service) {
	  this.service = service;
	  this.depService = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_DEPARTMENTS);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_DEPARTMENTS);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_DEPARTMENTS);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_DEPARTMENTS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_DEPARTMENTS);
	  this.auth.put(ControllerMethods.GET_HISTRORY, PERM_READ_DEPARTMENTS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.EDIT_VERSION_DTO, PERM_EDIT_DEPARTMENTS);
   }

   /**
	* Получение списка подразделений.
	*
	* @param params параметры
	* @throws PBKException
	*/
   @RequestMapping(value = "/departs-slist", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
   public ResponseEntity<?> getDepartsForBso(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<SelectItem> departs = depService.getSelectItemsByName(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(departs);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получение подразделения по id расписания.
	*
	* @param scheduleId id расписания
	* @return подразделение
	* @throws PBKException
	*/
   @RequestMapping(value = RM_BASE_ID + "/schedule", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
   public ResponseEntity<?> getDepartmentByScheduleId(@PathVariable("id") Long scheduleId)
	   throws PBKException {
	  DepartmentDTO dto = depService.getDepartmentByScheduleId(scheduleId);
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(dto);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получает список подразделений для фильтра карт.
	*
	* @param params параметры
	* @return список подразделений
	* @throws PBKException
	*/
   @RequestMapping(value = "/map-slist", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
   public ResponseEntity<?> getSelectItemForMap(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<SelectItem> departs = depService.getSelectItemForMap(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(departs);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получает подразделение по id места встречи.
	*
	* @param venueId id места встречи
	* @return подразделение
	* @throws PBKException
	*/
   @RequestMapping(value = RM_BASE_ID + "/venue", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
   public ResponseEntity<?> getByVenueId(@PathVariable("id") Long venueId)
	   throws PBKException {
	  DepartmentDTO dto = depService.getByVenueId(venueId);
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(dto);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}