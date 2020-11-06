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
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.domain.nsi.Route;
import ru.armd.pbk.dto.nsi.RouteDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.nsi.RouteService;
import ru.armd.pbk.utils.json.JsonResult;
import ru.armd.pbk.views.nsi.route.RouteSelectItem;
import ru.armd.pbk.views.nsi.route.RouteStop;
import ru.armd.pbk.views.nsi.route.RouteTrajectory;

import java.util.List;

/**
 * Контроллер маршрутов.
 */
@Controller
@RequestMapping(RoutesControllerApi.RM_CONTROLLER_PATH)
public class RoutesControllerApi
	extends BaseVersionControllerApi<Route, RouteDTO> {

   public static final String RM_PATH = "/nsi/routes";
   public static final String TRAJECTORIES_PATH = "/trajectories";
   public static final String TRAJECTORY_PATH = RM_BASE_ID + "/trajectory";
   public static final String STOPS_PATH = RM_BASE_ID + "/stops";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_ROUTES = "hasAnyRole('DEBUG_TO_REPLACE','ROUTES')";
   static final String PERM_EDIT_ROUTES = "hasAnyRole('DEBUG_TO_REPLACE','ROUTES_EDIT')";

   /**
	* Конструктор.
	*
	* @param service сервис.
	*/
   @Autowired
   RoutesControllerApi(RouteService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_ROUTES);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_ROUTES);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_ROUTES);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_ROUTES);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_ROUTES);
	  this.auth.put(ControllerMethods.GET_HISTRORY, PERM_READ_ROUTES);
	  this.auth.put(ControllerMethods.EDIT_VERSION_DTO, PERM_EDIT_ROUTES);
   }

   /**
	* Поулчение траектории маршрута.
	*
	* @param id ид маршрута.
	* @return
	*/
   @RequestMapping(value = TRAJECTORY_PATH, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize(PERM_READ_ROUTES)
   public ResponseEntity<?> getRouteTrajectory(@PathVariable("id") Long id) {
	  JsonResult jsonResult = new JsonResult();
	  List<RouteTrajectory> trajectory = ((RouteService) service).getRouteTrajectory(id);
	  jsonResult.setResult(trajectory);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получение траектории маршрута по номеру.
	*
	* @param routeNum номер.
	* @return
	*/
   @RequestMapping(value = TRAJECTORIES_PATH, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize(PERM_READ_ROUTES)
   public ResponseEntity<?> getRouteTrajectoryByRouteNum(@RequestParam("routeNum") String routeNum) {
	  JsonResult jsonResult = new JsonResult();
	  List<RouteTrajectory> trajectory = ((RouteService) service).getRouteTrajectoryByRouteNum(routeNum);
	  jsonResult.setResult(trajectory);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получение остановок маршрута.
	*
	* @param id          ИД
	* @param trajectType тип траектории.
	* @return
	*/
   @RequestMapping(value = STOPS_PATH, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize(PERM_READ_ROUTES)
   public ResponseEntity<?> getRouteStops(@PathVariable("id") Long id,
										  @RequestParam(value = "trType", required = false) Long trajectType) {
	  JsonResult jsonResult = new JsonResult();
	  List<RouteStop> routeStops = ((RouteService) service).getRouteStops(id, trajectType);
	  jsonResult.setResult(routeStops);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получение списка маршрутов для отображения в комбобоксах при создании
	* задания.
	*
	* @param params Параметры фильтрации.ф
	* @throws PBKException
	*/
   @RequestMapping(value = "slist-task", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getSelectItemsForTask(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<RouteSelectItem> selItems = ((RouteService) service)
		  .getSelectItemsForTask(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получение списка маршрутов с траекторией и остановками для отображения на
	* карте при добавления сопутствующих маршрутов.
	*
	* @param params параметры
	* @throws PBKException
	*/
   @RequestMapping(value = "slist-district", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getRoutesByDistrict(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(
		  ((RouteService) service).getRoutesByDistrict(new BaseSelectListParams(getFilterMap(params))));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получение списка маршрутов с траекторией и остановками для отображения на
	* карте при добавления сопутствующих маршрутов.
	*
	* @param venueId ИД места встречи
	* @throws PBKException
	*/
   @RequestMapping(value = "slist-venue", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getRoutesByVenue(@RequestParam("venueId") Long venueId)
	   throws PBKException {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(((RouteService) service).getRoutesByVenue(venueId));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получает список маршрутов района, которые еще не добавлены для текущего
	* места встречи.
	*
	* @param params параметры
	* @return список маршрутов района
	* @throws PBKException
	*/
   @RequestMapping(value = "slist-route-distr-add", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getSelectItemsForDistrictAdd(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(
		  ((RouteService) service).getSelectItemsForDistrictAdd(new BaseSelectListParams(getFilterMap(params))));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получение маршрутов для вкладки районов мест встреч.
	*
	* @param params параметры
	* @return список маршрутов
	* @throws PBKException
	*/
   @RequestMapping(value = "slist-route-distr-lazy", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getLazySelectItemsForVenues(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(
		  ((RouteService) service).getLazySelectItemsForVenues(new BaseSelectListParams(getFilterMap(params))));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получения списка ночных маршрутов для задания.
	*
	* @param params фильтры
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "slist-night-routes-for-task", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getNightRoutes(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(
		  ((RouteService) service).getNightRoutesForTask(new BaseSelectListParams(getFilterMap(params))));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получение маршрутов округа для вкладки добавления сопутствующих маршрутов
	* мест встреч.
	*
	* @param params параметры
	* @return список маршрутов
	* @throws PBKException
	*/
   @RequestMapping(value = "slist-route-county", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getRoutesOfCounty(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult
		  .setResult(((RouteService) service).getRoutesOfCounty(new BaseSelectListParams(getFilterMap(params))));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получение маршрутов района места встречи
	* для вкладки добавления сопутствующих маршрутов
	* мест встреч.
	*
	* @param params параметры
	* @return список маршрутов
	* @throws PBKException
	*/
   @RequestMapping(value = "slist-route-venue-district", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getSelectItemsForVenueDistrict(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult
		  .setResult(((RouteService) service).getSelectItemsForVenueDistrict(new BaseSelectListParams(getFilterMap(params))));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получения списка маршрутов из АСДУ ГТФС для фильтра.
	*
	* @param params параметры
	* @return список маршрутов
	* @throws PBKException
	*/
   @RequestMapping(value = "slist-gtfs-routes", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getGtfsRouteLists(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult
		  .setResult(((RouteService) service).getGtfsRouteLists(new BaseSelectListParams(getFilterMap(params))));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

}
