package ru.armd.pbk.services;

import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.exceptions.PBKAsposeReportException;

import java.io.File;
import java.util.Map;

/**
 * Интерфейс для сервисов отчетов.
 */
public interface IReportService {

   /**
	* Генерация отчета.
	*
	* @param type   тип отчета.
	* @param params параметры.
	* @return
	* @throws PBKAsposeReportException
	*/
   Map<ReportFormat, File> generate(IReportType type, Map<String, Object> params)
	   throws PBKAsposeReportException;

}
