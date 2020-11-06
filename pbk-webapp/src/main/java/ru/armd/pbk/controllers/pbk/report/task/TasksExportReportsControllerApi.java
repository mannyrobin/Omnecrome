package ru.armd.pbk.controllers.pbk.report.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.aspose.core.AsposeReports;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.task.TaskExportCellsReportType;
import ru.armd.pbk.aspose.tasks.TaskCellsReportType;
import ru.armd.pbk.controllers.pbk.report.BaseReportControllerApi;
import ru.armd.pbk.exceptions.PBKAsposeReportException;
import ru.armd.pbk.services.aspose.tasks.TaskExportReportService;
import ru.armd.pbk.services.aspose.tasks.TaskReportService;
import ru.armd.pbk.utils.AttachReportFileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер для генерации отчетов заданий.
 */
@Controller
@RequestMapping(TasksExportReportsControllerApi.RM_CONTROLLER_PATH)
public class TasksExportReportsControllerApi
	extends BaseReportControllerApi {

   public static final String RM_PATH = "/reports/aspose/pbk/tasks";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   public static final String RM_REPORT = "/detail";

   @Autowired
   private TaskReportService reportService;

   @Autowired
   TasksExportReportsControllerApi(TaskExportReportService service) {
	  super(service);
   }

   /**
	* Сгенерировать файл отчёта указанного формата для указанных заданий и поместить его в http-response для передачи клиенту.
	*
	* @param format   формат отчёта
	* @param ids      id заданий
	* @param request  http request
	* @param response http response
	*/
   @ResponseBody
   @RequestMapping(value = RM_REPORT, method = RequestMethod.GET)
   public void exportReport(@RequestParam(value = "format") String format, @RequestParam("ids") List<Long> ids, HttpServletRequest request,
							HttpServletResponse response) {
	  if (ids != null) {
		 IReportType type = new TaskCellsReportType();
		 if (format.equals("zip")) {
			ReportFormat rformat = AsposeReports.checkReportFormatOwnOrPdf(type, "xlsx");
			type.setReportFormat(rformat);
			List<Map<ReportFormat, File>> result = reportService.generateMultiple(type, ids);
			if (result == null) {
			   throw new PBKAsposeReportException("Ошибка генерации отчета");
			}
			Map<String, File> files = new HashMap<>();
			for (Map<ReportFormat, File> report : result) {
			   files.put(report.get(rformat).getName(), report.get(rformat));
			}
			AttachReportFileUtils.composeZipReportFile(files, response, type);
		 } else {
			ReportFormat rformat = AsposeReports.checkReportFormatOwnOrPdf(type, format);
			type.setReportFormat(rformat);
			Map<ReportFormat, File> result = null;
			Map<String, File> files = new HashMap<>();
			if (ids.size() == 1) {
			   result = reportService.generate(type, ids.get(0));
			   files.put(result.get(rformat).getName(), result.get(rformat));
			} else if (ids.size() > 1) {
			   if (format.equals("xlsx")) {
				  result = reportService.generateMerged(type, ids);
				  files.put(result.get(rformat).getName(), result.get(rformat));
			   } else {
				  for (Long id : ids) {
					 result = reportService.generate(type, id);
					 files.put(result.get(rformat).getName(), result.get(rformat));
				  }
			   }
			} else {
			   throw new IllegalArgumentException("Не были переданы id заданий");
			}
			if (result == null) {
			   throw new PBKAsposeReportException("Ошибка генерации отчета");
			}
			AttachReportFileUtils.composeReportFiles(files, response, type, false);
		 }
	  }
   }

   @Override
   protected IReportType getReportType() {
	  return new TaskExportCellsReportType();
   }
}
