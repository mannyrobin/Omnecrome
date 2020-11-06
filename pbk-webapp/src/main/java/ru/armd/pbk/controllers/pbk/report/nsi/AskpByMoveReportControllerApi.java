package ru.armd.pbk.controllers.pbk.report.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.nsi.askp.AskpByMoveReportType;
import ru.armd.pbk.controllers.pbk.report.BaseReportControllerApi;
import ru.armd.pbk.services.aspose.nsi.askp.AskpByMoveReportService;

/**
 * Контроллер отчета АСКП (данные по проходам) по выходам.
 */
@Controller
@RequestMapping(AskpByMoveReportControllerApi.RM_CONTROLLER_PATH)
public class AskpByMoveReportControllerApi
	extends BaseReportControllerApi {

   public static final String RM_PATH = "/reports/aspose/nsi/askp-by-move";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   /**
	* Конструктор.
	*
	* @param reportService сервис
	*/
   @Autowired
   public AskpByMoveReportControllerApi(AskpByMoveReportService reportService) {
	  super(reportService);
   }

   @Override
   protected IReportType getReportType() {
	  return new AskpByMoveReportType();
   }

}
