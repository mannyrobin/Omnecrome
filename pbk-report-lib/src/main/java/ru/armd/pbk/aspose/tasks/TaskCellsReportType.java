package ru.armd.pbk.aspose.tasks;

import ru.armd.pbk.aspose.core.AbstractReportProcessor;
import ru.armd.pbk.aspose.core.AbstractReportType;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;

/**
 * Тип выгружаемой печатной-отчетной формы "Задание контролеру".
 */
public class TaskCellsReportType
	extends AbstractReportType
	implements IReportType {

   /**
	* Конструктор по умолванию.
	*/
   public TaskCellsReportType() {
	  super("pbk_task.xlsx", "Задание контролеру ", "RT_TEMP_ControllerTask.xlsx", "controller_task", ReportFormat.XLSX, ReportFormat.XLSX);
   }

   @Override
   public AbstractReportProcessor instantiateProcessor() {
	  return new TaskCellsReportProcessor();
   }

}
