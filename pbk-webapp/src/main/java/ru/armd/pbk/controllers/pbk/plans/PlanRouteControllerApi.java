package ru.armd.pbk.controllers.pbk.plans;

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
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.plans.routes.RouteRatio;
import ru.armd.pbk.dto.plans.routes.RouteRatioDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.plans.routes.PlanRouteService;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.utils.json.JsonResult;

import java.text.SimpleDateFormat;

/**
 * Контроллер маршрутов планов отдела.
 */
@Controller
@RequestMapping(PlanRouteControllerApi.RM_CONTROLLER_PATH)
public class PlanRouteControllerApi
	extends BaseDomainControllerApi<RouteRatio, RouteRatioDTO> {

   public static final String RM_PATH = "/pbk/plan/routes";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   static final String PERM_READ_ROUTES = "hasAnyRole('DEBUG_TO_REPLACE','ROUTES_RATIO')";
   static final String PERM_EDIT_ROUTES = "hasAnyRole('DEBUG_TO_REPLACE','ROUTES_RATIO_EDIT')";

   @Autowired
   PlanRouteControllerApi(PlanRouteService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_ROUTES);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_ROUTES);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_ROUTES);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_ROUTES);
   }

   @Override
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
	  SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	  String fromDate = params.getFirst("d_dateFrom");
	  String toDate = params.getFirst("d_dateTo");
	  JsonResult jsonResult = new JsonResult();
	  try {
		 if (df.parse(fromDate).getTime() > df.parse(toDate).getTime()) {
			jsonResult.setResult(new JsonGridData(null, 0, 0, 1));
			return new ResponseEntity<>(jsonResult, HttpStatus.OK);
		 }
	  } catch (Exception ex) {
		 jsonResult.setResult(new JsonGridData(null, 0, 0, 1));
		 return new ResponseEntity<>(jsonResult, HttpStatus.OK);
	  }
	  JsonGridData gridData = service
		  .getGridViews(new BaseGridViewParams(page, count, orderBy, orderByAsc, getFilterMap(params)));
	  jsonResult.setResult(gridData);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}
