package ru.armd.pbk.controllers.pbk.report.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.nsi.NsiExportCellsReportType;
import ru.armd.pbk.controllers.pbk.report.BaseReportControllerApi;
import ru.armd.pbk.services.aspose.nsi.StopReportService;

/**
 * Контроллер для генерации отчетов остановочных пунктов.
 */
@Controller
@RequestMapping(StopsReportControllerApi.RM_CONTROLLER_PATH)
public class StopsReportControllerApi
	extends BaseReportControllerApi {

   public static final String RM_PATH = "/reports/aspose/nsi/stops";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   StopsReportControllerApi(StopReportService reportService) {
	  super(reportService);
   }

   @Override
   protected IReportType getReportType() {
	  return new NsiExportCellsReportType();
   }
}
