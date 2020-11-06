package ru.armd.pbk.aspose.nsi.bsos;

import ru.armd.pbk.aspose.core.AbstractReportProcessor;
import ru.armd.pbk.aspose.core.AbstractReportType;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;

/**
 * Тип выгружаемой печатной-отчетной формы "Акт об изъятии проездного документа".
 */
public class BsoCellsReportType
	extends AbstractReportType
	implements IReportType {

   /**
	* Конструктор по умолванию.
	*/
   public BsoCellsReportType() {
	  super("pbk_bso.xlsx", "БСО", "RT_TEMP_BSO.xlsx", "bso", ReportFormat.XLSX, ReportFormat.XLSX);
   }

   @Override
   public AbstractReportProcessor instantiateProcessor() {
	  return new BsoCellsReportProcessor();
   }

}
