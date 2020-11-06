package ru.armd.pbk.controllers.pbk.report.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.nsi.NsiExportCellsReportType;
import ru.armd.pbk.controllers.pbk.report.BaseReportControllerApi;
import ru.armd.pbk.services.aspose.tasks.TaskWithdrawCardReportService;

/**
 * Контроллер, выгрузки отчета изъятых карт.
 */
@Controller
@RequestMapping(TaskWithdrawCardExportReportControllerApi.RM_CONTROLLER_PATH)
public class TaskWithdrawCardExportReportControllerApi
	extends BaseReportControllerApi {

   public static final String RM_PATH = "/reports/aspose/tasks/withdrawn-cards";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   TaskWithdrawCardExportReportControllerApi(TaskWithdrawCardReportService reportService) {
	  super(reportService);
   }

   @Override
   protected IReportType getReportType() {
	  return new NsiExportCellsReportType();
   }

}
