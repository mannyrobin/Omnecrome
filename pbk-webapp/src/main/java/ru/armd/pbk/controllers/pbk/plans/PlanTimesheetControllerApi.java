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
import ru.armd.pbk.domain.plans.timesheets.Timesheet;
import ru.armd.pbk.dto.plans.timesheets.TimesheetDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.plans.timesheet.PlanTimesheetService;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.utils.json.JsonResult;

import java.text.SimpleDateFormat;

/**
 * Контроллер табелей отдела.
 */
@Controller
@RequestMapping(PlanTimesheetControllerApi.RM_CONTROLLER_PATH)
public class PlanTimesheetControllerApi
	extends BaseDomainControllerApi<Timesheet, TimesheetDTO> {

   public static final String RM_PATH = "/pbk/plan/timesheets";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   PlanTimesheetControllerApi(PlanTimesheetService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PlansControllerApi.PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_DTO, PlansControllerApi.PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.EDIT_DTO, PlansControllerApi.PERM_ALLOW_ALL);
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
