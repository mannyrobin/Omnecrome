package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.TsCapacity;
import ru.armd.pbk.dto.nsi.TsCapacityDTO;
import ru.armd.pbk.services.nsi.TsCapacityService;

/**
 * Контроллер для работы с вместимостью ТС.
 */
@Controller
@RequestMapping(TsCapacitiesControllerApi.RM_CONTROLLER_PATH)
public class TsCapacitiesControllerApi
	extends BaseDomainControllerApi<TsCapacity, TsCapacityDTO> {

   public static final String RM_PATH = "/nsi/ts-capacities";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_TS_CAPACITIES = "hasAnyRole('DEBUG_TO_REPLACE','TS_CAPACITIES')";
   static final String PERM_EDIT_TS_CAPACITIES = "hasAnyRole('DEBUG_TO_REPLACE','TS_CAPACITIES_EDIT')";


   @Autowired
   TsCapacitiesControllerApi(TsCapacityService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_TS_CAPACITIES);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_TS_CAPACITIES);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_TS_CAPACITIES);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_TS_CAPACITIES);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_TS_CAPACITIES);
   }

}
