package ru.armd.pbk.core.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.core.services.IDeviceOwnersHistoryService;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.utils.json.JsonResult;

/**
 * Базовый контроллер устройств с историей.
 */
public class BaseOwnersHistoryControllerApi<DomainVersion extends BaseVersionDomain, DTOVersion extends BaseVersionDTO>
	extends BaseVersionControllerApi<DomainVersion, DTOVersion> {

   public static final String RM_BASE_OWNERS_HISTORY = "/ownershistory";

   @RequestMapping(value = RM_BASE_OWNERS_HISTORY, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_OWNERS_HISTORY)")
   public ResponseEntity<?> getOwnerHistory(
	   @RequestParam(value = "rows", required = false, defaultValue = ROWS_DEFAULT_VALUE) Long count,
	   @RequestParam(value = "page", required = false, defaultValue = PAGE_DEFAULT_VALUE) Long page,
	   @RequestParam(value = "sidx", required = false, defaultValue = SIDX_DEFAULT_VALUE) String orderBy,
	   @RequestParam(value = "sord", required = false, defaultValue = SORD_DEFAULT_VALUE) String orderByAsc,
	   @RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  JsonGridData gridData = ((IDeviceOwnersHistoryService) service)
		  .getOwnersHistoryViews(new BaseGridViewParams(page, count, orderBy, orderByAsc, getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(gridData);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}
