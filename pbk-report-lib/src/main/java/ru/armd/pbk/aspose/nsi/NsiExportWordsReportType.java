package ru.armd.pbk.aspose.nsi;

import ru.armd.pbk.aspose.core.AbstractReportProcessor;
import ru.armd.pbk.aspose.core.AbstractReportType;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.exceptions.PBKAsposeReportException;

/**
 * Тип выгружаемой печатной-отчетной формы справочников НСИ.
 */
public class NsiExportWordsReportType
	extends AbstractReportType
	implements IReportType {

   /**
	* Конструктор типа выгружаемой печатной-отчетной формы справочников НСИ.
	*/
   public NsiExportWordsReportType() {
	  super("pbk_nsi.doc", "Справочник ", "RT_TEMP_Pbk_Nsi.doc", "pbk_nsi", ReportFormat.MSWORD, ReportFormat.MSWORD);
   }

   @Override
   public AbstractReportProcessor instantiateProcessor() {
	  throw new PBKAsposeReportException("Report processor not found");
   }

}
