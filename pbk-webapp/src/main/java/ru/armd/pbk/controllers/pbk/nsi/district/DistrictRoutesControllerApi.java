package ru.armd.pbk.controllers.pbk.nsi.district;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.district.DistrictRoute;
import ru.armd.pbk.dto.nsi.district.DistrictRouteDTO;
import ru.armd.pbk.services.nsi.district.DistrictRouteService;

/**
 * Контроллер для работы с маршрутами райнов.
 */
@Controller
@RequestMapping(DistrictRoutesControllerApi.RM_CONTROLLER_PATH)
public class DistrictRoutesControllerApi
	extends BaseVersionControllerApi<DistrictRoute, DistrictRouteDTO> {

   public static final String RM_PATH = "/nsi/districts/routes";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;


   @Autowired
   DistrictRoutesControllerApi(DistrictRouteService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, DistrictsControllerApi.PERM_READ_DISTRICTS);
	  this.auth.put(ControllerMethods.ADD_DTO, DistrictsControllerApi.PERM_EDIT_DISTRICTS);
	  this.auth.put(ControllerMethods.DELETE_SOFT, DistrictsControllerApi.PERM_EDIT_DISTRICTS);
   }

}