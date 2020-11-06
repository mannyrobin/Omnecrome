package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.TsModel;
import ru.armd.pbk.dto.nsi.TSModelDTO;
import ru.armd.pbk.services.nsi.TSModelService;

/**
 * rest контроллер для работы с моделями ТС.
 *
 * @author nikita_lobanov
 */
@Controller
@RequestMapping(TSModelsControllerApi.RM_CONTROLLER_PATH)
public class TSModelsControllerApi
	extends BaseDomainControllerApi<TsModel, TSModelDTO> {

   public static final String RM_PATH = "/nsi/ts-models";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_TS_MODELS = "hasAnyRole('DEBUG_TO_REPLACE','TS_MODELS')";
   static final String PERM_EDIT_TS_MODELS = "hasAnyRole('DEBUG_TO_REPLACE','TS_MODELS_EDIT')";


   @Autowired
   TSModelsControllerApi(TSModelService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_TS_MODELS);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_TS_MODELS);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_TS_MODELS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_TS_MODELS);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_TS_MODELS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }
}

