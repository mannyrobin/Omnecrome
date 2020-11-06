package ru.armd.pbk.aspose.report.standard;

import java.util.Date;

/**
 * Класс для построения имени файла печатной формы отдельного стандартного отчёта.
 */
public class SoReportSingleNameData
	extends AbstractSoReportNameData {

   /**
	* Конструктор по типу отчёта и времени генерации отчёта.
	*
	* @param soNumber       тип отчёта
	* @param reportDateTime время генерации отчёта
	*/
   public SoReportSingleNameData(Integer soNumber, Date reportDateTime) {
	  super(soNumber, reportDateTime);
   }
}
