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
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.StopIntervalStat;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.nsi.StopIntervalsStatsService;
import ru.armd.pbk.utils.json.JsonResult;

import java.util.List;

/**
 * Контроллер для получение статистических данных Интервалов.
 */
@Controller
@RequestMapping(StopIntervalsStatsControllerApi.RM_CONTROLLER_PATH)
public class StopIntervalsStatsControllerApi
	extends BaseDomainControllerApi<StopIntervalStat, BaseDTO> {

   public static final String RM_PATH = "/nsi/stop-intervals-stats";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   private StopIntervalsStatsService statsService;

   @Autowired
   StopIntervalsStatsControllerApi(StopIntervalsStatsService service) {
	  this.service = service;
	  this.statsService = service;
   }

   /**
	* Получить список маршрутов.
	*
	* @param params параметры.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "route-slist", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getRouteSelectList(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<SelectItem> selItems = statsService.getRouteSelectList(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получить список парков.
	*
	* @param params параметры.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "park-slist", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getParkSelectList(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<SelectItem> selItems = statsService.getParkSelectList(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получить список выходов.
	*
	* @param params параметры.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "route-move-slist", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getRouteMoveSelectList(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<SelectItem> selItems = statsService.getRouteMoveSelectList(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получить список рейсов.
	*
	* @param params параметры.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "route-trip-slist", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getRouteTripSelectList(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<SelectItem> selItems = statsService.getRouteTripSelectList(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получить список остановок.
	*
	* @param params параметры.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "stops-slist", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getStopSelectList(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<SelectItem> selItems = statsService.getStopSelectList(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

}
