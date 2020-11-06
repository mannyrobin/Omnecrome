package ru.armd.pbk.services.reports;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import ru.armd.pbk.aspose.core.AsposeReports;
import ru.armd.pbk.aspose.core.IReportProcessor;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ProcessorProvider;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportExportDTO;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.core.ReportTypeExport;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.exceptions.PBKException;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Генератор отчётов и печатных форм.
 */
@Service
public class AsposeReportsService
	extends BaseComponent {

   private static final Logger LOG = Logger.getLogger(AsposeReportsService.class);

   @Autowired
   private AsposeReports reportsRegistry;

   @Autowired
   private ProcessorProvider processorProvider;

   @Override
   public Logger getLogger() {
	  return LOG;
   }

   protected Map<ReportFormat, File> generateReport(IReportType reportType, ReportData reportData)
	   throws PBKException {
	  return generateReport(reportType, reportData, null);
   }

   protected Map<ReportFormat, File> generateReport(IReportType reportType, ReportData reportData, Boolean twoOnOnePage)
	   throws PBKException {
	  if (reportData == null) {
		 throw new PBKException("Не задан необходимый параметр");
	  }
	  if (twoOnOnePage != null && twoOnOnePage) {
		 return combineToOneReport(reportType, reportData);
	  } else {
		 return reportsRegistry.generateReport(reportType, reportData);
	  }
   }

   protected Map<ReportFormat, File> combineToOneReport(IReportType reportType, ReportData reportData) {
	  Map<ReportFormat, File> result = new HashMap<ReportFormat, File>();
	  try {
		 ReportFormat reportFormat = reportType.getReportFormat();
		 reportType.setReportFormat(ReportFormat.XLSX);
		 File file = reportsRegistry.generateReport(reportType, reportData).get(ReportFormat.XLSX);
		 IReportProcessor processor = null;
		 processor = processorProvider.getPooledProcessor(reportType);
		 String resultReportName = processor.getResultReportNameData(Arrays.asList(reportData)).buildName();
		 result.put(reportFormat, processor.merge(Arrays.asList(file, file), resultReportName, reportFormat));
		 processorProvider.returnPooledProcessor(reportType, processor);
		 reportType.setReportFormat(reportFormat);
	  } catch (Exception e) {
		 throw new PBKException("Ошибка генерации отчета", e);
	  }
	  return result;
   }

   protected List<Map<ReportFormat, File>> generateReports(IReportType reportType, List<ReportData> reportDataList)
	   throws PBKException {
	  return generateReports(reportType, reportDataList, null);
   }

   protected List<Map<ReportFormat, File>> generateReports(IReportType reportType, List<ReportData> reportDataList, Boolean twoOnOnePage)
	   throws PBKException {
	  if (reportDataList == null) {
		 throw new PBKException("Не задан необходимый параметр");
	  }

	  List<Map<ReportFormat, File>> resultReportList = new ArrayList<>();
	  for (ReportData reportData : reportDataList) {
		 resultReportList.add(generateReport(reportType, reportData, twoOnOnePage));
	  }

	  return resultReportList;
   }

   protected Map<ReportFormat, File> generateMergedReport(IReportType type, List<ReportData> reportDataList)
	   throws PBKException {
	  IReportProcessor processor = null;
	  try {
		 ReportFormat reportFormat = type.getReportFormat();
		 type.setReportFormat(ReportFormat.XLSX);
		 processor = processorProvider.getPooledProcessor(type);
		 List<Map<ReportFormat, File>> reportListAll = generateReports(type, reportDataList, true);
		 Map<ReportFormat, List<File>> reportListGrouped = groupReportListByFormat(reportListAll);
		 Map<ReportFormat, File> resultReports = new HashMap<>();
		 for (Map.Entry<ReportFormat, List<File>> report : reportListGrouped.entrySet()) {
			List<File> reportFiles = report.getValue();
			String resultReportName = processor.getResultReportNameData(reportDataList).buildName();
			File resultReport = processor.merge(reportFiles, resultReportName, reportFormat);
			resultReports.put(reportFormat, resultReport);
		 }
		 processorProvider.returnPooledProcessor(type, processor);
		 type.setReportFormat(reportFormat);
		 return resultReports;
	  } catch (Exception e) {
		 throw new PBKException("Ошибка генерации отчета", e);
	  }
   }

   protected String getReportUrl(String identifier) {
	  return null;
   }

   protected List<ReportTypeExport.Format> getTypes(String identifier, boolean isForAll) {
	  return null;
   }

   protected boolean allowPrint(String identifier) {
	  return false;
   }

   protected String getTitle(String identifier, String[] objectIds) {
	  return null;
   }

   public String getReportExport(HttpServletRequest request, String requestUri, String report, String[] objectIds, ModelMap model)
	   throws PBKException {
	  String reportUrl = getReportUrl(report);
	  if (reportUrl == null) {
		 throw new PBKException("Указан неверный отчет для выгрузки");
	  }
	  ReportExportDTO dto = new ReportExportDTO();
	  dto.setUrl(requestUri + request.getContextPath() + "/reports");
	  dto.setAllowPrint(allowPrint(report));
	  dto.setReport(reportUrl);
	  dto.setTitle(getTitle(report, objectIds));
	  dto.setObjectIds(objectIds);
	  dto.setCookies(request.getCookies());

	  model.addAttribute("dto", dto);
	  model.addAttribute("reportIds", new ArrayList<String>(Arrays.asList(objectIds)));
	  model.addAttribute("reportTypes", getTypes(report, false));
	  model.addAttribute("reportTypesAll", getTypes(report, true));

	  return "/reports-export";
   }

   private Map<ReportFormat, List<File>> groupReportListByFormat(List<Map<ReportFormat, File>> reportList) {
	  Map<ReportFormat, List<File>> reportListGrouped = new HashMap<>();
	  for (Map<ReportFormat, File> report : reportList) {
		 for (Map.Entry<ReportFormat, File> entry : report.entrySet()) {
			ReportFormat format = entry.getKey();
			if (reportListGrouped.get(format) == null) {
			   reportListGrouped.put(format, new ArrayList<File>());
			}
			reportListGrouped.get(format).add(entry.getValue());
		 }
	  }
	  return reportListGrouped;
   }

}
