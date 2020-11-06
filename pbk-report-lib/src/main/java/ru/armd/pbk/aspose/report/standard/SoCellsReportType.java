package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.aspose.core.AbstractReportProcessor;
import ru.armd.pbk.aspose.core.AbstractReportType;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;

/**
 * Тип выгружаемой печатной-отчетной формы стандартного отчёта.
 */
public class SoCellsReportType
	extends AbstractReportType
	implements IReportType {

   private Integer soNumber;

   /**
	* Конструктор по умолчанию.
	*
	* @param soNumber номер типа стандартного отчёта (1-18)
	*/
   public SoCellsReportType(Integer soNumber) {
	  super("pbk_so" + soNumber + ".xlsx",
		  "Стандартный отчёт",
		  "RT_TEMP_ControllerSo" + soNumber + ".xlsx",
		  "controller_so" + soNumber,
		  ReportFormat.XLSX,
		  ReportFormat.XLSX);
	  this.soNumber = soNumber;
   }



   @Override
   public AbstractReportProcessor instantiateProcessor() {
	  switch (soNumber) {
		 case 1:
			return new So1CellsReportProcessor();
		 case 2:
			return new So2CellsReportProcessor();
		 case 3:
			return new So3CellsReportProcessor();
		 case 4:
			return new So4CellsReportProcessor();
		 case 5:
			return new So5CellsReportProcessor();
		 case 6:
			return new So6CellsReportProcessor();
		 case 7:
			return new So7CellsReportProcessor();
		 case 8:
			return new So8CellsReportProcessor();
		 case 9:
			return new So9CellsReportProcessor();
		 case 10:
			return new So10CellsReportProcessor();
		 case 11:
			return new So11CellsReportProcessor();
		 case 12:
			return new So12CellsReportProcessor();
		 case 13:
			return new So13CellsReportProcessor();
		 case 14:
			return new So14CellsReportProcessor();
		 case 15:
			return new So15CellsReportProcessor();
		 case 16:
			return new So16CellsReportProcessor();
		 case 17:
			return new So17CellsReportProcessor();
		 case 18:
			return new So18CellsReportProcessor();
		 case 19:
			return new So19CellsReportProcessor();
		 case 20:
			return new So20CellReportProcessor();
		 case 21:
			return new So21CellsReportProcessor();
		 case 22:
			return new So22CellsReportProcessor();
		 case 23:
			return new So23CellsReportProcessor();
		 case 24:
		  	return new So24CellsReportProcessor();
		 case 25:
		 	return new So25CellsReportProcessor();
		 default:
			return null; 
	  }
   }
}
