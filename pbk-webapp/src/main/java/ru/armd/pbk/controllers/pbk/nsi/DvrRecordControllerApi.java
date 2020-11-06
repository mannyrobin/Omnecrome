package ru.armd.pbk.controllers.pbk.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.dvr.DvrRecord;
import ru.armd.pbk.services.dvr.DvrRecordService;
import ru.armd.pbk.utils.AttachFileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;

/**
 * Контроллер записей с видеорегистраторами.
 */
@Controller
@RequestMapping(DvrRecordControllerApi.RM_CONTROLLER_PATH)
public class DvrRecordControllerApi
	extends BaseDomainControllerApi<DvrRecord, BaseDTO> {

   public static final String RM_PATH = "/nsi/dvrs/records";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   public static final String RM_BIND = "/bind";

   static final String PERM_READ_DVRS = "hasAnyRole('DEBUG_TO_REPLACE','DVRS')";

   @Autowired
   DvrRecordControllerApi(DvrRecordService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_DVRS);
   }

   /**
	* Привязать проверку ПУсК к заданию.
	*
	* @param taskId - ИД задания.
	*/
   @RequestMapping(value = RM_BIND, method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> bind(@RequestParam("taskId") Long taskId) {
	  ((DvrRecordService) service).bind(taskId);
	  return new ResponseEntity<>(HttpStatus.OK);
   }

   /**
	* Воспроизвести видео.
	* Возвращает часть видеофайла.
	*
	* @param request  запрос.
	* @param response ответ.
	* @param url      урл, записи с видеорегистратора в ДОЗОР ПРО.
	* @throws Exception
	*/
   @RequestMapping(value = "/play", method = RequestMethod.GET)
   @ResponseBody
   public void play(HttpServletRequest request, HttpServletResponse response, String url)
	   throws Exception {
	  AttachFileUtils.writeVideoToPartResponse(new URL(url), request, response);
   }

	/**
	 * Скачать видео
	 *
	 * @param request  запрос.
	 * @param response ответ.
	 * @param url      урл, записи с видеорегистратора в ДОЗОР ПРО.
	 * @throws Exception
	 */
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	@ResponseBody
	public void load(HttpServletRequest request, HttpServletResponse response, String url)
			throws Exception {
		AttachFileUtils.writeVideoToResponse(new URL(url), request, response);
	}
}
