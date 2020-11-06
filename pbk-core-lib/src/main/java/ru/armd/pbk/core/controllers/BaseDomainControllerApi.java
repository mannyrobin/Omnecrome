package ru.armd.pbk.core.controllers;

import org.apache.log4j.Logger;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.IDomainService;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.utils.json.JsonResult;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Базовый класс для контроллера доменной сущности.
 *
 * @param <Domain> Домен.
 * @param <DTO>    ДТО
 */
@Controller
public abstract class BaseDomainControllerApi<Domain extends BaseDomain, DTO extends BaseDTO>
	extends BaseControllerApi {

   public static final Logger LOGGER = Logger.getLogger(BaseDomainControllerApi.class);

   public static final String RM_BASE = "";
   public static final String RM_BASE_ID = "/{id}";
   public static final String RM_BASE_SLIST = "/slist";
   public static final String RM_BASE_DELETE_SOFT = "/deleteSoft";
   public static final String RM_BASE_DELETE = "/delete";

   public static final String PERM_TEMP_ROLE = "hasRole('DEBUG_TO_REPLACE')";
   public static final String PERM_DENY_ALL = "denyAll";
   public static final String PERM_ALLOW_ALL = "permitAll";

   protected IDomainService<Domain, DTO> service;

   protected Map<ControllerMethods, String> auth = new HashMap<ControllerMethods, String>() {
	  private static final long serialVersionUID = 8167783464200607698L;

	  {
		 put(ControllerMethods.GET_VIEWS, PERM_DENY_ALL);
		 put(ControllerMethods.GET_SLIST, PERM_DENY_ALL);
		 put(ControllerMethods.GET_DTO, PERM_DENY_ALL);
		 put(ControllerMethods.ADD_DTO, PERM_DENY_ALL);
		 put(ControllerMethods.EDIT_DTO, PERM_DENY_ALL);
		 put(ControllerMethods.DELETE, PERM_DENY_ALL);
		 put(ControllerMethods.DELETE_SOFT, PERM_DENY_ALL);
		 put(ControllerMethods.GET_HISTRORY, PERM_DENY_ALL);
		 put(ControllerMethods.GET_OWNERS_HISTORY, PERM_DENY_ALL);
		 put(ControllerMethods.EDIT_VERSION_DTO, PERM_DENY_ALL);

	  }
   };

   public Boolean customPreAuthorize(Object root, ControllerMethods method) {
	  Expression exp = (new SpelExpressionParser()).parseExpression(auth.get(method));
	  return exp.getValue(new StandardEvaluationContext(root), Boolean.class);
   }

   protected Map<String, Object> getFilterMap(MultiValueMap<String, String> params) {
	  Map<String, Object> filter = new HashMap<String, Object>();

	  try {
		 for (Map.Entry<String, List<String>> entry : params.entrySet()) {
			try {
			   if (entry.getValue().size() == 0) {
				  continue;
			   }
			   if (entry.getKey().startsWith("t_")) {
				  filter.put(entry.getKey().substring(2), entry.getValue().get(0));
			   } else if (entry.getKey().startsWith("v_")) {
				  filter.put(entry.getKey().substring(2), entry.getValue().get(0));
			   } else if (entry.getKey().startsWith("d_")) {
				  filter.put(entry.getKey().substring(2), BaseGridViewParams.parseDate(entry.getValue().get(0)));
			   } else if (entry.getKey().startsWith("i_")) {
				  filter.put(entry.getKey().substring(2), new Long(entry.getValue().get(0)));
			   } else if (entry.getKey().startsWith("l_")) {
				  String arg = entry.getValue().get(0);
				  for (int it = 1; it < entry.getValue().size(); it++) {
					 arg += ", " + entry.getValue().get(it);
				  }
				  filter.put(entry.getKey().substring(2), arg);
			   }
			} catch (Throwable t) {
			   LOGGER.error(t.getMessage(), t);
			}
		 }
	  } catch (Throwable t) {
		 LOGGER.error(t.getMessage(), t);
	  }

	  return filter;
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
	  JsonGridData gridData = service
		  .getGridViews(new BaseGridViewParams(page, count, orderBy, orderByAsc, getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(gridData);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получить список объектов для выпадаюшего списка.
	*
	* @param params фильтры.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = RM_BASE_SLIST, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_SLIST)")
   public ResponseEntity<?> getSelectList(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  List<SelectItem> selItems = service.getSelectList(new BaseSelectListParams(getFilterMap(params)));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(selItems);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Получить объект по id.
	*
	* @param id id.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = RM_BASE_ID, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_DTO)")
   public ResponseEntity<?> getDTO(@PathVariable("id") Long id)
	   throws PBKException {
	  DTO dto = service.getDTOById(id);
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(dto);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Создать объект.
	*
	* @param dto dto.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = RM_BASE, method = RequestMethod.POST)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).ADD_DTO)")
   public ResponseEntity<?> insertDTO(@Valid @RequestBody DTO dto)
	   throws PBKException {
	  service.saveDTO(dto);
	  return new ResponseEntity<>(HttpStatus.OK);
   }

   /**
	* Обновить объект.
	*
	* @param dto dto.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = RM_BASE, method = RequestMethod.PUT)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).EDIT_DTO)")
   public ResponseEntity<?> updateDTO(@Valid @RequestBody DTO dto)
	   throws PBKException {
	  service.saveDTO(dto);
	  return new ResponseEntity<>(HttpStatus.OK);
   }

   /**
	* Удалить объекты по id.
	*
	* @param ids список id.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = RM_BASE_DELETE, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).DELETE)")
   public ResponseEntity<?> delete(@RequestParam("ids") List<Long> ids)
	   throws PBKException {
	  customDeleteValidation(ids);
	  service.delete(ids);
	  return new ResponseEntity<>(HttpStatus.OK);
   }

   /**
	* Метод дополнительной валидации при удалении. Базовая реализация ничего не
	* делает, но можно переопределить и если что выкинуть ошибку.
	*
	* @param ids идентификаторы удаляемых сущностей.
	* @throws PBKValidationException
	*/
   protected void customDeleteValidation(List<Long> ids)
	   throws PBKValidationException {
	  // по умолчанию ничего не делаем.
   }

   /**
	* Мягко удалить объекты по id.
	*
	* @param ids список id.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = RM_BASE_DELETE_SOFT, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).DELETE_SOFT)")
   public ResponseEntity<?> deleteSoft(@RequestParam("ids") List<Long> ids,
									   @RequestParam("tryDelete") Boolean tryDelete)
	   throws PBKException {
	  customDeleteValidation(ids);
	  service.deleteSoft(ids, tryDelete);
	  return new ResponseEntity<>(HttpStatus.OK);
   }
}
