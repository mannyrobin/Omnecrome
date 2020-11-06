package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.services.So24Service;
import ru.armd.pbk.utils.json.JsonResult;

/**
 * Контроллер стандартного отчёта "Сводные данные по наряд заданиям".
 */
@Controller
@RequestMapping(So24ControllerApi.RM_CONTROLLER_PATH)
public class So24ControllerApi
	extends SoControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/report/standard/so-24";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_SO24 = "hasAnyRole('DEBUG_TO_REPLACE','REPORTS_STANDARD24')";

   @Autowired
   So24ControllerApi(So24Service service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_SO24);
   }

    @RequestMapping(value = "/task-check-type", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getTaskCheckType() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(((So24Service) service).getTaskCheckType());
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }
}
