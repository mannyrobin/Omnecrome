package ru.armd.pbk.controllers.viss.asmpp;

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
import ru.armd.pbk.domain.viss.asmpp.AsmppStop;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.viss.asmpp.AsmppStopsService;
import ru.armd.pbk.utils.json.JsonResult;

import java.util.List;

/**
 * Контроллер для работы с остановками АСМПП.
 */
@Controller
@RequestMapping(AsmppStopsControllerApi.RM_CONTROLLER_PATH)
public class AsmppStopsControllerApi
	extends BaseDomainControllerApi<AsmppStop, BaseDTO> {

   public static final String RM_PATH = "/vis/asmpp-stops";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   private AsmppStopsService statsService;

   @Autowired
   AsmppStopsControllerApi(AsmppStopsService service) {
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
	* Получить список выходов маршрутов.
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
	* Получить список рейсов маршрутов.
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
	* Получить список номеров рейсов маршрутов.
	*
	* @param params параметры.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "route-trip-num-slist", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getRouteTripNumSelectList(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<SelectItem> selItems = statsService.getRouteTripNumSelectList(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}
