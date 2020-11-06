package ru.armd.pbk.aspose.nsi;

import ru.armd.pbk.aspose.core.AbstractReportProcessor;
import ru.armd.pbk.aspose.core.AbstractReportType;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;

/**
 * Тип выгружаемой печатной-отчетной формы справочников НСИ.
 */
public class NsiExportCellsReportType
	extends AbstractReportType
	implements IReportType {

   /**
	* Конструктор типа выгружаемой печатной-отчетной формы справочников НСИ.
	*/
   public NsiExportCellsReportType() {
	  super("pbk_nsi.xlsx", "Справочник ", "RT_TEMP_Pbk_Nsi.xlsx", "pbk_nsi", ReportFormat.XLSX, ReportFormat.XLSX, true);
   }

   @Override
   public AbstractReportProcessor instantiateProcessor() {
	  return new NsiExportCellsReportProcessor();
   }

}
