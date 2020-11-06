package ru.armd.pbk.controllers.pbk.report.nsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.aspose.core.AsposeReports;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.nsi.bsos.BsoCellsReportType;
import ru.armd.pbk.aspose.nsi.bsos.BsoReportBeanData;
import ru.armd.pbk.authtoken.AuthenticationManager;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.dto.nsi.bso.BsoDTO;
import ru.armd.pbk.exceptions.PBKAsposeReportException;
import ru.armd.pbk.services.aspose.nsi.bsos.BsosReportService;
import ru.armd.pbk.services.nsi.bso.BsoService;
import ru.armd.pbk.utils.AttachReportFileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * Контроллер выгрузки актов об изъятии.
 */
@Controller
@RequestMapping(BsosExportReportsControllerApi.RM_CONTROLLER_PATH)
public class BsosExportReportsControllerApi
	extends BaseDomainControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/reports/aspose/nsi/bsos";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   public static final String RM_REPORT = "/export";

   @Autowired
   private BsosReportService reportService;

   @Autowired
   private BsoService bsoService;

   /**
	* Выгрузка актов об изъятии.
	*
	* @param format   формат
	* @param ids      список бсо
	* @param request  запрос
	* @param response ответ
	*/
   @ResponseBody
   @RequestMapping(value = RM_REPORT, method = RequestMethod.GET)
   public void exportReport(@RequestParam(value = "format") String format, @RequestParam("ids") List<Long> ids, HttpServletRequest request,
							HttpServletResponse response) {
	  if (ids != null && !ids.isEmpty()) {
		 IReportType type = new BsoCellsReportType();
		 List<BsoReportBeanData> data = reportService.getData(ids);
		 Set<String> roles = Objects.requireNonNull(AuthenticationManager.getUserInfo()).getRoleNames();
		  if (format.equals("xlsx")) {
			  if (!roles.contains("Начальник ИАО") && !roles.contains("Главный администратор")) {
				  throw new PBKAsposeReportException("Попытка распечатать БСО в запрещенном для вашей роли формате, обратитесь к начальнику ИАО");
			  }
		  }
		 for (Long bsoId : ids) {
			BsoDTO dto = bsoService.getDTOById(bsoId);
			if (dto.getIsPrinted()) {
				if (!roles.contains("Начальник ИАО") && !roles.contains("Главный администратор")) {
					throw new PBKAsposeReportException("Попытка повторно распечатать БСО, обратитесь к начальнику ИАО");
				}
			}
		 }
		 if (format.equals("zip")) {
			ReportFormat rformat = AsposeReports.checkReportFormatOwnOrPdf(type, "xlsx");
			type.setReportFormat(rformat);
			List<Map<ReportFormat, File>> result = reportService.generateMultiple(type, data);
			if (result == null) {
			   throw new PBKAsposeReportException("Ошибка генерации отчета");
			}
			Map<String, File> files = new HashMap<>();
			for (Map<ReportFormat, File> report : result) {
			   files.put(report.get(rformat).getName(), report.get(rformat));
			}
			AttachReportFileUtils.composeZipReportFile(files, response, type);
		 } else {
			ReportFormat rformat = AsposeReports.checkReportFormatOwnOrPdf(type, format);
			type.setReportFormat(rformat);
			Map<ReportFormat, File> result = null;
			if (data.size() == 1) {
			   result = reportService.generate(type, data.get(0));
			} else if (data.size() > 1) {
			   result = reportService.generateMerged(type, data);
			} else {
			   throw new IllegalArgumentException("Не были переданы id актов об изъятии");
			}
			if (result == null) {
			   throw new PBKAsposeReportException("Ошибка генерации отчета");
			}
			Map<String, File> files = new HashMap<>();
			files.put(result.get(rformat).getName(), result.get(rformat));
			AttachReportFileUtils.composeReportFiles(files, response, type, true);
			bsoService.printBsos(ids);
		 }
	  }
   }

}
