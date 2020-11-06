package ru.armd.pbk.controllers.pbk.nsi.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.venue.Venue;
import ru.armd.pbk.dto.nsi.district.DistrictVenueDTO;
import ru.armd.pbk.dto.nsi.venue.VenueDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.nsi.venue.VenueService;
import ru.armd.pbk.utils.json.JsonResult;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для работы с местами встреч.
 */
@Controller
@RequestMapping(VenuesControllerApi.RM_CONTROLLER_PATH)
public class VenuesControllerApi
	extends BaseVersionControllerApi<Venue, VenueDTO> {

   public static final String RM_PATH = "/nsi/venues";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   public static final String DELETE_DISTRICTS = "/remove-districts";
   public static final String ADD_DISTRICT = "/add-district";
   public static final String RESTORE_VENUE = "/restore-venue";

   static final String PERM_READ_VENUES = "hasAnyRole('DEBUG_TO_REPLACE','VENUES')";
   static final String PERM_EDIT_DISTRICTS_AND_VENUES = "hasAnyRole('DEBUG_TO_REPLACE', 'VENUES_EDIT', 'DISTRICTS_EDIT')";
   static final String PERM_READ_DISTRICTS_AND_VENUES = "hasAnyRole('DEBUG_TO_REPLACE', 'VENUES', 'DISTRICTS')";
   static final String PERM_EDIT_VENUES = "hasAnyRole('DEBUG_TO_REPLACE','VENUES_EDIT')";

   @Autowired
   VenuesControllerApi(VenueService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_DISTRICTS_AND_VENUES);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_VENUES);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_VENUES);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_VENUES);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_VENUES);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_HISTRORY, PERM_READ_DISTRICTS_AND_VENUES);
	  this.auth.put(ControllerMethods.EDIT_VERSION_DTO, PERM_EDIT_VENUES);
   }

   /**
	* Удалить район у места встречи.
	*
	* @param ids - ИД мест встреч.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = DELETE_DISTRICTS, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize(PERM_EDIT_DISTRICTS_AND_VENUES)
   public ResponseEntity<?> deleteDistricts(@RequestParam("ids") List<Long> ids)
	   throws PBKException {
	  ((VenueService) service).removeDistricts(ids);
	  return new ResponseEntity<>(HttpStatus.OK);
   }

   /**
	* Добавить район к месту встречи.
	*
	* @param dto ДТО местовстречи - район.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = ADD_DISTRICT, method = RequestMethod.POST)
   @ResponseBody
   @PreAuthorize(PERM_EDIT_DISTRICTS_AND_VENUES)
   public ResponseEntity<?> addDistrict(@Valid @RequestBody DistrictVenueDTO dto)
	   throws PBKException {
	  ((VenueService) service).linkDistrict(dto);
	  return new ResponseEntity<>(HttpStatus.OK);
   }

   /**
	* Востановить место встречи после удаления.
	*
	* @param dto ДТО местовстречи.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = RESTORE_VENUE, method = RequestMethod.PUT)
   @ResponseBody
   @PreAuthorize(PERM_EDIT_VENUES)
   public ResponseEntity<?> restoreVenue(@RequestBody VenueDTO dto)
	   throws PBKException {
	  ((VenueService) service).restoreVenue(dto.getHeadId(), dto.getVersionStartDate());
	  return new ResponseEntity<>(HttpStatus.OK);
   }

   /**
	* Получение списка мест встреч для отображения в комбобоксах планов.
	*
	* @param params параметры фильтрации.
	* @return список мест встреч объектов.
	*/
   @RequestMapping(value = RM_BASE_SLIST + "-plan", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize(PERM_ALLOW_ALL)
   public ResponseEntity<?> getSelectItemsForPlan(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<ISelectItem> selItems = ((VenueService) service).getSelectItemsForPlan(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}
