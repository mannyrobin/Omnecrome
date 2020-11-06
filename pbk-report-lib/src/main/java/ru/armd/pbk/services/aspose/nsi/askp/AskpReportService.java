package ru.armd.pbk.services.aspose.nsi.askp;

import org.apache.log4j.Logger;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.nsi.NsiReportBeanData;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.IReportView;
import ru.armd.pbk.exceptions.PBKAsposeReportException;
import ru.armd.pbk.services.IReportService;
import ru.armd.pbk.services.reports.AsposeReportsService;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Базовый сервис для отчетов АСКП.
 */
public abstract class AskpReportService
	extends AsposeReportsService
	implements IReportService {

   private static final Logger LOGGER = Logger.getLogger(AskpReportService.class);

   private String caption;

   /**
	* Конструктор базового сервиса отчетов АСКП.
	*
	* @param caption - заголовок.
	*/
   public AskpReportService(String caption) {
	  this.caption = caption;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   protected <View extends IReportView> List<View> prepareData(BaseGridViewParams params) {
	  return null;
   }

   @Override
   public Map<ReportFormat, File> generate(IReportType type, Map<String, Object> params)
	   throws PBKAsposeReportException {
	  BaseGridViewParams p = new BaseGridViewParams(null, null,
		  (String) params.get("sidx"), params.containsKey("sord") ? (String) params.get("sord") : "asc", params);
	  return generateReport(type, new ReportData(new NsiReportBeanData(prepareData(p), caption)));
   }

   protected String getHourHeader(Integer hour) {
	  return String.format("%02d-%02d", hour % 24, (hour + 1) % 24);
   }

}
