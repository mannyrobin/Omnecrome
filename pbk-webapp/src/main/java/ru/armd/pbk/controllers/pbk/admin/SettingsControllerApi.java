package ru.armd.pbk.controllers.pbk.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.core.Setting;
import ru.armd.pbk.dto.core.SettingDTO;
import ru.armd.pbk.services.core.SettingsService;

/**
 * Контроллер системных настроек.
 */
@Controller
@RequestMapping(SettingsControllerApi.RM_CONTROLLER_PATH)
public class SettingsControllerApi
	extends BaseDomainControllerApi<Setting, SettingDTO> {

   public static final String RM_PATH = "/admin/settings";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SETTINGS = "hasAnyRole('DEBUG_TO_REPLACE','ADMIN_SETTING')";
   static final String PERM_EDIT_SETTINGS = "hasAnyRole('DEBUG_TO_REPLACE','ADMIN_SETTING_EDIT')";

   @Autowired
   SettingsControllerApi(SettingsService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SETTINGS);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_SETTINGS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_SETTINGS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_EDIT_SETTINGS);
   }
}
