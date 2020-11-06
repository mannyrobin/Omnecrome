package ru.armd.pbk.controllers.pbk.report;

import org.apache.log4j.Logger;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.aspose.core.AsposeReports;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.core.controllers.BaseControllerApi;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.exceptions.PBKAsposeReportException;
import ru.armd.pbk.services.IReportService;
import ru.armd.pbk.utils.AttachReportFileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Базовый контроллер для генерации отчетов.
 */
public abstract class BaseReportControllerApi
	extends BaseControllerApi {

   public static final Logger LOGGER = Logger.getLogger(BaseReportControllerApi.class);
   public static final String RM_REPORT = "";


   private IReportService reportService;

   /**
	* Конструктор контроллера генерации отчетов.
	*
	* @param reportService - сервис генерации отчетов.
	*/
   public BaseReportControllerApi(IReportService reportService) {
	  this.reportService = reportService;
   }

   /**
	* Получить тип отчета.
	*
	* @return тип отчета.
	*/
   protected abstract IReportType getReportType();

   protected Map<String, Object> getFilterMap(MultiValueMap<String, String> params) {
	  Map<String, Object> filter = new HashMap<String, Object>();

	  try {
		 for (Map.Entry<String, List<String>> entry : params.entrySet()) {
			try {
			   if (entry.getValue().size() == 0) {
				  continue;
			   }
			   if (entry.getKey().startsWith("t_")) {
				  filter.put(entry.getKey().substring(2), entry.getValue().get(0));
			   } else if (entry.getKey().startsWith("d_")) {
				  filter.put(entry.getKey().substring(2), BaseGridViewParams.parseDate(entry.getValue().get(0)));
			   } else if (entry.getKey().startsWith("i_")) {
				  filter.put(entry.getKey().substring(2), new Long(entry.getValue().get(0)));
			   } else if (entry.getKey().startsWith("l_")) {
				  String arg = entry.getValue().get(0);
				  for (int it = 1; it < entry.getValue().size(); it++) {
					 arg += ", " + entry.getValue().get(it);
				  }
				  filter.put(entry.getKey().substring(2), arg);
			   }
			} catch (Throwable t) {
			   LOGGER.error(t.getMessage(), t);
			}
		 }
	  } catch (Throwable t) {
		 LOGGER.error(t.getMessage(), t);
	  }

	  return filter;
   }

   /**
	* Выгрузить один отчет в нужном формате.
	*
	* @param format   - формат отчета.
	* @param request  - запрос.
	* @param response - ответ.
	* @param params   - параметры.
	*/
   @ResponseBody
   @RequestMapping(value = RM_REPORT, method = RequestMethod.GET)
   public void exportReport(@RequestParam(value = "format") String format, HttpServletRequest request,
							HttpServletResponse response,
							@RequestParam MultiValueMap<String, String> params) {
	  IReportType type = getReportType();
	  ReportFormat rformat = AsposeReports.checkReportFormatOwnOrPdf(type, format);
	  type.setReportFormat(rformat);
	  Map<ReportFormat, File> result = reportService.generate(type, getFilterMap(params));
	  if (result == null) {
		 throw new PBKAsposeReportException("Ошибка генерации отчета");
	  }
	  Map<String, File> files = new HashMap<String, File>();
	  files.put(result.get(rformat).getName(), result.get(rformat));
	  AttachReportFileUtils.composeReportFiles(files, response, type, false);
   }

}
