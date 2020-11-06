package ru.armd.pbk.controllers.pbk.report.standard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.aspose.core.AsposeReports;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.report.standard.SoCellsReportType;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.exceptions.PBKAsposeReportException;
import ru.armd.pbk.services.aspose.standard.SoReportService;
import ru.armd.pbk.utils.AttachReportFileUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Контроллер выгрузки стандартных отчётов.
 */
@Controller
@RequestMapping(SoExportReportsControllerApi.RM_CONTROLLER_PATH)
public class SoExportReportsControllerApi
	extends BaseDomainControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/reports/aspose/report/standard/so-{number:(?:[1-9]|1[0-9]|2[0-9])}";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   public static final String RM_REPORT = "/export";
   private static final String SORD_KEY = "sord";
   private static final String SIDX_KEY = "sidx";

   @Autowired
   private SoReportService reportService;

   /**
	* Выгрузить стандартный отчёт с указанным номером в файл указанного формата.
	*
	* @param soNumber номер отчёта
	* @param format   формат файла отчёта
	* @param params   параметры фильтрации
	* @param response http-ответ для прикрепления файла отчёта
	*/
   @ResponseBody
   @RequestMapping(value = RM_REPORT, method = RequestMethod.GET)
   public void exportReport(@PathVariable("number") Integer soNumber,
							@RequestParam(value = "format") String format,
							@RequestParam MultiValueMap<String, String> params,
							HttpServletResponse response) {
	  IReportType type = new SoCellsReportType(soNumber);
	  Map<String, File> files = new HashMap<>();
	  ReportFormat rformat = AsposeReports.checkReportFormatOwnOrPdf(type, format);
	  type.setReportFormat(rformat);
	  Map<String, Object> filter = getFilterMap(params);
	  BaseGridViewParams viewParams = new BaseGridViewParams(1L, Long.MAX_VALUE,
		  (String) filter.get(SIDX_KEY), (String) filter.get(SORD_KEY), filter);
	  Map<ReportFormat, File> result = reportService.generate(soNumber, viewParams, type);
	  if (result == null) {
		 throw new PBKAsposeReportException("Ошибка генерации отчета");
	  }
	  files.put(result.get(rformat).getName(), result.get(rformat));
	  if (format.equals("zip")) {
		 AttachReportFileUtils.composeZipReportFile(files, response, type);
	  } else {
		 AttachReportFileUtils.composeReportFiles(files, response, type, false);
	  }
   }

}
