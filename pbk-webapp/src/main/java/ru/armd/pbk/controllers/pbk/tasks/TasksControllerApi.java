package ru.armd.pbk.controllers.pbk.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.tasks.Task;
import ru.armd.pbk.dto.tasks.ChangeTasksStatusDTO;
import ru.armd.pbk.dto.tasks.TaskDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.tasks.TasksService;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.utils.json.JsonResult;
import ru.armd.pbk.views.tasks.TaskViewDomain;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * rest контроллер заданий.
 */
@Controller
@RequestMapping(TasksControllerApi.RM_CONTROLLER_PATH)
public class TasksControllerApi
	extends BaseDomainControllerApi<Task, TaskDTO> {

   public static final String RM_PATH = "/pbk/tasks";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   public static final String RM_PROC_TASK = "process-tasks";

   static final String PERM_READ_TASKS = "hasAnyRole('DEBUG_TO_REPLACE','PBK_TASKS')";
   static final String PERM_READ_EDIT_TASKS = "hasAnyRole('DEBUG_TO_REPLACE','PBK_TASKS_EDIT')";
   static final String PERM_READ_CREATE_TASKS = "hasAnyRole('DEBUG_TO_REPLACE','CREATE_TASKS')";

   private TasksService taskService;

   @Autowired
   TasksControllerApi(TasksService service) {
	  this.service = service;
	  this.taskService = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_TASKS);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_READ_CREATE_TASKS);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_TASKS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_READ_EDIT_TASKS);
	  this.auth.put(ControllerMethods.DELETE, PERM_READ_EDIT_TASKS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }

/*   @RequestMapping(value = "/test", method = RequestMethod.GET)
   @ResponseBody
   public void test() {
	   ((TasksService) service).cancelTasksOfFiredEmployees();
   }*/

	@RequestMapping(value = RM_BASE, method = RequestMethod.POST)
	@ResponseBody
	@PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).ADD_DTO)")
	@Override
	public ResponseEntity<?> insertDTO(@Valid @RequestBody TaskDTO taskDto)
			throws PBKException {
		service.saveDTO(taskDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

   /**
	* Возвращает список использованных БСО для указанного задания (taskId в
	* params).
	*
	* @param count      количество строк в гриде.
	* @param page       номер страницы грида.
	* @param orderBy    id поля, по которому будет производиться сортировка.
	* @param orderByAsc вид сортировки(asc/desc).
	* @param params     фильтры.
	* @return список использованных БСО.
	* @throws PBKException
	*/
   @RequestMapping(value = "/bsos-used", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_VIEWS)")
   public ResponseEntity<?> getBsosUsed(
	   @RequestParam(value = "rows", required = false, defaultValue = ROWS_DEFAULT_VALUE) Long count,
	   @RequestParam(value = "page", required = false, defaultValue = PAGE_DEFAULT_VALUE) Long page,
	   @RequestParam(value = "sidx", required = false, defaultValue = SIDX_DEFAULT_VALUE) String orderBy,
	   @RequestParam(value = "sord", required = false, defaultValue = SORD_DEFAULT_VALUE) String orderByAsc,
	   @RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  JsonGridData gridData = taskService
		  .getBsosUsed(new BaseGridViewParams(page, count, orderBy, orderByAsc, getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(gridData);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Выбора статуса задания.
	*
	* @param changeTasksStatusDTO статус.
	* @return
	*/
   @RequestMapping(value = "/changestate")
   @PreAuthorize("hasAnyRole('DEBUG_TO_REPLACE', 'PBK_TASKS_EDIT', 'PBK_TASKS')")
   @ResponseBody
   public ResponseEntity<?> changeStatus(@Valid @RequestBody ChangeTasksStatusDTO changeTasksStatusDTO) {
	  taskService.changeTasksStatus(changeTasksStatusDTO);
	  return new ResponseEntity<>(HttpStatus.OK);
   }

   /**
	* Получить представление для задания с ид {@code id}.
	*
	* @param id ид задания.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/get-by-task/{id}", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getGridViewByTaskId(@PathVariable("id") Long id)
	   throws PBKException {
	  TaskViewDomain result = taskService.getGridViewByTaskId(id);
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(result);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получить типы маршрутов задания.
	*
	* @return
	*/
   @RequestMapping(value = "/task-route-type", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getTaskRouteType() {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(taskService.getTaskRouteType());
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получить маршруты типа {@code typeId}
	* задания с ид {@code taskId}.
	*
	* @param typeId тип маршрута.
	* @param taskId задание.
	* @return
	*/
   @RequestMapping(value = "/task-routes", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getTaskRoutesForType(
	   @RequestParam(value = "typeId", required = false, defaultValue = "0") Long typeId,
	   @RequestParam(value = "taskId") Long taskId) {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(taskService.getTaskRoutesForType(typeId, taskId));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
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
	* Сменить статус задач с "Открыто" на "В работе" при выгрузки отчета.
	*
	* @param ids id заданий
	*/
   @ResponseBody
   @RequestMapping(value = RM_PROC_TASK, method = RequestMethod.GET)
   public void processTask(@RequestParam("ids") List<Long> ids) {
	  for (Long id : ids) {
		 ((TasksService) service).processReport(id);
	  }
   }

   /**
	* Получение дополнительной информации по id задания (место встречи,
	* подразделние).
	*
	* @param taskId id задания
	* @return дополнительная информация по заданию
	*/
   @RequestMapping(value = "/task-additional", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getTaskRoutesForType(@RequestParam(value = "taskId") Long taskId) {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(taskService.getTaskAdditionalInfoById(taskId));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получение списка всех маршрутов для задания, (сопутствующие от, до).
	*
	* @param taskId id задания
	* @return дополнительная информация по заданию
	*/
   @RequestMapping(value = "/task-all-routes", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getAllRoutesForTask(@RequestParam(value = "taskId") Long taskId) {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(taskService.getAllRoutesForTask(taskId));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получение списка районов задания.
	*
	* @param taskId     ИД задания.
	* @param districtId ИД текущего района
	* @return
	*/
   @RequestMapping(value = "/slist-district", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getTaskDistricts(@RequestParam(value = "taskId") Long taskId, @RequestParam(value = "districtId", required = false) Long districtId) {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(taskService.getTaskDistricts(taskId, districtId));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

}
