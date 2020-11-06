package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.TsType;
import ru.armd.pbk.dto.nsi.TsTypeDTO;
import ru.armd.pbk.services.nsi.TsTypeService;

/**
 * Контроллер для работы с типами ТС.
 */
@Controller
@RequestMapping(TsTypesControllerApi.RM_CONTROLLER_PATH)
public class TsTypesControllerApi
	extends BaseDomainControllerApi<TsType, TsTypeDTO> {

   public static final String RM_PATH = "/nsi/ts-types";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_TS_TYPES = "hasAnyRole('DEBUG_TO_REPLACE','TS_TYPES')";
   static final String PERM_EDIT_TS_TYPES = "hasAnyRole('DEBUG_TO_REPLACE','TS_TYPES_EDIT')";


   @Autowired
   TsTypesControllerApi(TsTypeService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_TS_TYPES);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_TS_TYPES);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_TS_TYPES);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_TS_TYPES);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_TS_TYPES);
   }
}
