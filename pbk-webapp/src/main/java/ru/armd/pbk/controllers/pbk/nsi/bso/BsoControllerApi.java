package ru.armd.pbk.controllers.pbk.nsi.bso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.bso.Bso;
import ru.armd.pbk.domain.nsi.bso.TrashBso;
import ru.armd.pbk.domain.nsi.bso.TrashInfoBso;
import ru.armd.pbk.dto.nsi.bso.BsoDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.nsi.bso.BsoService;
import ru.armd.pbk.utils.json.JsonResult;

import java.util.List;

/**
 * rest контроллер типов БСО.
 */
@Controller
@RequestMapping(BsoControllerApi.RM_CONTROLLER_PATH)
public class BsoControllerApi
	extends BaseDomainControllerApi<Bso, BsoDTO> {

   public static final String RM_PATH = "/nsi/bsos";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_BSO = "hasAnyRole('DEBUG_TO_REPLACE','PBK_TASKS','BSO')";
   static final String PERM_EDIT_BSO = "hasAnyRole('DEBUG_TO_REPLACE','BSO_EDIT')";

   private BsoService bsoService;

   @Autowired
   BsoControllerApi(BsoService service) {
	  this.service = service;
	  this.bsoService = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_BSO);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_BSO);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_BSO);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_BSO);
   }

   /**
	* Получить список SelectItem состояний БСО (print, trash, use, bind).
	*
	* @return OK response
	* @throws PBKException
	*/
   @RequestMapping(value = "/states", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
   public ResponseEntity<?> getBsoStates()
	   throws PBKException {
	  List<SelectItem> selItems = bsoService.getStateSelectList();
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Сгенерировать БСО типа bsoTypeId по правилам для подразделения
	* departmentId, в количестве count.
	*
	* @param departmentId id подразделения
	* @param bsoTypeId    id типа БСО
	* @param count        количество генерируемых БСО
	* @return OK response
	* @throws PBKException
	*/
   @RequestMapping(value = "/bulk", method = RequestMethod.POST)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).ADD_DTO)")
   public ResponseEntity<?> insertBulk(@RequestParam("departmentId") Long departmentId,
									   @RequestParam("bsoTypeId") Long bsoTypeId, @RequestParam("count") Integer count)
	   throws PBKException {
	  bsoService.saveBulk(departmentId, bsoTypeId, count);
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Привязать БСО, указанные в bsoIds, к сотруднику, указанному в employeeId.
	*
	* @param ids        id БСО
	* @param employeeId id сотрудника
	* @return OK response
	* @throws PBKException
	*/
   @RequestMapping(value = "/bind", method = RequestMethod.PUT)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).EDIT_DTO)")
   public ResponseEntity<?> bindBsos(@RequestParam("ids") List<Long> ids, @RequestParam("employeeId") Long employeeId)
	   throws PBKException {
	  bsoService.bindBsos(ids, employeeId);
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

	@RequestMapping(value = "/date", method = RequestMethod.GET)
	@ResponseBody
	@PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
	public ResponseEntity<?> getBindDateById(@RequestParam("bsoId") Long bsoId)
			throws PBKException {
		String bsoDate = bsoService.getBindDateById(bsoId);
		JsonResult jsonResult = new JsonResult();
		jsonResult.setResult(bsoDate);
		return new ResponseEntity<>(jsonResult, HttpStatus.OK);
	}

   /**
	* Отвязать БСО, указанные в ids, от сотрудников.
	*
	* @param ids id БСО
	* @return OK response
	* @throws PBKException
	*/
   @RequestMapping(value = "/unbind", method = RequestMethod.PUT)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).EDIT_DTO)")
   public ResponseEntity<?> unbindBsos(@RequestParam("ids") List<Long> ids)
	   throws PBKException {
	  bsoService.unbindBsos(ids);
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Отправить в мусор БСО, указанный в id.
	*
	* @return OK response
	* @throws PBKException
	*/
   @RequestMapping(value = "/trash", method = RequestMethod.POST)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).EDIT_DTO)")
   public ResponseEntity<?> trashBso(@RequestBody TrashBso trashBso)
	   throws PBKException {
	  bsoService.trashBso(trashBso);
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

	@RequestMapping(value = "/trash-info", method = RequestMethod.GET)
	@ResponseBody
	@PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
	public ResponseEntity<?> getTrashBsoByBsoId(@RequestParam Long bsoId)
			throws PBKException {
		TrashInfoBso bso = bsoService.getTrashBsoByBsoId(bsoId);
		JsonResult jsonResult = new JsonResult();
		jsonResult.setResult(bso);
		return new ResponseEntity<>(jsonResult, HttpStatus.OK);
	}
//67-01-041962
   /**
	* Исправить БСО, указанные в ids.
	*
	* @param ids id БСО
	* @return OK response
	* @throws PBKException
	*/
/*   @RequestMapping(value = "/fix", method = RequestMethod.PUT)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).EDIT_DTO)")
   public ResponseEntity<?> fixBsos(@RequestParam("ids") List<Long> ids)
	   throws PBKException {
	  bsoService.fixBsos(ids);
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }*/

   /**
	* Напечатать БСО, указанные в ids.
	*
	* @param ids id БСО
	* @return OK response
	* @throws PBKException
	*/
   @RequestMapping(value = "/print", method = RequestMethod.PUT)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).EDIT_DTO)")
   public ResponseEntity<?> printBsos(@RequestParam("ids") List<Long> ids)
	   throws PBKException {
	  bsoService.printBsos(ids);
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Использовать БСО, указанные в списке bsoIds, для задания, указанного в
	* taskId.
	*
	* @param bsoIds список id БСО
	* @param taskId id задания
	* @return OK response
	* @throws PBKException
	*/
   @RequestMapping(value = "/use", method = RequestMethod.PUT)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).EDIT_DTO)")
   public ResponseEntity<?> useBsos(@RequestParam("bsoIds") List<Long> bsoIds, @RequestParam("taskId") Long taskId)
	   throws PBKException {
	  bsoService.useBsos(bsoIds, taskId);
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Отвязать использование БСО, указанных в списке bsoIds, от задания,
	* указанного в taskId.
	*
	* @param bsoIds список id БСО
	* @param taskId id задания
	* @return OK response
	* @throws PBKException
	*/
   @RequestMapping(value = "/disuse", method = RequestMethod.PUT)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).EDIT_DTO)")
   public ResponseEntity<?> disuseBsos(@RequestParam("bsoIds") List<Long> bsoIds, @RequestParam("taskId") Long taskId)
	   throws PBKException {
	  bsoService.disuseBsos(bsoIds, taskId);
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получить все доступные указанному сотруднику БСО (нужно при
	* создании/изменении задания).
	*
	* @param params фильтры
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/task-slist", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
   public ResponseEntity<?> getBsosForTask(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<SelectItem> bsos = bsoService.getSelectItemsForTask(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(bsos);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

	@RequestMapping(value = "/task-card-slist", method = RequestMethod.GET)
	@ResponseBody
	@PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
	public ResponseEntity<?> getSelectItemsForTaskCard(@RequestParam MultiValueMap<String, String> params)
			throws PBKException {
		List<SelectItem> bsos = bsoService.getSelectItemsForTaskCard(new BaseSelectListParams(getFilterMap(params)));
		JsonResult jsonResult = new JsonResult();
		jsonResult.setResult(bsos);
		return new ResponseEntity<>(jsonResult, HttpStatus.OK);
	}

   /**
	* Получить все неиспользованные БСО указанного расписания сотрудника.
	*
	* @param taskId             id задания
	* @param employeeScheduleId id сотрудника
	* @param currentId          id задания
	* @return OK response
	*/
   @RequestMapping(value = "/schedule-slist", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
   public ResponseEntity<?> getBsosForSchedule(@RequestParam("taskId") Long taskId,
											   @RequestParam("employeeScheduleId") Long employeeScheduleId,
											   @RequestParam(value = "currentId", required = false) Long currentId)
	   throws PBKException {
	  List<SelectItem> bsos = bsoService.getSelectItemsForSchedule(taskId, employeeScheduleId, currentId);
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(bsos);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}
