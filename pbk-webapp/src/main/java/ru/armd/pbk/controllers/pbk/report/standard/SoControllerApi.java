package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.SoService;
import ru.armd.pbk.utils.json.JsonResult;

/**
 * Базовый контроллер для стандартных отчетов.
 *
 * @param <Domain> домен
 * @param <DTO>    дто
 */
@Component
public class SoControllerApi<Domain extends BaseDomain, DTO extends BaseDTO>
	extends BaseDomainControllerApi<Domain, DTO> {

   public static final String RM_BASE_TOTAL = "/total";

   /**
	* Получить выпадающий список.
	*
	* @param params параметры.
	*/
   @RequestMapping(value = RM_BASE_TOTAL, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).GET_VIEWS)")
   public ResponseEntity<?> getSelectList(@RequestParam MultiValueMap<String, String> params)
	   throws PBKException {
	  BaseGridView reslut = ((SoService<?, ?>) service).getGridViewTotal(getFilterMap(params));
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(reslut);
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}
