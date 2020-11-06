package ru.armd.pbk.controllers.pbk.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.tasks.AskpContactlessCard;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.tasks.AskpContactlessCardService;
import ru.armd.pbk.services.tasks.TasksService;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.utils.json.JsonResult;

/**
 * rest контроллер данных БСК от АСКП.
 */
@Controller
@RequestMapping(AskpContactlessCardControllerApi.RM_CONTROLLER_PATH)
public class AskpContactlessCardControllerApi
	extends BaseDomainControllerApi<AskpContactlessCard, BaseDTO> {

   public static final String RM_PATH = "/pbk/task-contactless-card";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   public static final String RM_BIND = "/bind";

   static final String PERM_READ_TASKS = "hasAnyRole('DEBUG_TO_REPLACE','PBK_TASKS')";

   @Autowired
   private TasksService tasksService;

   @Autowired
   AskpContactlessCardControllerApi(AskpContactlessCardService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_TASKS);
   }

   /**
	* Возвращает список объектов для грида.
	*
	* @param count      количество строк в гриде.
	* @param page       номер страницы грида.
	* @param orderBy    id поля, по которому будет производиться сортировка.
	* @param orderByAsc вид сортировки(asc/desc).
	* @param params     фильтры.
	* @return список объектов для отображения в гриде.
	* @throws PBKException
	*/
   @RequestMapping(value = RM_BASE, method = RequestMethod.GET)
   @ResponseBody
   @Override
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_VIEWS)")
   public ResponseEntity<?> getViews(
	   @RequestParam(value = "rows", required = false, defaultValue = ROWS_DEFAULT_VALUE) Long count,
	   @RequestParam(value = "page", required = false, defaultValue = PAGE_DEFAULT_VALUE) Long page,
	   @RequestParam(value = "sidx", required = false, defaultValue = SIDX_DEFAULT_VALUE) String orderBy,
	   @RequestParam(value = "sord", required = false, defaultValue = SORD_DEFAULT_VALUE) String orderByAsc,
	   @RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  String dateTo = "v_dateTo";
	  params.set(dateTo, (params.get(dateTo).toString().substring(0, params.get(dateTo).toString().length() - 1) + ":59").replace("[", ""));
	  JsonGridData gridData = service
		  .getGridViews(new BaseGridViewParams(page, count, orderBy, orderByAsc, getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(gridData);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получает заголовок вкладки "Проходы по БСК контролера" по id задания.
	*
	* @param taskId id задания
	* @return заголовок вкладки "Проходы по БСК контролера"
	* @throws PBKException
	*/
   @RequestMapping(value = "/title", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getSelectList(@RequestParam(name = "taskId") Long taskId)
	   throws PBKException {
	  String title = ((AskpContactlessCardService) service).getTitle(taskId);
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(title);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Привязать проверку БСК к заданию.
	*
	* @param taskId - ИД задания.
	*/
   @RequestMapping(value = RM_BIND, method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> bind(@RequestParam("taskId") Long taskId) {
	  ((AskpContactlessCardService) service).bind(taskId);
	  tasksService.processASKP(taskId);
	  return new ResponseEntity<>(HttpStatus.OK);
   }

}