package ru.armd.pbk.aspose.core;

/**
 * Фабрика процессоров отчётов.
 */
public class ReportProcessorFactory {

   /**
	* Возвращает процессор отчета по его типу без предпроцесса.
	*
	* @param reportType Тип отчёта.
	* @return Процессор отчёта, который можно использовать, если соответствующий шаблон отчёта уже предобработан.
	* @throws Exception
	*/
   public IReportProcessor getProcessor(IReportType reportType)
	   throws Exception {
	  return getProcessor(reportType, false);
   }

   /**
	* Возвращает процессор отчета по его типу после предпроцесса.
	*
	* @param reportType Тип отчёта.
	* @return Процессор отчёта, требующий предварительную предобработку шаблона.
	* @throws Exception
	*/
   public IReportProcessor getProcessorRaw(IReportType reportType)
	   throws Exception {
	  return getProcessor(reportType, true);
   }

   private IReportProcessor getProcessor(IReportType reportType, boolean isPreprocess)
	   throws Exception {
	  if (reportType == null) {
		 return null;
	  }
	  AbstractReportProcessor processor = reportType.instantiateProcessor();
	  // TODO каждому типу отчета соответствует 1 формат выгрузки. Расширить.

	  processor.setRootFolder(AsposeReports.getRootDirectory());
	  if (isPreprocess) {
		 processor.setTemplateName(reportType.getTemplateName());
	  } else {
		 processor.setTemplateName(AsposeReports.getPreprocessedTemplateName(reportType));
	  }
	  return processor;
   }
}
