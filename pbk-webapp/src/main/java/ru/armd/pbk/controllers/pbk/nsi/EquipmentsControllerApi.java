package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.core.controllers.BaseVersionControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.Equipment;
import ru.armd.pbk.dto.nsi.EquipmentDTO;
import ru.armd.pbk.services.nsi.EquipmentService;

/**
 * rest контроллер для работы с бортовым оборудованием.
 */
@Controller
@RequestMapping(EquipmentsControllerApi.RM_CONTROLLER_PATH)
public class EquipmentsControllerApi
	extends BaseVersionControllerApi<Equipment, EquipmentDTO> {

   public static final String RM_PATH = "/nsi/equipments";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_EQUIPMENTS = "hasAnyRole('DEBUG_TO_REPLACE','EQUIPMENTS')";
   static final String PERM_EDIT_EQUIPMENTS = "hasAnyRole('DEBUG_TO_REPLACE','EQUIPMENTS_EDIT')";


   @Autowired
   EquipmentsControllerApi(EquipmentService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_EQUIPMENTS);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_EQUIPMENTS);
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_EQUIPMENTS);
	  this.auth.put(ControllerMethods.EDIT_DTO, PERM_EDIT_EQUIPMENTS);
	  this.auth.put(ControllerMethods.DELETE_SOFT, PERM_EDIT_EQUIPMENTS);
	  this.auth.put(ControllerMethods.GET_SLIST, PERM_ALLOW_ALL);
	  this.auth.put(ControllerMethods.GET_HISTRORY, PERM_READ_EQUIPMENTS);
	  this.auth.put(ControllerMethods.EDIT_VERSION_DTO, PERM_EDIT_EQUIPMENTS);
   }
}

