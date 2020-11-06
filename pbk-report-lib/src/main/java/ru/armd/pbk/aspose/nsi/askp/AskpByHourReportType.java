package ru.armd.pbk.aspose.nsi.askp;

import ru.armd.pbk.aspose.core.AbstractReportProcessor;
import ru.armd.pbk.aspose.core.AbstractReportType;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;

/**
 * Тип отчета по часам.
 */
public class AskpByHourReportType
	extends AbstractReportType
	implements IReportType {

   /**
	* Конструктор.
	*/
   public AskpByHourReportType() {
	  super("pbk_nsi_askp.xlsx", "Справочник ", "RT_TEMP_Pbk_Nsi_askp.xlsx", "pbk_nsi_askp", ReportFormat.XLSX, ReportFormat.XLSX, true);
   }

   @Override
   public AbstractReportProcessor instantiateProcessor() {
	  return new AskpCellsReportProcessor();
   }

}
