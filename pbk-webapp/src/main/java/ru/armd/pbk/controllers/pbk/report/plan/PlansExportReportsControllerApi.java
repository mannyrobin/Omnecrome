package ru.armd.pbk.controllers.pbk.report.plan;

import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.armd.pbk.aspose.core.AsposeReports;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.plan.BrigadeGraphReportType;
import ru.armd.pbk.aspose.plan.TimesheetReportType;
import ru.armd.pbk.core.controllers.BaseControllerApi;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.venue.Venue;
import ru.armd.pbk.domain.plans.brigades.Brigade;
import ru.armd.pbk.dto.tasks.TaskFileDTO;
import ru.armd.pbk.exceptions.PBKAsposeReportException;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.venue.VenueMapper;
import ru.armd.pbk.mappers.plans.brigades.PlanBrigadeMapper;
import ru.armd.pbk.services.aspose.plan.PlanReportService;
import ru.armd.pbk.services.aspose.plan.TimesheetReportService;
import ru.armd.pbk.utils.AttachReportFileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер для генерации отчетов заданий.
 */
@Controller
@RequestMapping(PlansExportReportsControllerApi.RM_CONTROLLER_PATH)
public class PlansExportReportsControllerApi
	extends BaseControllerApi {

   public static final String RM_PATH = "/reports/aspose/pbk/plans";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   public static final String RM_REPORT = "";

   @Autowired
   private PlanReportService reportService;

   @Autowired
   private PlanBrigadeMapper planBrigadeMapper;

   @Autowired
   private VenueMapper venueMapper;

   @Autowired
   private TimesheetReportService timesheetReportService;

   /**
	* Сгенерировать файл отчёта указанного формата для указанных заданий и
	* поместить его в http-response для передачи клиенту.
	*
	* @param format   формат отчёта
	* @param deptId   id подразделения
	* @param fromDate дата начала
	* @param toDate   дата конца
	* @param request  http request
	* @param response http response
	*/
   @ResponseBody
   @RequestMapping(value = "/brigades", method = RequestMethod.GET)
   public void exportReport(@RequestParam(value = "format") String format,
							@RequestParam(value = "deptId", required = false) Long deptId,
							@RequestParam("fromDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date fromDate,
							@RequestParam("toDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date toDate,
							HttpServletRequest request, HttpServletResponse response) {
	  IReportType type = new BrigadeGraphReportType();
	  ReportFormat rformat = AsposeReports.checkReportFormatOwnOrPdf(type, format);
	  type.setReportFormat(rformat);
	  Map<ReportFormat, File> result = null;
	  result = reportService.generate(type, deptId, fromDate, toDate);
	  if (result == null) {
		 throw new PBKAsposeReportException("Ошибка генерации отчета");
	  }
	  Map<String, File> files = new HashMap<>();
	  files.put(result.get(rformat).getName(), result.get(rformat));
	  AttachReportFileUtils.composeReportFiles(files, response, type, false);
   }

   /**
	* Выгрузка табеля.
	*
	* @param format   формат.
	* @param deptId   подразделение.
	* @param fromDate дата с.
	* @param toDate   дата по.
	* @param request  ответ.
	* @param response запрос.
	*/
   @ResponseBody
   @RequestMapping(value = "/timesheet", method = RequestMethod.GET)
   public void exportTimesheetReport(@RequestParam(value = "format") String format,
									 @RequestParam(value = "deptId", required = false) Long deptId,
									 @RequestParam("fromDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date fromDate,
									 @RequestParam("toDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date toDate,
									 HttpServletRequest request, HttpServletResponse response) {
	  IReportType type = new TimesheetReportType();
	  Map<ReportFormat, File> result = null;
	  ReportFormat rformat = AsposeReports.checkReportFormatOwnOrPdf(type, format);
	  type.setReportFormat(rformat);
	  result = timesheetReportService.generate(type, deptId, fromDate, toDate);
	  if (result == null) {
		 throw new PBKAsposeReportException("Ошибка генерации отчета");
	  }
	  Map<String, File> files = new HashMap<>();
	  files.put(result.get(rformat).getName(), result.get(rformat));
	  AttachReportFileUtils.composeReportFiles(files, response, type, false);
   }

   /**
	* Импорт графика бригад.
	*
	* @param file - добавляемый файл
	* @return
	*/
   @SuppressWarnings("rawtypes")
   @RequestMapping(value = "/brigades/import", method = RequestMethod.POST)
   @ResponseStatus(value = HttpStatus.OK)
   // TODO @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).ADD_DTO)")
   public ResponseEntity importBrigadeGraph(@Valid @ModelAttribute("file") TaskFileDTO file)
	   throws Exception {

	  Workbook wb = new Workbook(file.getFile().getInputStream());
	  Worksheet ws = wb.getWorksheets().get(0);
	  Cells cells = ws.getCells();
	  String deptName = "";
	  for (int r = 3; r <= cells.getMaxRow(); r += 2) {
		 String[] keys = null;
		 String key = cells.get(r, 3).getStringValue();
		 if (key != null) {
			keys = key.split("/");
		 }
		 if (keys == null || keys.length != 3) {
			String dName = cells.get(r, 0).getStringValue();
			deptName = dName == null || dName.isEmpty() ? deptName : dName.substring(0, Math.max(0, dName.indexOf("\n")));
			String venueName = cells.get(r, 1).getStringValue();
			String shiftHours = cells.get(r, 2).getStringValue();
			List<SelectItem> ids = planBrigadeMapper.findBrigadeImportByNames(deptName, venueName, shiftHours);
			if (ids != null && ids.size() == 3) {
			   keys = new String[] {String.valueOf(ids.get(0).getId()), String.valueOf(ids.get(1).getId()), String.valueOf(ids.get(2).getId())};
			} else {
			   throw new PBKException("В БД не найдены атрибуты строки " + r);
			}
		 }
		 int c = 4;
		 int dateRow = 1;
		 while (cells.get(dateRow, ++c).getValue() != null) {
			Brigade br = new Brigade();
			Long deptId = Long.parseLong(keys[0]);
			if (-1 == deptId.longValue()) {
			   continue;
			}
			Date workDate = cells.get(dateRow, c).getDateTimeValue().toDate();
			Long cvId = Long.parseLong(keys[1]);
			List<Venue> vens = venueMapper.getCityVenueByDept(deptId, workDate);
			for (Venue ven : vens) {
			   if (ven.getId().equals(cvId)) {
				  br.setDeptId(deptId);
				  br.setPlanDate(workDate);
				  br.setCityVenueId(cvId);
				  br.setShiftId(Long.parseLong(keys[2]));
				  Brigade br2 = planBrigadeMapper.getBrigadeByParams(br.getDeptId(), br.getCityVenueId(), br.getShiftId(), br.getPlanDate());
				  if (br2 != null) {
					 br2.setMgtCount(cells.get(r, c).getIntValue());
					 br2.setGkuopCount(cells.get(r + 1, c).getIntValue());
					 initUpdaterInfo(br2);
					 planBrigadeMapper.update(br2);
				  } else {
					 br.setMgtCount(cells.get(r, c).getIntValue());
					 br.setGkuopCount(cells.get(r + 1, c).getIntValue());
					 initUpdaterInfo(br);
					 initCreaterInfo(br);
					 planBrigadeMapper.insert(br);
				  }
				  break;
			   }
			}
		 }
	  }
	  // TODO recalc
	  return new ResponseEntity(HttpStatus.OK);
   }
}