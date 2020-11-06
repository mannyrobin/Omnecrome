package ru.armd.pbk.core.controllers;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.core.services.IVersionDomainService;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.utils.json.JsonResult;

import javax.validation.Valid;

/**
 * Базовый контроллер версионных объектов.
 *
 * @param <DomainVersion> версионный домен.
 * @param <DTOVersion>    версионное ДТО.
 */
public class BaseVersionControllerApi<DomainVersion extends BaseVersionDomain, DTOVersion extends BaseVersionDTO>
	extends BaseDomainControllerApi<DomainVersion, DTOVersion> {

   public static final Logger LOGGER = Logger.getLogger(BaseVersionControllerApi.class);

   public static final String RM_BASE_HISTORY = "/history";
   public static final String RM_BASE_VERSION = "/version";
   public static final String RM_RESTORE_VERSION = "/restore-version/{id}";

   /**
	* Возвращает историю для грида.
	*
	* @param count      количество строк в гриде.
	* @param page       номер страницы грида.
	* @param orderBy    id поля, по которому будет производиться сортировка.
	* @param orderByAsc вид сортировки(asc/desc).
	* @param params     фильтры.
	* @return историю в гриде.
	* @throws PBKException
	*/

   @RequestMapping(value = RM_BASE_HISTORY, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_HISTRORY)")
   public ResponseEntity<?> getHistoryViews(
	   @RequestParam(value = "rows", required = false, defaultValue = ROWS_DEFAULT_VALUE) Long count,
	   @RequestParam(value = "page", required = false, defaultValue = PAGE_DEFAULT_VALUE) Long page,
	   @RequestParam(value = "sidx", required = false, defaultValue = SIDX_DEFAULT_VALUE) String orderBy,
	   @RequestParam(value = "sord", required = false, defaultValue = SORD_DEFAULT_VALUE) String orderByAsc,
	   @RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  JsonGridData gridData = ((IVersionDomainService<DomainVersion, DTOVersion>) service)
		  .getHistoryViews(new BaseGridViewParams(page, count, orderBy, orderByAsc, getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(gridData);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Обновить объект.
	*
	* @param dto dto.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = RM_BASE_VERSION, method = RequestMethod.PUT)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).EDIT_VERSION_DTO)")
   public ResponseEntity<?> updateVersionDTO(@Valid @RequestBody DTOVersion dto)
	   throws PBKException {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(((IVersionDomainService<DomainVersion, DTOVersion>) service).saveVersionDTO(dto));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Восстановить версию объекта.
	*
	* @param id id версии.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = RM_RESTORE_VERSION, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).EDIT_VERSION_DTO)")
   public ResponseEntity<?> restoreVersionDTO(@PathVariable("id") Long id)
	   throws PBKException {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(((IVersionDomainService<DomainVersion, DTOVersion>) service).restoreVersionDTO(id));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}
