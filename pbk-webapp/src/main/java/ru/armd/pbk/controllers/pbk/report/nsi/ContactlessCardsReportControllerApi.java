package ru.armd.pbk.controllers.pbk.report.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.nsi.NsiExportCellsReportType;
import ru.armd.pbk.controllers.pbk.report.BaseReportControllerApi;
import ru.armd.pbk.services.aspose.nsi.ContactlessCardReportService;

/**
 * Контроллер для генерации отчетов БСК.
 */
@Controller
@RequestMapping(ContactlessCardsReportControllerApi.RM_CONTROLLER_PATH)
public class ContactlessCardsReportControllerApi
	extends BaseReportControllerApi {

   public static final String RM_PATH = "/reports/aspose/nsi/contactless-cards";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;

   /**
	* Конструктор.
	*
	* @param reportService сервис
	*/
   @Autowired
   public ContactlessCardsReportControllerApi(ContactlessCardReportService reportService) {
	  super(reportService);
   }

   @Override
   protected IReportType getReportType() {
	  return new NsiExportCellsReportType();
   }

}
