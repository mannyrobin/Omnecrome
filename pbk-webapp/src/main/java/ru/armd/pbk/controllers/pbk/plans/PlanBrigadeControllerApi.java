package ru.armd.pbk.controllers.pbk.plans;

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
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.plans.brigades.Brigade;
import ru.armd.pbk.dto.plans.brigades.BrigadeApproveDTO;
import ru.armd.pbk.dto.plans.brigades.BrigadeDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.services.plans.brigades.PlanBrigadeService;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.utils.json.JsonResult;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Контроллер бригад планов отдела.
 */
@Controller
@RequestMapping(PlanBrigadeControllerApi.RM_CONTROLLER_PATH)
public class PlanBrigadeControllerApi
	extends BaseDomainControllerApi<Brigade, BrigadeDTO> {

   public static final String RM_PATH = "/pbk/plan/brigades";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   public static final String PERM_EDIT_BRIGADE = "hasAnyRole('DEBUG_TO_REPLACE','PBK_PLAN_BRIGADE_EDIT')";

   @Autowired
   PlanBrigadeControllerApi(PlanBrigadeService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PlansControllerApi.PERM_READ_PLANS);
	  this.auth.put(ControllerMethods.GET_DTO, PlansControllerApi.PERM_READ_PLANS);
	  this.auth.put(ControllerMethods.ADD_DTO, PlansControllerApi.PERM_EDIT_PLANS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PlansControllerApi.PERM_EDIT_PLANS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
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

   /**
	* Согласование бригад.
	*
	* @param dto дто.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/approve", method = RequestMethod.POST)
   @ResponseBody
   @PreAuthorize(PERM_EDIT_BRIGADE)
   public ResponseEntity<?> approveBrigade(@RequestBody @Valid BrigadeApproveDTO dto)
	   throws PBKValidationException {
	  ((PlanBrigadeService) service).approveBrigade(dto, true);
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Снятие согласования бригад.
	*
	* @param dto дто
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/disapprove", method = RequestMethod.POST)
   @ResponseBody
   @PreAuthorize(PERM_EDIT_BRIGADE)
   public ResponseEntity<?> disapproveBrigade(@RequestBody @Valid BrigadeApproveDTO dto)
	   throws PBKException {
	  ((PlanBrigadeService) service).approveBrigade(dto, false);
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}