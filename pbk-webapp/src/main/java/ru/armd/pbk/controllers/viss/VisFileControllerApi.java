package ru.armd.pbk.controllers.viss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseControllerApi;
import ru.armd.pbk.core.domain.FileStream;
import ru.armd.pbk.services.viss.VisFileService;
import ru.armd.pbk.utils.AttachFileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

/**
 * rest контроллер для работы с записью журнала взаимодействия с ВИС.
 */
@Controller
@RequestMapping(VisFileControllerApi.RM_CONTROLLER_PATH)
public class VisFileControllerApi
	extends BaseControllerApi {

   public static final String RM_PATH = "/vis/file";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   static final String PERM_READ_VIS_EXCHANGES = "hasAnyRole('DEBUG_TO_REPLACE','VIS_EXCHANGES')";

   @Autowired
   VisFileService service;

   VisFileControllerApi() {
   }

   /**
	* Выгрузить файл обмена.
	*
	* @param streamId ид файла.
	* @param request  запрос.
	* @param response ответ.
	*/
   @ResponseBody
   @RequestMapping(value = "/{streamId}", method = RequestMethod.GET)
   @PreAuthorize(PERM_READ_VIS_EXCHANGES)
   public void getFile(@PathVariable(value = "streamId") String streamId,
					   HttpServletRequest request,
					   HttpServletResponse response) {

	  FileStream fs = ((VisFileService) service).getFileStream(streamId);
	  ByteArrayInputStream bais = new ByteArrayInputStream(fs.getStream());
	  AttachFileUtils.writeStreamToResponse(response, bais, fs.getName(), "application/octet-stream", false);
   }
}
