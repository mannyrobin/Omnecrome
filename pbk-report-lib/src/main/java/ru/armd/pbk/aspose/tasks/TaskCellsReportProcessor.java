package ru.armd.pbk.aspose.tasks;

import com.aspose.cells.AutoFitterOptions;
import com.aspose.cells.Cells;
import com.aspose.cells.Worksheet;
import org.springframework.util.StringUtils;
import ru.armd.pbk.aspose.cells.BaseCellsReportProcessor;
import ru.armd.pbk.aspose.core.IReportNameData;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.tasks.TaskReportValueCell;
import ru.armd.pbk.enums.tasks.TaskValueCell;
import ru.armd.pbk.exceptions.PBKReportException;
import ru.armd.pbk.views.tasks.TaskReportReportView;
import ru.armd.pbk.views.tasks.TaskReportView;
import ru.armd.pbk.views.tasks.TaskRoutesReportView;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ru.armd.pbk.utils.AsposeCellsUtils.mergeReportsToFile;
import static ru.armd.pbk.utils.NameUtils.toSurnameAndInitials;
//import static ru.armd.pbk.utils.date.DateUtils.getHour;
//import static ru.armd.pbk.utils.date.DateUtils.getMinute;
import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateFormat;
import static ru.armd.pbk.utils.date.DateUtils.makeGeneralTimeFormat;

/**
 * Генератор печатной формы задания с заполненными полями задания.
 */
public class TaskCellsReportProcessor
	extends BaseCellsReportProcessor {

   private static final int TASK_REPORT_WIDTH = 14; //ширина печатной формы XLS по заданию в столбцах
   private static final int MAX_COUNT_TASK_IN_FILE_NAME = 5;

   private TaskReportBeanData data;
   private Worksheet worksheet;
   private Cells cells;

   @Override
   protected void processReport() {
	  if (dataBean == null) {
		 return;
	  }
	  data = (TaskReportBeanData) dataBean;
	  worksheet = wb.getWorksheets().get(0);
	  cells = worksheet.getCells();

	  prepareData();
	  processTable();
	  setAutoFitOptions();
   }

   private void prepareData() {
   }

   private void processTable() {
	  TaskReportView task = data.getTask();
	  TaskReportReportView taskReport = data.getTaskReport();
	  processTaskCells(task);
	  processTaskReportCells(taskReport);
	  processTaskRoutesReportCells(task, data.getTaskRoutes());
   }

   private void processTaskCells(TaskReportView task) {
	  processValueCell(cells, TaskValueCell.BSO_NUMBER.getPos(), task.getBsoNumber(), CellStyle.TEXT);
	  processValueCell(cells, TaskValueCell.TASK_DATE.getPos(), makeGeneralDateFormat().format(task.getTaskDate()) + " г.", CellStyle.TEXT);
	  String employeeSurname = task.getEmployeeSurname();
	  String employeeName = task.getEmployeeName();
	  String employeePatronumic = task.getEmployeePatronumic();
	  processValueCell(cells, TaskValueCell.EMPLOYEE_NAME.getPos(), toSurnameAndInitials(employeeSurname, employeeName, employeePatronumic), CellStyle.TEXT);
	  processValueCell(cells, TaskValueCell.PERSONAL_NUMBER.getPos(), task.getPersonalNumber(), CellStyle.TEXT);
	 // processValueCell(cells, TaskValueCell.LICENSE_NUMBER.getPos(), task.getLicenseDetails(), CellStyle.TEXT);
	 // if (task.getLicenseEndDate() != null) {
	//	 processValueCell(cells, TaskValueCell.LICENSE_END_DATE.getPos(), makeGeneralDateFormat().format(task.getLicenseEndDate()), CellStyle.TEXT);
	 // }
	 // processValueCell(cells, TaskValueCell.PUSK_NUMBER.getPos(), task.getPuskNumber(), CellStyle.TEXT);
	 // processValueCell(cells, TaskValueCell.WORK_START_HOUR.getPos(), getHour(task.getWorkStartTime()), CellStyle.INT);
	 // processValueCell(cells, TaskValueCell.WORK_START_MINUTE.getPos(), getMinute(task.getWorkStartTime()), CellStyle.INT);
	  processValueCell(cells, TaskValueCell.WORK_START_TIME.getPos(), makeGeneralTimeFormat(task.getWorkStartTime()), CellStyle.INT);
	  processValueCell(cells, TaskValueCell.WORK_END_TIME.getPos(), makeGeneralTimeFormat(task.getWorkEndTime()), CellStyle.INT);
	 // processValueCell(cells, TaskValueCell.WORK_END_HOUR.getPos(), getHour(task.getWorkEndTime()), CellStyle.INT);
	 // processValueCell(cells, TaskValueCell.WORK_END_MINUTE.getPos(), getMinute(task.getWorkEndTime()), CellStyle.INT);
	  processValueCell(cells, TaskValueCell.BREAK_START_TIME.getPos(), makeGeneralTimeFormat(task.getBreakStartTime()), CellStyle.TEXT);
	  processValueCell(cells, TaskValueCell.BREAK_END_TIME.getPos(), makeGeneralTimeFormat(task.getBreakEndTime()), CellStyle.TEXT);
	  processValueCell(cells, TaskValueCell.EMPLOYEE_SIGN.getPos(),
		  task.getEmployeeSignSurname() == null ? ""
			  : toSurnameAndInitials(task.getEmployeeSignSurname(), task.getEmployeeSignName(),
			  task.getEmployeeSignPatronumic()),
		  CellStyle.TEXT);
	  processValueCell(cells, TaskValueCell.SPECIAL_MARK.getPos(), task.getSpecialMark(), CellStyle.TEXT);
	//  processValueCell(cells, TaskValueCell.SPECIAL_TASK.getPos(), task.getSpecialTask(), CellStyle.TEXT);
   }

   private void processTaskReportCells(TaskReportReportView taskReport) {
	  String employeeSurname = taskReport.getEmployeeSurname();
	  String employeeName = taskReport.getEmployeeName();
	  String employeePatronumic = taskReport.getEmployeePatronumic();
	  String employeeSurnameAndInitials = toSurnameAndInitials(employeeSurname, employeeName, employeePatronumic);
	  processValueCell(cells, TaskReportValueCell.EMPLOYEE_NAME.getPos(), employeeSurnameAndInitials, CellStyle.TEXT);
	  processValueCell(cells, TaskReportValueCell.PERSONAL_NUMBER.getPos(), taskReport.getPersonalNumber(), CellStyle.TEXT);
   }

   private void processTaskRoutesReportCells(TaskReportView task, TaskRoutesReportView taskRoutes) {
	  String routesStr = "";
	  if (taskRoutes.getRouteNumbers() != null && !taskRoutes.getRouteNumbers().isEmpty()) {
		 routesStr = StringUtils.collectionToDelimitedString(taskRoutes.getRouteNumbers(), ", ");
	  } else {
		 routesStr = task.getCountyName();
	  }
	  processValueCell(cells, TaskValueCell.ROUTES.getPos(), routesStr, CellStyle.TEXT);
   }

   private void setAutoFitOptions() {
	  AutoFitterOptions options = new AutoFitterOptions();
	  options.setAutoFitMergedCells(true);
	  try {
		 worksheet.autoFitRows(options);
	  } catch (Exception e) {
		 throw new PBKReportException("Ошибка при выполнении autoFit", e);
	  }
   }

   @Override
   public File merge(List<File> reports, String mergedReportName, ReportFormat reportFormat) {
	  return mergeReportsToFile(reports, mergedReportName, TASK_REPORT_WIDTH);
   }

   @Override
   public IReportNameData getResultReportNameData(List<ReportData> reportDataList) {
	  Date reportDateTime = new Date();
	  if (reportDataList.size() == 1) {
		 String bsoNumber = ((TaskReportBeanData) reportDataList.get(0).getDataBean()).getTask().getBsoNumber();
		 return new TaskReportSingleNameData(reportDateTime, bsoNumber);
	  } else {
		 List<String> bsoNumbers = new ArrayList<>();
		 for (int i = 0; i < reportDataList.size() && i < MAX_COUNT_TASK_IN_FILE_NAME; i++) {
			bsoNumbers.add(((TaskReportBeanData) reportDataList.get(i).getDataBean()).getTask().getBsoNumber());
		 }
		 return new TaskReportMultipleNameData(reportDateTime, bsoNumbers);
	  }
   }
}
