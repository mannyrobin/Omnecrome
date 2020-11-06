package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So4ValueCell;
import ru.armd.pbk.views.report.So4View;
import ru.armd.pbk.views.report.SoView;

import java.util.Calendar;

import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateFormat;

/**
 * Генератор печатной формы отчёта "Ежедневная посменная численность контролёров ГУП "Мосгортранс" по территориальному подразделению"
 * с заполненными полями отчёта.
 */
public class So4CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   private So4View weekDaysRow;
   private int weekDaysRowCount;

   @Override
   protected void prepareData() {
	  weekDaysRow = new So4View();
	  weekDaysRow.setFirstShiftCount(0);
	  weekDaysRow.setSecondShiftCount(0);
	  weekDaysRow.setDayCount(0);
	  weekDaysRow.setNightCount(0);
	  weekDaysRow.setTotalCount(0);
	  weekDaysRowCount = 0;
   }

   protected int processTotal(SoReportBeanData data, int row, So4View weekDaysRow, int weekDaysRowCount) {
	  So4View total = (So4View) data.getTotal();
	  processValueCell(cells, row, So4ValueCell.DATE.getCol(), "Итого", CellStyle.TEXT);
	  processValueCell(cells, row, So4ValueCell.FIRST_SHIFT_COUNT.getCol(), total.getFirstShiftCount(), CellStyle.TEXT);
	  processValueCell(cells, row, So4ValueCell.SECOND_SHIFT_COUNT.getCol(), total.getSecondShiftCount(), CellStyle.TEXT);
	  processValueCell(cells, row, So4ValueCell.DAY_COUNT.getCol(), total.getDayCount(), CellStyle.TEXT);
	  processValueCell(cells, row, So4ValueCell.NIGHT_COUNT.getCol(), total.getNightCount(), CellStyle.TEXT);
	  processValueCell(cells, row, So4ValueCell.TOTAL_COUNT.getCol(), total.getTotalCount(), CellStyle.INT);
	  cells.insertRow(row + 1);
	  processValueCell(cells, row + 1, So4ValueCell.DATE.getCol(), "Среднее за период", CellStyle.TEXT);
	  processValueCell(cells, row + 1, So4ValueCell.FIRST_SHIFT_COUNT.getCol(), total.getFirstShiftCount() / (row - 1), CellStyle.TEXT);
	  processValueCell(cells, row + 1, So4ValueCell.SECOND_SHIFT_COUNT.getCol(), total.getSecondShiftCount() / (row - 1), CellStyle.TEXT);
	  processValueCell(cells, row + 1, So4ValueCell.DAY_COUNT.getCol(), total.getDayCount() / (row - 1), CellStyle.TEXT);
	  processValueCell(cells, row + 1, So4ValueCell.NIGHT_COUNT.getCol(), total.getNightCount() / (row - 1), CellStyle.TEXT);
	  processValueCell(cells, row + 1, So4ValueCell.TOTAL_COUNT.getCol(), total.getTotalCount() / (row - 1), CellStyle.INT);
	  cells.insertRow(row + 2);
	  processValueCell(cells, row + 2, So4ValueCell.DATE.getCol(), "Среднее по будним дням за период", CellStyle.TEXT);
	  processValueCell(cells, row + 2, So4ValueCell.FIRST_SHIFT_COUNT.getCol(), weekDaysRowCount > 0 ? weekDaysRow.getFirstShiftCount() / weekDaysRowCount : 0, CellStyle.TEXT);
	  processValueCell(cells, row + 2, So4ValueCell.SECOND_SHIFT_COUNT.getCol(), weekDaysRowCount > 0 ? weekDaysRow.getSecondShiftCount() / weekDaysRowCount : 0, CellStyle.TEXT);
	  processValueCell(cells, row + 2, So4ValueCell.DAY_COUNT.getCol(), weekDaysRowCount > 0 ? weekDaysRow.getDayCount() / weekDaysRowCount : 0, CellStyle.TEXT);
	  processValueCell(cells, row + 2, So4ValueCell.NIGHT_COUNT.getCol(), weekDaysRowCount > 0 ? weekDaysRow.getNightCount() / weekDaysRowCount : 0, CellStyle.TEXT);
	  processValueCell(cells, row + 2, So4ValueCell.TOTAL_COUNT.getCol(), weekDaysRowCount > 0 ? weekDaysRow.getTotalCount() / weekDaysRowCount : 0, CellStyle.INT);

	  return row + 3;
   }

   @Override
   protected int processTotal(int row, SoView view) {
	  return processTotal((SoReportBeanData) dataBean, row, weekDaysRow, weekDaysRowCount);
   }

   @Override
   protected int processRow(int row, SoView view) {
	  So4View soView = (So4View) view;
	  Calendar c = Calendar.getInstance();
	  c.setTime(soView.getDate());
	  int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
	  if (dayOfWeek > 1 && dayOfWeek < 7) {
		 weekDaysRow.setFirstShiftCount(weekDaysRow.getFirstShiftCount() + soView.getFirstShiftCount());
		 weekDaysRow.setSecondShiftCount(weekDaysRow.getSecondShiftCount() + soView.getSecondShiftCount());
		 weekDaysRow.setDayCount(weekDaysRow.getDayCount() + soView.getDayCount());
		 weekDaysRow.setNightCount(weekDaysRow.getNightCount() + soView.getNightCount());
		 weekDaysRow.setTotalCount(weekDaysRow.getTotalCount() + soView.getTotalCount());
		 weekDaysRowCount++;
	  }
	  processValueCell(cells, row, So4ValueCell.DATE.getCol(), makeGeneralDateFormat().format(soView.getDate()), CellStyle.TEXT);
	  processValueCell(cells, row, So4ValueCell.FIRST_SHIFT_COUNT.getCol(), soView.getFirstShiftCount(), CellStyle.INT);
	  processValueCell(cells, row, So4ValueCell.SECOND_SHIFT_COUNT.getCol(), soView.getSecondShiftCount(), CellStyle.INT);
	  processValueCell(cells, row, So4ValueCell.DAY_COUNT.getCol(), soView.getDayCount(), CellStyle.INT);
	  processValueCell(cells, row, So4ValueCell.NIGHT_COUNT.getCol(), soView.getNightCount(), CellStyle.INT);
	  processValueCell(cells, row, So4ValueCell.TOTAL_COUNT.getCol(), soView.getTotalCount(), CellStyle.INT);
	  return row;
   }

}
