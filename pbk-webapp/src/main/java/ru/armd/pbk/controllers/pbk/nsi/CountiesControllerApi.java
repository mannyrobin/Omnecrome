package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.County;
import ru.armd.pbk.dto.nsi.CountyDTO;
import ru.armd.pbk.services.nsi.CountyService;
import ru.armd.pbk.utils.json.JsonResult;

/**
 * Контроллер округов.
 */
@Controller
@RequestMapping(CountiesControllerApi.RM_CONTROLLER_PATH)
public class CountiesControllerApi
	extends BaseVersionControllerApi<County, CountyDTO> {

   public static final String RM_PATH = "/nsi/counties";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_COUNTIES = "hasAnyRole('DEBUG_TO_REPLACE','COUNTIES')";
   static final String PERM_EDIT_COUNTIES = "hasAnyRole('DEBUG_TO_REPLACE','COUNTIES_EDIT')";


   @Autowired
   CountiesControllerApi(CountyService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_COUNTIES);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_COUNTIES);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_COUNTIES);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_COUNTIES);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_COUNTIES);
	  this.auth.put(ControllerMethods.GET_HISTRORY, PERM_READ_COUNTIES);
	  this.auth.put(ControllerMethods.EDIT_VERSION_DTO, PERM_READ_COUNTIES);
   }

   /**
	* Получить территорю округа.
	*
	* @param id - ИД округа.
	* @return
	*/
   @RequestMapping(value = "/egko", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> egko(@RequestParam("id") Long id) {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(((CountyService) service).getEgko(id));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}
