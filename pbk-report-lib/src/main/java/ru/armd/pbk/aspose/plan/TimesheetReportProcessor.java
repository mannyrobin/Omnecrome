package ru.armd.pbk.aspose.plan;

import com.aspose.cells.BackgroundType;
import com.aspose.cells.BorderType;
import com.aspose.cells.CellBorderType;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Worksheet;
import ru.armd.pbk.aspose.cells.BaseCellsReportProcessor;
import ru.armd.pbk.aspose.core.IReportNameData;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.utils.NameUtils;
import ru.armd.pbk.utils.date.DateUtils;
import ru.armd.pbk.views.plan.TimesheetReportView;

import java.io.File;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Генератор формы табеля с заполненными полями.
 */
public class TimesheetReportProcessor
	extends BaseCellsReportProcessor {

   private static final int DATE_START_COLUMN = 5;
   private static final int START_ROW = 1;
   private static final int START_COLUMN = 0;

   private Worksheet worksheet;
   private Cells cells;
   private TimesheetReportBeanData data;

   @Override
   protected void processReport() {
	  worksheet = wb.getWorksheets().get(0);
	  cells = worksheet.getCells();

	  processHeaders();
	  processTable();
   }

   protected void processTable() {
	  if (data.getViews() == null || data.getViews().isEmpty()) {
		 return;
	  }
	  int col = DATE_START_COLUMN;
	  int row = START_ROW;
	  TimesheetReportView first = null;
	  for (TimesheetReportView curr : data.getViews()) {
		 if (first == null || !first.getPersonalNumber().equals(curr.getPersonalNumber())) {
			row += 2;
			first = processEmployeeCells(row, curr);
			col = DATE_START_COLUMN;
		 }
		 // Если выходной, отпуск или больничный
		 if (curr.getShiftId() == 1 || curr.getShiftId() == 4
			 || curr.getShiftId() == 5) {
			cells.merge(row, col, 2, 1);
			processValueCell(row, col, NameUtils.shiftDataToName(curr.getShiftId(), false), CellStyle.TEXT, getShiftColor(curr.getShiftId()));
			cells.get(row, col).getMergedRange().setOutlineBorder(BorderType.TOP_BORDER, CellBorderType.THIN, Color.getBlack());
			cells.get(row, col).getMergedRange().setOutlineBorder(BorderType.BOTTOM_BORDER, CellBorderType.THIN, Color.getBlack());
			cells.get(row, col).getMergedRange().setOutlineBorder(BorderType.LEFT_BORDER, CellBorderType.THIN, Color.getBlack());
			cells.get(row, col++).getMergedRange().setOutlineBorder(BorderType.RIGHT_BORDER, CellBorderType.THIN, Color.getBlack());
		 } else {
			processValueCell(row, col, curr.getFactHours(), CellStyle.TEXT, null);
			processValueCell(row + 1, col++, curr.getPlanHours(), CellStyle.TEXT, null);
		 }
	  }
	  row += 2;
	  cells.deleteRow(row);
	  cells.deleteRow(row);
   }

   private Color getShiftColor(Long shiftId) {
	  if (shiftId != null) {
		 switch (shiftId.intValue()) {
			case 1:
			   return Color.fromArgb(192, 192, 192);
			case 4:
			case 5:
			   return Color.fromArgb(160, 160, 164);
			default:
			   return Color.fromArgb(255, 255, 255);
		 }
	  }
	  return null;
   }

   protected TimesheetReportView processEmployeeCells(int row, TimesheetReportView view) {
	  int col = START_COLUMN;
	  cells.copyRows(cells, row, row + 2, 2);
	  processValueCell(row, col++, NameUtils.toSurnameAndInitials(view.getSurname(), view.getName(), view.getPatronumic()), CellStyle.TEXT, null);
	  processValueCell(row, col, view.getPersonalNumber(), CellStyle.TEXT, null);
	  processValueCell(row, col + 2, view.getSumFactHours(), CellStyle.TEXT, null);
	  processValueCell(row + 1, col + 2, view.getSumPlanHours(), CellStyle.TEXT, null);
	  processValueCell(row, col + 3, view.getSumFactDays(), CellStyle.TEXT, null);
	  processValueCell(row + 1, col + 3, view.getSumPlanDays(), CellStyle.TEXT, null);	  
	  return view;
   }

   protected void processHeaders() {
	  DateFormatSymbols symbols = new DateFormatSymbols(new Locale("ru"));
	  Calendar c = Calendar.getInstance();
	  c.setTime(data.getDateFrom());
	  int col = DATE_START_COLUMN;
	  Style cs = cells.get(1, col).getStyle();
	  cs.setCustom("D");
	  do {
		 cells.setColumnWidthInch(col, 0.35);
		 processValueCell(1, col, c.getTime(), CellStyle.TEXT, null);
		 processValueCell(2, col, symbols.getShortWeekdays()[c.get(Calendar.DAY_OF_WEEK)].toLowerCase(), CellStyle.TEXT, null);
		 cells.get(1, col).setStyle(cs);
		 cells.get(2, col++).setStyle(cs);
		 c.add(Calendar.DAY_OF_YEAR, 1);
	  } while (!c.getTime().after(data.getDateTo()));
   }

   protected void processValueCell(Integer row, Integer col, Object value, CellStyle cellStyle, Color color) {
	  processValueCell(cells, row, col, value, cellStyle);
	  Style style = cells.getCellStyle(row, col);
	  style.setVerticalAlignment(TextAlignmentType.CENTER);
	  style.setHorizontalAlignment(TextAlignmentType.CENTER);
	  style.setBorder(BorderType.TOP_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.BOTTOM_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.LEFT_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.RIGHT_BORDER, CellBorderType.THIN, Color.getBlack());
	  if (color != null) {
		 style.setPattern(BackgroundType.SOLID);
		 style.setForegroundColor(color);
	  }
	  cells.get(row, col).setStyle(style);
   }

   @Override
   public File merge(List<File> reports, String mergedReportName,
					 ReportFormat fromat) {
	  return null;
   }

   @Override
   public IReportNameData getResultReportNameData(List<ReportData> reportData) {
	  return new IReportNameData() {
		 @Override
		 public String buildName() {
			if (dataBean == null) {
			   return null;
			}
			data = (TimesheetReportBeanData) dataBean;
			return String.format("Табель %s с %s по %s", data.getDepartmentName()
				, DateUtils.makeGeneralDateFormat().format(data.getDateFrom())
				, DateUtils.makeGeneralDateFormat().format(data.getDateTo()));
		 }
	  };
   }

}
