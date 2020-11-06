package ru.armd.pbk.services.aspose.nsi.bsos;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.nsi.bsos.BsoReportBeanData;
import ru.armd.pbk.exceptions.PBKAsposeReportException;
import ru.armd.pbk.mappers.bsos.BsoReportMapper;
import ru.armd.pbk.services.reports.AsposeReportsService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Сервис генерации печатных форм актов об изъятии.
 */
@Service
public class BsosReportService
	extends AsposeReportsService {

   private static final Logger LOGGER = Logger.getLogger(BsosReportService.class);

   @Autowired
   private BsoReportMapper bsoReportMapper;

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Генерирует печатную форму актов об изъятии.
	*
	* @param type           свойства выходной печатной формы
	* @param reportBeanData данные
	* @return результат
	* @throws PBKAsposeReportException
	*/
   public Map<ReportFormat, File> generate(IReportType type, BsoReportBeanData reportBeanData)
	   throws PBKAsposeReportException {
	  return generateReport(type, new ReportData(reportBeanData), true);
   }

   /**
	* Генерирует список печатных форм актов об изъятии.
	*
	* @param type свойства выходной печатной формы
	* @param data данные
	* @return результат
	* @throws PBKAsposeReportException
	*/
   public List<Map<ReportFormat, File>> generateMultiple(IReportType type, List<BsoReportBeanData> data)
	   throws PBKAsposeReportException {
	  List<ReportData> bsoReportDataList = new ArrayList<>();
	  for (BsoReportBeanData reportBeanData : data) {
		 bsoReportDataList.add(new ReportData(reportBeanData));
	  }
	  return generateReports(type, bsoReportDataList, true);
   }

   /**
	* Генерирует печатную форму, сформированную из отдельных печатных форм
	* актов об изъятии с расположением их рядом на форме.
	*
	* @param type тип отчета
	* @param data данные
	* @return результат
	* @throws PBKAsposeReportException
	*/
   public Map<ReportFormat, File> generateMerged(IReportType type, List<BsoReportBeanData> data)
	   throws PBKAsposeReportException {
	  List<ReportData> bsoReportDataList = new ArrayList<>();
	  for (BsoReportBeanData reportBeanData : data) {
		 bsoReportDataList.add(new ReportData(reportBeanData));
		 bsoReportDataList.add(new ReportData(reportBeanData));
	  }
	  return generateMergedReport(type, bsoReportDataList);
   }

   /**
	* Получить все акты об изъятии по списку id.
	*
	* @param ids список id
	* @return список актов об изъятии
	*/
   public List<BsoReportBeanData> getData(List<Long> ids) {
	  return bsoReportMapper.getByIds(ids);
   }
}
