package ru.armd.pbk.aspose.task;

import ru.armd.pbk.aspose.core.AbstractReportProcessor;
import ru.armd.pbk.aspose.core.AbstractReportType;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;

/**
 * Тип выгружаемой печатной-отчетной формы справочника заданий.
 */
public class TaskExportCellsReportType
	extends AbstractReportType
	implements IReportType {

   /**
	* Конструктор типа выгружаемой печатной-отчетной формы справочника заданий.
	*/
   public TaskExportCellsReportType() {
	  super("pbk_task.xlsx", "Справочник ", "RT_TEMP_Pbk_task.xlsx", "pbk_task", ReportFormat.XLSX, ReportFormat.XLSX, true);
   }

   @Override
   public AbstractReportProcessor instantiateProcessor() {
	  return new TaskExportCellsReportProcessor();
   }

}
