package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.nsi.NsiReportBeanData;
import ru.armd.pbk.core.mappers.IReportMapper;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.exceptions.PBKAsposeReportException;
import ru.armd.pbk.services.IReportService;
import ru.armd.pbk.services.reports.AsposeReportsService;

import java.io.File;
import java.util.Map;

/**
 * Базовый сервис для НСИ отчетов.
 */
public class NsiReportService
	extends AsposeReportsService
	implements IReportService {

   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   private String caption;

   private IReportMapper mapper;

   /**
	* Конструктор базового сервиса отчетов НСИ.
	*
	* @param mapper  - маппер.
	* @param caption - заголовок.
	*/
   public NsiReportService(IReportMapper mapper, String caption) {
	  this.caption = caption;
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }


   @Override
   public Map<ReportFormat, File> generate(IReportType type, Map<String, Object> params)
	   throws PBKAsposeReportException {
	  BaseGridViewParams p = new BaseGridViewParams(null, null,
		  (String) params.get("sidx"), params.containsKey("sord") ? (String) params.get("sord") : "asc", params);
	  return generateReport(type, new ReportData(new NsiReportBeanData(mapper.getReportViews(p), caption)));
   }

}
