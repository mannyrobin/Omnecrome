package ru.armd.pbk.controllers.pbk.report.standard;

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
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.So4Service;
import ru.armd.pbk.services.SoService;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.utils.json.JsonResult;
import ru.armd.pbk.views.report.So4View;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер стандартного отчёта "Ежедневная посменная численность контролеров ГУП "Мосгортранс" по территориальному подразделению".
 */
@Controller
@RequestMapping(So4ControllerApi.RM_CONTROLLER_PATH)
public class So4ControllerApi
	extends SoControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-4";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SO4 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD4')";

   private List<So4View> views = null;

   @Autowired
   So4ControllerApi(So4Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO4);
   }

   @RequestMapping(value = RM_BASE_TOTAL, method = RequestMethod.GET)
   @ResponseBody
   @Override
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_VIEWS)")
   public ResponseEntity<?> getSelectList(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<BaseGridView> result = new ArrayList<>();
	  result.add(((SoService<?, ?>) service).getGridViewTotal(getFilterMap(params)));
	  result.add(((So4Service) service).getAverageTotal((So4View) result.get(0), views.size()));
	  result.add(((So4Service) service).getAverageWeekDayTotal(views));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(result);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
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
   @SuppressWarnings("unchecked")
   @RequestMapping(value = RM_BASE, method = RequestMethod.GET)
   @ResponseBody
   @Override
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_VIEWS)")
   public ResponseEntity<?> getViews(
	   @RequestParam(value = "rows", required = false, defaultValue = ROWS_DEFAULT_VALUE) Long count,
	   @RequestParam(value = "page", required = false, defaultValue = PAGE_DEFAULT_VALUE) Long page,
	   @RequestParam(value = "sidx", required = false, defaultValue = SIDX_DEFAULT_VALUE) String orderBy,
	   @RequestParam(value = "sord", required = false, defaultValue = SORD_DEFAULT_VALUE) String orderByAsc,
	   @RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  JsonGridData gridData = service
		  .getGridViews(new BaseGridViewParams(page, count, orderBy, orderByAsc, getFilterMap(params)));
	  if (gridData != null && gridData.getRows() != null) {
		 views = (List<So4View>) gridData.getRows();
	  }
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(gridData);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}
