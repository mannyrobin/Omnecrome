package ru.armd.pbk.controllers.pbk.nsi.district;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.district.District;
import ru.armd.pbk.dto.nsi.district.DistrictDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.nsi.district.DistrictService;
import ru.armd.pbk.utils.json.JsonResult;

import java.util.List;

/**
 * rest контроллер для работы с районами.
 */
@Controller
@RequestMapping(DistrictsControllerApi.RM_CONTROLLER_PATH)
public class DistrictsControllerApi
	extends BaseVersionControllerApi<District, DistrictDTO> {

   public static final String RM_PATH = "/nsi/districts";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_DISTRICTS = "hasAnyRole('DEBUG_TO_REPLACE','DISTRICTS')";
   static final String PERM_EDIT_DISTRICTS = "hasAnyRole('DEBUG_TO_REPLACE','DISTRICTS_EDIT')";

   @Autowired
   DistrictsControllerApi(DistrictService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_DISTRICTS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_DISTRICTS);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_DISTRICTS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_DISTRICTS);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_DISTRICTS);
	  this.auth.put(ControllerMethods.GET_HISTRORY, PERM_READ_DISTRICTS);
	  this.auth.put(ControllerMethods.EDIT_VERSION_DTO, PERM_EDIT_DISTRICTS);
   }

   /**
	* Получение выпадающего списка.
	*
	* @param venueId ИД места встречи.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = RM_BASE_SLIST + "-venue", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
   public ResponseEntity<?> getSelectList(@RequestParam("venueId") Long venueId)
	   throws PBKException {
	  List<ISelectItem> selItems = ((DistrictService) service).getDistrictsByVenueId(venueId);
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получить территорю района.
	*
	* @param id - ИД округа.
	* @return
	*/
   @RequestMapping(value = "/egko", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> egko(@RequestParam("id") Long id) {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(((DistrictService) service).getEgko(id));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}
