package ru.armd.pbk.controllers.pbk.plans;

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
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.plans.schedules.ScheduleShift;
import ru.armd.pbk.dto.plans.schedules.ScheduleShiftDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.plans.schedules.PlanScheduleService;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.utils.json.JsonResult;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Контроллер расписаний планов отдела.
 */
@Controller
@RequestMapping(PlanScheduleControllerApi.RM_CONTROLLER_PATH)
public class PlanScheduleControllerApi
	extends BaseDomainControllerApi<ScheduleShift, ScheduleShiftDTO> {

   public static final String RM_PATH = "/pbk/plan/schedules";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   PlanScheduleControllerApi(PlanScheduleService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PlansControllerApi.PERM_READ_PLANS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_ALLOW_ALL);
   }

   @Override
   @RequestMapping(value = RM_BASE, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_VIEWS)")
   public ResponseEntity<?> getViews(
	   @RequestParam(value = "rows", required = false, defaultValue = ROWS_DEFAULT_VALUE) Long count,
	   @RequestParam(value = "page", required = false, defaultValue = PAGE_DEFAULT_VALUE) Long page,
	   @RequestParam(value = "sidx", required = false, defaultValue = SIDX_DEFAULT_VALUE) String orderBy,
	   @RequestParam(value = "sord", required = false, defaultValue = SORD_DEFAULT_VALUE) String orderByAsc,
	   @RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	  String fromDate = params.getFirst("d_dateFrom");
	  String toDate = params.getFirst("d_dateTo");
	  JsonResult jsonResult = new JsonResult();
	  try {
		 if (df.parse(fromDate).getTime() > df.parse(toDate).getTime()) {
			jsonResult.setResult(new JsonGridData(null, 0, 0, 1));
			return new ResponseEntity<>(jsonResult, HttpStatus.OK);
		 }
	  } catch (Exception ex) {
		 jsonResult.setResult(new JsonGridData(null, 0, 0, 1));
		 return new ResponseEntity<>(jsonResult, HttpStatus.OK);
	  }
	  JsonGridData gridData = service
		  .getGridViews(new BaseGridViewParams(page, count, orderBy, orderByAsc, getFilterMap(params)));
	  jsonResult.setResult(gridData);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получить список объектов смен - выходных для выпадаюшего списка.
	*
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/holiday-shifts", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getHolidayShifts()
	   throws PBKException {
	  List<SelectItem> selItems = ((PlanScheduleService) service).getHolidayShifts();
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получить смену расписания по параметрам.
	*
	* @param params параметры
	* @return смену расписания по параметрам
	* @throws PBKException
	*/
   @RequestMapping(value = "/get", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getSchedule(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  ScheduleShiftDTO result = ((PlanScheduleService) service)
		  .getSchedule(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(result);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
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
	  ScheduleShiftDTO result = ((PlanScheduleService) service).getScheduleByTaskId(id);
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(result);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получение списка расписания для отображения в комбобоксах при создании
	* заданий.
	*
	* @param params Параметры фильтрации.
	* @return списка расписания.
	* @throws PBKException
	*/
   @RequestMapping(value = RM_BASE_SLIST + "-create-task", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
   public ResponseEntity<?> getSelectItemsForCreatingTask(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<SelectItem> selItems = ((PlanScheduleService) service)
		  .getSelectItemsForCreatingTask(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получение списка расписания для отображения в комбобоксах при создании
	* заданий.
	*
	* @param params Параметры фильтрации.
	* @return списка расписания.
	* @throws PBKException
	*/
   @RequestMapping(value = RM_BASE_SLIST + "-employee-without-schedule", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
   public ResponseEntity<?> getEmployeesWithoutSchedule(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<SelectItem> selItems = ((PlanScheduleService) service)
		  .getEmployeesWithoutSchedule(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}
