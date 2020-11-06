package ru.armd.pbk.aspose.core;

import org.apache.commons.pool.BaseKeyedPoolableObjectFactory;

/**
 * Фабрика, используемая пулом процессоров отчётов.
 */
public class PoolingReportProcessorFactory
	extends BaseKeyedPoolableObjectFactory<IReportType, IReportProcessor> {

   public IReportProcessor makeObject(IReportType reportType)
	   throws Exception {
	  if (reportType == null) {
		 return null;
	  }

	  AbstractReportProcessor processor = reportType.instantiateProcessor();
	  // TODO каждому типу отчета соответствует 1 формат выгрузки. Расширить.

	  processor.setRootFolder(AsposeReports.getRootDirectory());
	  processor.setTemplateName(AsposeReports.getPreprocessedTemplateName(reportType));
	  return processor;
   }

   public void destroyObject(IReportType reportType, IReportProcessor object) {
	  object.deleteReportFiles();
	  object.cleanup();
   }
}
