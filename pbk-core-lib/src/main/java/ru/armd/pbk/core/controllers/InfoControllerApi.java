package ru.armd.pbk.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.authtoken.AuthenticationManager;
import ru.armd.pbk.services.CommonService;
import ru.armd.pbk.utils.json.JsonResult;

/**
 * Контроллер с информационными утилитарными методами.
 */
@Controller
@RequestMapping(InfoControllerApi.RM_CONTROLLER_PATH)
public class InfoControllerApi
	extends BaseControllerApi {

   public static final String RM_CONTROLLER_PATH = RM_API_PATH + "/info";
   public static final String VERSION = "/version";
   public static final String USER = "/user";

   @Autowired
   CommonService commonService;

   /**
	* Получение информации о версии приложения.
	*
	* @return
	*/
   @RequestMapping(value = VERSION, method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getVersionInfo() {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(commonService.getVersionInfoDTO());
	  return new ResponseEntity(jsonResult, HttpStatus.OK);
   }

   /**
	* Получение информации о пользователе.
	*
	* @return
	*/
   @RequestMapping(value = USER, method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> getUserInfo() {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(AuthenticationManager.getUserInfo().getName());
	  return new ResponseEntity(jsonResult, HttpStatus.OK);
   }
}
