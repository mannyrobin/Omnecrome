package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.So1Service;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.utils.json.JsonResult;

/**
 * Контроллер стандартного отчёта "График работы контролеров по территориальному подразделению".
 */
@Controller
@RequestMapping(So1ControllerApi.RM_CONTROLLER_PATH)
public class So1ControllerApi
	extends BaseDomainControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-1";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SO1 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD1')";

   @Autowired
   So1ControllerApi(So1Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO1);
   }

   @Override
   public ResponseEntity<?> getViews(@RequestParam(value = "rows", required = false, defaultValue = ROWS_DEFAULT_VALUE) Long count,
									 @RequestParam(value = "page", required = false, defaultValue = PAGE_DEFAULT_VALUE) Long page,
									 @RequestParam(value = "sidx", required = false, defaultValue = SIDX_DEFAULT_VALUE) String orderBy,
									 @RequestParam(value = "sord", required = false, defaultValue = SORD_DEFAULT_VALUE) String orderByAsc,
									 @RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  JsonGridData gridData = ((So1Service) service)
		  .getPreviewGridViews(new BaseGridViewParams(page, count, orderBy, orderByAsc, getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(gridData);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}
