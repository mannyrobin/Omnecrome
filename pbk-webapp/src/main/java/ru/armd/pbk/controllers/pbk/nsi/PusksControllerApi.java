package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseOwnersHistoryControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.Pusk;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.dto.nsi.PuskDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.services.nsi.PuskService;
import ru.armd.pbk.utils.json.JsonResult;

import java.util.List;

/**
 * Контроллер ПУсКов.
 */
@Controller
@RequestMapping(PusksControllerApi.RM_CONTROLLER_PATH)
public class PusksControllerApi
	extends BaseOwnersHistoryControllerApi<Pusk, PuskDTO> {

   public static final String RM_PATH = "/nsi/pusks";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_PUSKS = "hasAnyRole('DEBUG_TO_REPLACE','PUSKS')";
   static final String PERM_EDIT_PUSKS = "hasAnyRole('DEBUG_TO_REPLACE','PUSKS_EDIT')";


   @Autowired
   PusksControllerApi(PuskService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_PUSKS);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_PUSKS);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_PUSKS);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_PUSKS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_PUSKS);
	  this.auth.put(ControllerMethods.GET_HISTRORY, PERM_READ_PUSKS);
	  this.auth.put(ControllerMethods.GET_OWNERS_HISTORY, PERM_READ_PUSKS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.EDIT_VERSION_DTO, PERM_EDIT_PUSKS);
   }

   /**
	* Получить список ПУсКов для выпадаюшего списка сотрудников.
	*
	* @param params фильтры.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/employee-slist", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getSelectList(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<ISelectItem> selItems = ((PuskService) service).getSelectItemsForEmployee(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   @Override
   protected void customDeleteValidation(List<Long> ids)
	   throws PBKValidationException {
	  if (ids != null) {
		 for (Long id : ids) {
			Employee employee = ((PuskService) service).getEmployeeByDeviceId(id);
			if (employee != null) {
			   throw new PBKValidationException("dvr id", " Устройство привязано к " + employee.getSurname()
				   + " " + employee.getName() + " " + employee.getPatronumic());
			}
		 }
	  }
   }
}
