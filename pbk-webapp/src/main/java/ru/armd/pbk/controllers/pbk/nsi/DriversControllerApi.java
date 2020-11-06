package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.Driver;
import ru.armd.pbk.dto.nsi.DriverDTO;
import ru.armd.pbk.services.nsi.DriverService;

/**
 * rest контроллер для работы с водителями.
 */
@Controller
@RequestMapping(DriversControllerApi.RM_CONTROLLER_PATH)
public class DriversControllerApi
	extends BaseVersionControllerApi<Driver, DriverDTO> {

   public static final String RM_PATH = "/nsi/drivers";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_DRIVERS = "hasAnyRole('DEBUG_TO_REPLACE','DRIVERS')";
   static final String PERM_EDIT_DRIVERS = "hasAnyRole('DEBUG_TO_REPLACE','DRIVERS_EDIT')";


   @Autowired
   DriversControllerApi(DriverService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_DRIVERS);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_DRIVERS);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_DRIVERS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_DRIVERS);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_DRIVERS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_HISTRORY, PERM_READ_DRIVERS);
	  this.auth.put(ControllerMethods.EDIT_VERSION_DTO, PERM_EDIT_DRIVERS);
   }
}

