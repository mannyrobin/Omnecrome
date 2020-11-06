package ru.armd.pbk.aspose.plan;

import ru.armd.pbk.aspose.core.AbstractReportProcessor;
import ru.armd.pbk.aspose.core.AbstractReportType;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;

/**
 * Тип выгружаемой формы "Табель".
 */
public class TimesheetReportType
	extends AbstractReportType
	implements IReportType {

   /**
	* Конструктор по умолчанию.
	*/
   public TimesheetReportType() {
	  super("pbk_timesheets.xlsx", "Табель ", "pbk_timesheets.xlsx", "pbk_timesheets", ReportFormat.XLSX, ReportFormat.XLSX);
   }

   @Override
   public AbstractReportProcessor instantiateProcessor() {
	  return new TimesheetReportProcessor();
   }

}
