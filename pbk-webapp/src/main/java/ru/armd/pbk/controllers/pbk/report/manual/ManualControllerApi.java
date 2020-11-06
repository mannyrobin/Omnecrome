package ru.armd.pbk.controllers.pbk.report.manual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseControllerApi;
import ru.armd.pbk.services.manual.ManualReportService;
import ru.armd.pbk.utils.AttachFileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * rest контроллер отчета сводных данных.
 */
@Controller
@RequestMapping(ManualControllerApi.RM_CONTROLLER_PATH)
public class ManualControllerApi
	extends BaseControllerApi {

   public static final String RM_PATH = "/report/manual";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   public static final String RM_REPORT = "/download";

   @Autowired
   private ManualReportService manualReportService;

   /**
	* Выгрузить один отчет в нужном формате.
	*
	* @param request  - запрос.
	* @param response - ответ.
	* @throws IOException
	*/
   @ResponseBody
   @RequestMapping(value = RM_REPORT, method = RequestMethod.GET)
   public void download(HttpServletRequest request, HttpServletResponse response)
	   throws IOException {
	  AttachFileUtils.writeFileToResponse(manualReportService.generate()
		  , request, response);
   }

}
