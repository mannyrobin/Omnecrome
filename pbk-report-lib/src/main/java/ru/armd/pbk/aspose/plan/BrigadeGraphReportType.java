package ru.armd.pbk.aspose.plan;

import ru.armd.pbk.aspose.core.AbstractReportProcessor;
import ru.armd.pbk.aspose.core.AbstractReportType;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;

/**
 * Тип выгружаемой формы "График бригад".
 */
public class BrigadeGraphReportType
	extends AbstractReportType
	implements IReportType {
   /**
	* Конструктор по умолчанию.
	*/
   public BrigadeGraphReportType() {
	  super("pbk_brigades_graph.xlsx", "График бригад ", "pbk_brigades_graph.xlsx", "brigades_graph", ReportFormat.XLSX, ReportFormat.XLSX);
   }

   @Override
   public AbstractReportProcessor instantiateProcessor() {
	  return new BrigadeGraphReportProcessor();
   }
}