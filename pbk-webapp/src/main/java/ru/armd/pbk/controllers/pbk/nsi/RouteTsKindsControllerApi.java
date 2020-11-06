package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.RouteTsKind;
import ru.armd.pbk.dto.nsi.RouteTsKindDTO;
import ru.armd.pbk.services.nsi.RouteTsKindService;

/**
 * Контроллер видов транспорта маршрута.
 */
@Controller
@RequestMapping(RouteTsKindsControllerApi.RM_CONTROLLER_PATH)
public class RouteTsKindsControllerApi
	extends BaseDomainControllerApi<RouteTsKind, RouteTsKindDTO> {

   public static final String RM_PATH = "/nsi/route-ts-kinds";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_ROUTE_TS_KINDS = "hasAnyRole('DEBUG_TO_REPLACE','ROUTE_TS_KINDS')";
   static final String PERM_EDIT_ROUTE_TS_KINDS = "hasAnyRole('DEBUG_TO_REPLACE','ROUTE_TS_KINDS_EDIT')";

   @Autowired
   RouteTsKindsControllerApi(RouteTsKindService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_ROUTE_TS_KINDS);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_ROUTE_TS_KINDS);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_ROUTE_TS_KINDS);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_ROUTE_TS_KINDS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_ROUTE_TS_KINDS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
   }

}
