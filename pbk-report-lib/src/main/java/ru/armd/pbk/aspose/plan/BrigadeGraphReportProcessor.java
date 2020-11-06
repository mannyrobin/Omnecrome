package ru.armd.pbk.aspose.plan;

import com.aspose.cells.AutoFitterOptions;
import com.aspose.cells.BackgroundType;
import com.aspose.cells.BorderType;
import com.aspose.cells.CellBorderType;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.ProtectionType;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Worksheet;
import ru.armd.pbk.aspose.cells.BaseCellsReportProcessor;
import ru.armd.pbk.aspose.core.IReportNameData;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.exceptions.PBKReportException;
import ru.armd.pbk.utils.date.DateUtils;
import ru.armd.pbk.views.plan.BrigadeGraphPlanVenuesView;
import ru.armd.pbk.views.plan.BrigadeGraphVenuesView;

import java.io.File;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static ru.armd.pbk.utils.AsposeCellsUtils.mergeReportsToFile;

/**
 * Генератор формы графика бригад с заполненными полями.
 */
public class BrigadeGraphReportProcessor
	extends BaseCellsReportProcessor {

   private static final int TASK_REPORT_WIDTH = 11; // ширина печатной формы XLS по заданию в столбцах
   private static final String SEPARATOR = "/";

   private BrigadeGraphReportBeanData data;
   private Worksheet worksheet;
   private Cells cells;
   private Color orangeColor;
   private int maxCol = 0;

   @Override
   protected void processReport() {
	  if (dataBean == null) {
		 return;
	  }
	  data = (BrigadeGraphReportBeanData) dataBean;
	  worksheet = wb.getWorksheets().get(0);
	  cells = worksheet.getCells();

	  prepareData();
	  processTable();
	  setAutoFitOptions();
	  //processPrintArea();
   }

   private void prepareData() {
   }

   private void processTable() {
	  List<BrigadeGraphVenuesView> venues = data.getVenues();
	  List<BrigadeGraphPlanVenuesView> pvs = data.getPlanVenues();

	  DateFormatSymbols symbols = new DateFormatSymbols(new Locale("ru"));
	  Calendar c = Calendar.getInstance();
	  c.setTime(data.getFromDate());
	  int dateStartCol = 5;
	  int col = dateStartCol;
	  Style cs = cells.get(1, col).getStyle();
	  cs.setCustom("D");
	  do {
		 cells.setColumnWidthInch(col, 0.35);
		 processValueCell(1, col, c.getTime(), CellStyle.TEXT);
		 cells.get(1, col).setStyle(cs);
		 cells.get(2, col).setStyle(cs);
		 processValueCell(2, col++, symbols.getShortWeekdays()[c.get(Calendar.DAY_OF_WEEK)].toLowerCase(),
			 CellStyle.TEXT);
		 c.add(Calendar.DAY_OF_YEAR, 1);
	  } while (!c.getTime().after(data.getToDate()));

	  orangeColor = cells.get(2, 4).getStyle().getFont().getColor();
	  Map<String, Integer> map = new HashMap<String, Integer>();
	  Integer row = 3;
	  int startDptRow = 3;
	  String prevName = null;
	  Style csShiftTime = cells.get(2, 3).getStyle();
	  for (BrigadeGraphVenuesView venue : venues) {
		 String cName = venue.getCountyName();
		 if (cName != null && cName.endsWith(", ")) {
			cName = cName.substring(0, cName.length() - 2);
		 }
		 String name = venue.getDeptName() + "\n" + cName;
		 if (prevName == null || !prevName.equalsIgnoreCase(name)) {
			if (prevName != null) {
			   cells.merge(startDptRow, 0, row - startDptRow, 1);
			   startDptRow = row;
			}
			prevName = name;
		 }
		 String key = venue.getDeptId() + SEPARATOR + venue.getVenueId() + SEPARATOR + venue.getShiftId();
		 map.put(key, row);
		 processValueCell(row, 3, key, CellStyle.TEXT);
		 processValueCell(row, 0, name, CellStyle.TEXT);
		 processValueCell(row + 1, 0, "", CellStyle.TEXT);

		 processValueCell(row, 1, venue.getVenueName(), CellStyle.TEXT);
		 processValueCell(row + 1, 1, "", CellStyle.TEXT);
		 cells.merge(row, 1, 2, 1);
		 processValueCell(row, 2, venue.getShiftHours(), CellStyle.TEXT);
		 processValueCell(row + 1, 2, "", CellStyle.TEXT);
		 cells.get(row, 2).setStyle(csShiftTime);
		 cells.merge(row, 2, 2, 1);
		 processValueCell(row, 4, "МГТ", CellStyle.TEXT, true, null);
		 processValueCell(row + 1, 4, "ГКУ", CellStyle.TEXT);
		 for (int i = dateStartCol; i < col; i++) {
			processValueCell(row, i, 0, CellStyle.INT, true, null);
			processValueCell(row + 1, i, 0, CellStyle.INT);
		 }
		 row += 2;
	  }
	  cells.merge(startDptRow, 0, row - startDptRow, 1);

	  int lastEditableRow = -1;
	  Color color = null;
	  for (BrigadeGraphPlanVenuesView pv : pvs) {
		 col = dateStartCol + DateUtils.getDaysDifference(data.getFromDate(), pv.getPlanDate());
		 if (col > maxCol) {
			maxCol = col;
		 }
		 row = map.get(pv.getDeptId() + SEPARATOR + pv.getCityVenueId() + SEPARATOR + pv.getShiftId());
		 if (row == null) {
			continue;
		 }
		 color = getBrigadeColor(pv);
		 processValueCell(row, col, pv.getMgtCount(), CellStyle.INT, true, color);
		 processValueCell(row + 1, col, pv.getGkuopCount(), CellStyle.INT, false, color);
		 if (pv.getDeptId() == -1 && (lastEditableRow == -1 || row < lastEditableRow)) {
			lastEditableRow = row;
		 }
	  }
	  maxCol++;
	  for (int i = dateStartCol; i < maxCol; i++) {
		 processValueCell(0, i, "", CellStyle.INT, false, null);
	  }
	  cells.merge(0, 0, 1, maxCol);
	  if (lastEditableRow != -1) {
		 worksheet.getAllowEditRanges().add("editable", 3, 5, lastEditableRow - 1, maxCol - 1);
		 worksheet.protect(ProtectionType.ALL);
	  }
   }

   protected void processValueCell(Integer row, Integer col, Object value, CellStyle cellStyle) {
	  processValueCell(row, col, value, cellStyle, false, null);
   }

   protected void processValueCell(Integer row, Integer col, Object value, CellStyle cellStyle, boolean isOrange, Color color) {
	  processValueCell(cells, row, col, value, cellStyle);
	  Style style = cells.getCellStyle(row, col);
	  if (isOrange) {
		 style.getFont().setColor(orangeColor);
	  }
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
	  return new IReportNameData() {
		 @Override
		 public String buildName() {
			return "График бригад "; //TODO
		 }
	  };
   }

   private Color getBrigadeColor(BrigadeGraphPlanVenuesView brigade) {
	  if (brigade != null) {
		 if (brigade.getIsAgree()) {
			if (brigade.getIsNotFull()) {
			   return Color.fromArgb(255, 204, 204);
			}
			if (brigade.getIsHaveFreeControllers()) {
			   return Color.fromArgb(255, 153, 0);
			}
			return Color.fromArgb(92, 184, 92);
		 } else {
			if (brigade.getIsNotFull()) {
			   return Color.fromArgb(102, 204, 204);
			}
			if (brigade.getIsHaveFreeControllers()) {
			   return Color.fromArgb(255, 215, 40);
			}
		 }
	  }
	  return null;
   }
}
