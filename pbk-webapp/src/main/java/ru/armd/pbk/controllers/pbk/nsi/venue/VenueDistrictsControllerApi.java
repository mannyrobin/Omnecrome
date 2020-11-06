package ru.armd.pbk.controllers.pbk.nsi.venue;

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
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.venue.Venue;
import ru.armd.pbk.dto.nsi.venue.VenueDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.nsi.venue.VenueService;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.utils.json.JsonResult;

import java.util.List;

/**
 * Контроллер для работы с "Районы".
 */
@Controller
@RequestMapping(VenueDistrictsControllerApi.RM_CONTROLLER_PATH)
public class VenueDistrictsControllerApi
	extends BaseVersionControllerApi<Venue, VenueDTO> {

   public static final String RM_PATH = "/nsi/venues/districts";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   VenueDistrictsControllerApi(VenueService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, VenuesControllerApi.PERM_READ_VENUES);
	  this.auth.put(ControllerMethods.ADD_DTO, VenuesControllerApi.PERM_EDIT_VENUES);
	  this.auth.put(ControllerMethods.DELETE_SOFT, VenuesControllerApi.PERM_EDIT_VENUES);
	  this.auth.put(ControllerMethods.GET_SLIST, VenuesControllerApi.PERM_READ_VENUES);
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
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_VIEWS)")
   public ResponseEntity<?> getViews(
	   @RequestParam(value = "rows", required = false, defaultValue = ROWS_DEFAULT_VALUE) Long count,
	   @RequestParam(value = "page", required = false, defaultValue = PAGE_DEFAULT_VALUE) Long page,
	   @RequestParam(value = "sidx", required = false, defaultValue = SIDX_DEFAULT_VALUE) String orderBy,
	   @RequestParam(value = "sord", required = false, defaultValue = SORD_DEFAULT_VALUE) String orderByAsc,
	   @RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  JsonGridData gridData = ((VenueService) service).getVenueDistrictsHistoryViews(
		  new BaseGridViewParams(page, count, orderBy, orderByAsc, getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(gridData);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получить список объектов для выпадаюшего списка фильтра райнов места
	* встречи.
	*
	* @param params фильтры.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/venue-district-slist", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
   public ResponseEntity<?> getSelectDistricts(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<ISelectItem> selItems = ((VenueService) service)
		  .getSelectDistricts(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получить список объектов для выпадаюшего списка фильтра маршрутов места
	* встречи.
	*
	* @param params фильтры.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/venue-district-route-slist", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
   public ResponseEntity<?> getSelectVenueDistrictRoutes(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<SelectItem> selItems = ((VenueService) service)
		  .getSelectVenueDistrictRoutes(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получить список объектов для выпадаюшего списка фильтра остановок места
	* встречи.
	*
	* @param params фильтры.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/venue-district-stop-slist", method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
   public ResponseEntity<?> getSelectStops(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<SelectItem> selItems = ((VenueService) service)
		  .getSelectStops(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}