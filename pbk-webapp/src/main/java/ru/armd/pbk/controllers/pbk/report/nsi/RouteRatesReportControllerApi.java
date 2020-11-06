package ru.armd.pbk.controllers.pbk.report.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.nsi.RouteRatesCellsReportType;
import ru.armd.pbk.controllers.pbk.report.BaseReportControllerApi;
import ru.armd.pbk.services.aspose.nsi.RouteRateReportService;

/**
 * Created by Yakov Volkov.
 */
@Controller
@RequestMapping(RouteRatesReportControllerApi.RM_CONTROLLER_PATH)
public class RouteRatesReportControllerApi
	extends BaseReportControllerApi {

   public static final String RM_PATH = "/reports/aspose/nsi/routerates";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   @Autowired
   RouteRatesReportControllerApi(RouteRateReportService reportService) {
	  super(reportService);
   }

   @Override
   protected IReportType getReportType() {
	  return new RouteRatesCellsReportType();
   }
}