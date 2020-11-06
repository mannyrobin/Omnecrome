package ru.armd.pbk.controllers.pbk.report.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.nsi.askp.AskpByHourReportType;
import ru.armd.pbk.controllers.pbk.report.BaseReportControllerApi;
import ru.armd.pbk.services.aspose.nsi.askp.AskpByHourReportService;

/**
 * Контроллер отчета АСКП (данные по проходам) по часам.
 */
@Controller
@RequestMapping(AskpByHourReportControllerApi.RM_CONTROLLER_PATH)
public class AskpByHourReportControllerApi
	extends BaseReportControllerApi {

   public static final String RM_PATH = "/reports/aspose/nsi/askp-by-hour";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   /**
	* Конструктор.
	*
	* @param reportService сервис
	*/
   @Autowired
   public AskpByHourReportControllerApi(AskpByHourReportService reportService) {
	  super(reportService);
   }

   @Override
   protected IReportType getReportType() {
	  return new AskpByHourReportType();
   }

}
