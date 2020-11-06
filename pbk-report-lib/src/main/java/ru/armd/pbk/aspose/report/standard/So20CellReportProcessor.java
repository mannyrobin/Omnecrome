package ru.armd.pbk.aspose.report.standard;

import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.CellBorderType;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.Font;
import com.aspose.cells.Row;
import com.aspose.cells.Style;
import com.aspose.cells.StyleFlag;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Worksheet;
import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So20CheckPlanValueCell;
import ru.armd.pbk.enums.report.standard.So20EndCheckValueCell;
import ru.armd.pbk.enums.report.standard.So20LegendValueCell;
import ru.armd.pbk.utils.date.DateUtils;
import ru.armd.pbk.views.report.So20MediumResultView;
import ru.armd.pbk.views.report.So20MergedCheckPlanView;
import ru.armd.pbk.views.report.So20MergedCheckResultView;
import ru.armd.pbk.views.report.SoView;

import java.text.SimpleDateFormat;

/**
 * Created by Yakov Volkov.
 */
public class So20CellReportProcessor
	extends SoBaseCellsReportProcessor {

   private SimpleDateFormat sdfDateTime = new SimpleDateFormat(DateUtils.DATETIME_FORMAT);
   private SimpleDateFormat sdfDate = new SimpleDateFormat(DateUtils.DATE_FORMAT);

   private StyleFlag allStyleFlag;

   private Cells legendWorksheetCells;
   private Cells planCheckWorksheetCells;

   @Override
   protected void processReport() {
	  headerHeight = 3;

	  allStyleFlag = new StyleFlag();
	  allStyleFlag.setAll(true);

	  Worksheet legendWorksheet = wb.getWorksheets().get(1);
	  legendWorksheetCells = legendWorksheet.getCells();

	  Worksheet planCheckWorksheet = wb.getWorksheets().get(2);
	  planCheckWorksheetCells = planCheckWorksheet.getCells();

	  super.processReport();
   }

   @Override
   protected int processRow(int row, SoView view) {
	  if (view instanceof So20MergedCheckResultView) {
		 return processCommonRow(row, (So20MergedCheckResultView) view);
	  } else if (view instanceof So20MediumResultView) {
		 fillLegend((So20MediumResultView) view);
		 return processTotalRow(row, (So20MediumResultView) view);
	  } else if (view instanceof So20MergedCheckPlanView) {
		 fillCheckPlan((So20MergedCheckPlanView) view);
	  }
	  return row;
   }

   private void fillCheckPlan(So20MergedCheckPlanView view) {
	  int row = planCheckWorksheetCells.getMaxDataRow() + 1;

	  int size = view.getCheckDates().size();
	  for (int i = 0; i < size; i++) {
		 int rowi = row + i;

		 String checkTime = sdfDate.format(view.getCheckDates().get(i));
		 processValueCell(planCheckWorksheetCells, rowi,
			 So20CheckPlanValueCell.ROUTE_NUMBER.getCol(), view.getRouteNumber(), CellStyle.TEXT);
		 processValueCell(planCheckWorksheetCells, rowi,
			 So20CheckPlanValueCell.CHECK_DATE.getCol(), checkTime, CellStyle.TEXT);
		 processValueCell(planCheckWorksheetCells, rowi,
			 So20CheckPlanValueCell.MOC.getCol(), view.getMocs().get(i), CellStyle.TEXT);
		 processValueCell(planCheckWorksheetCells, rowi,
			 So20CheckPlanValueCell.SHIFT.getCol(), view.getShifts().get(i), CellStyle.TEXT);

		 Row cellRow = planCheckWorksheetCells.checkRow(rowi);

		 for (int j = 0; j < So20CheckPlanValueCell.values().length; j++) {
			setCellStyle(cellRow.get(j));
		 }
	  }

	  planCheckWorksheetCells.merge(row, So20CheckPlanValueCell.ROUTE_NUMBER.getCol(), size, 1);
   }

   private void fillLegend(So20MediumResultView view) {
	  int row = legendWorksheetCells.getMaxDataRow() + 1;

	  processValueCell(legendWorksheetCells, row, So20LegendValueCell.ROUTE_NUMBER.getCol(),
		  view.getRouteNumber(), CellStyle.TEXT);
	  processValueCell(legendWorksheetCells, row, So20LegendValueCell.NUMBER.getCol(),
		  view.getCheckCount(), CellStyle.TEXT);
	  processValueCell(legendWorksheetCells, row, So20LegendValueCell.SCM.getCol(),
		  view.getScmSum(), CellStyle.TEXT);
	  processValueCell(legendWorksheetCells, row, So20LegendValueCell.SCMO.getCol(),
		  view.getScmoSum(), CellStyle.TEXT);
	  processValueCell(legendWorksheetCells, row, So20LegendValueCell.VESB.getCol(),
		  view.getVesbSum(), CellStyle.TEXT);
	  processValueCell(legendWorksheetCells, row, So20LegendValueCell.OTHER.getCol(),
		  view.getOtherSum(), CellStyle.TEXT);

	  Row cellRow = legendWorksheetCells.checkRow(row);

	  for (int i = 0; i < So20LegendValueCell.values().length; i++) {
		 setCellStyle(cellRow.get(i));
	  }
   }

   private void setCellStyle(Cell cell) {
	  Style style = cell.getStyle();
	  style.setVerticalAlignment(TextAlignmentType.CENTER);
	  style.setHorizontalAlignment(TextAlignmentType.CENTER);
	  style.setBorder(BorderType.TOP_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.BOTTOM_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.LEFT_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.RIGHT_BORDER, CellBorderType.THIN, Color.getBlack());

	  cell.setStyle(style, allStyleFlag);
   }

   private int processTotalRow(int row, So20MediumResultView view) {
	  processValueCell(cells, row, So20EndCheckValueCell.NUMBER.getCol(),
		  " Итого " + view.getRouteNumber(), CellStyle.TEXT);
	  processValueCell(cells, row, So20EndCheckValueCell.SHIFT.getCol(),
		  view.getShiftResult(), CellStyle.TEXT);
	  processValueCell(cells, row, So20EndCheckValueCell.SCM.getCol(),
		  view.getScmSum(), CellStyle.TEXT);
	  processValueCell(cells, row, So20EndCheckValueCell.SCMO.getCol(),
		  view.getScmoSum(), CellStyle.TEXT);
	  processValueCell(cells, row, So20EndCheckValueCell.VESB.getCol(),
		  view.getVesbSum(), CellStyle.TEXT);
	  processValueCell(cells, row, So20EndCheckValueCell.OTHER.getCol(),
		  view.getOtherSum(), CellStyle.TEXT);

	  cells.merge(row, So20EndCheckValueCell.NUMBER.getCol(), 1, 2);
	  cells.merge(row, So20EndCheckValueCell.SHIFT.getCol(), 1, 2);

	  addTotalRowStyle(row);

	  return row;
   }

   private void addCommonRowStyle(int row) {
	  Row cellRow = cells.checkRow(row);

	  for (int i = 0; i < 9; i++) {
		 setCellStyle(cellRow.get(i));
	  }
   }

   private void addTotalRowStyle(int row) {
	  Row cellRow = cells.checkRow(row);

	  Style style = cellRow.getStyle();
	  style.setVerticalAlignment(TextAlignmentType.CENTER);
	  style.setHorizontalAlignment(TextAlignmentType.CENTER);
	  style.setBorder(BorderType.TOP_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.BOTTOM_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.LEFT_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.RIGHT_BORDER, CellBorderType.THIN, Color.getBlack());

	  Font font = style.getFont();
	  font.setSize(font.getSize() + 1);
	  font.setBold(true);

	  for (int i = 0; i < 9; i++) {
		 cellRow.get(i).setStyle(style, allStyleFlag);
	  }
   }

   private int processCommonRow(int row, So20MergedCheckResultView view) {
	  int size = view.getNumbers().size();
	  for (int i = 0; i < size; i++) {
		 int rowi = row + i;

		 String checkTime = view.getCheckTimes() == null ? "" : sdfDateTime.format(view.getCheckTimes().get(i));
		 processValueCell(cells, rowi, So20EndCheckValueCell.NUMBER.getCol(), view.getNumbers().get(i), CellStyle.TEXT);
		 processValueCell(cells, rowi, So20EndCheckValueCell.CHECK_DATETIME.getCol(), checkTime, CellStyle.TEXT);
		 processValueCell(cells, rowi, So20EndCheckValueCell.MOC.getCol(), view.getMocs().get(i), CellStyle.TEXT);
		 processValueCell(cells, rowi, So20EndCheckValueCell.SHIFT.getCol(), view.getShifts().get(i), CellStyle.TEXT);
		 processValueCell(cells, rowi, So20EndCheckValueCell.FIO.getCol(), view.getFio(), CellStyle.TEXT);
		 processValueCell(cells, rowi, So20EndCheckValueCell.SCM.getCol(), view.getScm(), CellStyle.TEXT);
		 processValueCell(cells, rowi, So20EndCheckValueCell.SCMO.getCol(), view.getScmo(), CellStyle.TEXT);
		 processValueCell(cells, rowi, So20EndCheckValueCell.VESB.getCol(), view.getVesb(), CellStyle.TEXT);
		 processValueCell(cells, rowi, So20EndCheckValueCell.OTHER.getCol(), view.getOther(), CellStyle.TEXT);

		 addCommonRowStyle(rowi);
	  }

	  cells.merge(row, So20EndCheckValueCell.FIO.getCol(), size, 1);
	  cells.merge(row, So20EndCheckValueCell.SCM.getCol(), size, 1);
	  cells.merge(row, So20EndCheckValueCell.SCMO.getCol(), size, 1);
	  cells.merge(row, So20EndCheckValueCell.VESB.getCol(), size, 1);
	  cells.merge(row, So20EndCheckValueCell.MOC.getCol(), size, 1);
	  cells.merge(row, So20EndCheckValueCell.OTHER.getCol(), size, 1);


	  return row + size - 1;
   }
}
