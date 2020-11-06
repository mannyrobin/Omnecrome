package ru.armd.pbk.controllers.pbk.nsi;

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
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.Shift;
import ru.armd.pbk.dto.nsi.ShiftDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.nsi.ShiftService;
import ru.armd.pbk.utils.json.JsonResult;

import java.util.List;

/**
 * rest контроллер для работы со сменами.
 */
@Controller
@RequestMapping(ShiftsControllerApi.RM_CONTROLLER_PATH)
public class ShiftsControllerApi
	extends BaseDomainControllerApi<Shift, ShiftDTO> {

   public static final String RM_PATH = "/nsi/shifts";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SHIFTS = "hasAnyRole('DEBUG_TO_REPLACE','SHIFTS')";
   static final String PERM_GET_SHIFTS = "hasAnyRole('DEBUG_TO_REPLACE', 'SHIFTS', 'PBK_TASKS')";
   static final String PERM_EDIT_SHIFTS = "hasAnyRole('DEBUG_TO_REPLACE','SHIFTS_EDIT')";

   @Autowired
   ShiftsControllerApi(ShiftService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SHIFTS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_GET_SHIFTS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_SHIFTS);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_SHIFTS);
   }

   /**
	* Получение списка смен для отображения в плановых комбобоксах.
	*
	* @param params параметры фильтрации.
	* @return список смен объектов.
	*/
   @RequestMapping(value = RM_BASE_SLIST + "-plan", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getSelectItemsForPlan(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<ISelectItem> selItems = ((ShiftService) service)
		  .getSelectItemsForPlan(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Выбрать смену по id расписания.
	*
	* @param id ИД расписания.
	* @return смену.
	* @throws PBKException
	*/
   @RequestMapping(value = RM_BASE_ID + "/schedule", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_DTO)")
   public ResponseEntity<?> getDTOByScheduleId(@PathVariable("id") Long id)
	   throws PBKException {
	  ShiftDTO dto = ((ShiftService) service).getDTOByScheduleId(id);
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(dto);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получить смену задания. Время окончания рабочего дня смены
	* зависит от планового времени работы сотрудника. Например,
	* если смена начинается в 11:00, а заканчивается в 20:00
	* и плановое время сотрудника 7 часов, то время окончания
	* рабочего дня будет 19:00.
	*
	* @param id ИД задания
	* @return смена
	*/
   @RequestMapping(value = RM_BASE_ID + "/task", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_DTO)")
   public ResponseEntity<?> getByDTOByTaskId(@PathVariable("id") Long id) {
	  ShiftDTO dto = ((ShiftService) service).getDTOByTaskId(id);
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(dto);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}
