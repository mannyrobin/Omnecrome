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
import ru.armd.pbk.domain.nsi.ContactlessCard;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.dto.nsi.ContactlessCardDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.services.nsi.ContactlessCardService;
import ru.armd.pbk.utils.json.JsonResult;

import java.util.List;

/**
 * Контроллер БСК.
 */
@Controller
@RequestMapping(ContactlessCardsControllerApi.RM_CONTROLLER_PATH)
public class ContactlessCardsControllerApi
	extends BaseOwnersHistoryControllerApi<ContactlessCard, ContactlessCardDTO> {

   public static final String RM_PATH = "/nsi/contactless-cards";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   static final String PERM_READ_CONTRACTLESS_CARD = "hasAnyRole('DEBUG_TO_REPLACE','CONTRACTLESS_CARD')";
   static final String PERM_EDIT_CONTRACTLESS_CARD = "hasAnyRole('DEBUG_TO_REPLACE','CONTRACTLESS_CARD_EDIT')";


   @Autowired
   ContactlessCardsControllerApi(ContactlessCardService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_CONTRACTLESS_CARD);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_CONTRACTLESS_CARD);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_CONTRACTLESS_CARD);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_CONTRACTLESS_CARD);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_CONTRACTLESS_CARD);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_HISTRORY, PERM_READ_CONTRACTLESS_CARD);
	  this.auth.put(ControllerMethods.GET_OWNERS_HISTORY, PERM_READ_CONTRACTLESS_CARD);
	  this.auth.put(ControllerMethods.EDIT_VERSION_DTO, PERM_EDIT_CONTRACTLESS_CARD);
   }

   /**
	* Получить список БСК для выпадаюшего списка сотрудников.
	*
	* @param params фильтры.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/employee-slist", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getSelectList(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<ISelectItem> selItems = ((ContactlessCardService) service).getSelectItemsForEmployee(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   @Override
   protected void customDeleteValidation(List<Long> ids)
	   throws PBKValidationException {
	  if (ids != null) {
		 for (Long id : ids) {
			Employee employee = ((ContactlessCardService) service).getEmployeeByDeviceId(id);
			if (employee != null) {
			   throw new PBKValidationException("contactlessCard id", " Устройство привязано к " + employee.getSurname()
				   + " " + employee.getName() + " " + employee.getPatronumic());
			}
		 }
	  }
   }
}
