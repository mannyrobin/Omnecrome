package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So24ValueCell;
import ru.armd.pbk.views.report.So24View;
import ru.armd.pbk.views.report.SoView;

import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateFormat;

/**
 * Генератор печатной формы отчёта "Сопоставительный анализ данных по наряд-заданию и из АСКП" с заполненными полями отчёта.
 */
public class So24CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected void prepareData() {
	  headerHeight = 2;
   }

   @Override
   protected int processRow(int row, SoView view) {
	  So24View soView = (So24View) view;

	  processValueCell(cells, row, So24ValueCell.TASK_DATE.getCol(), makeGeneralDateFormat().format(soView.getTaskDate()), CellStyle.TEXT);
	  processValueCell(cells, row, So24ValueCell.EMPLOYEE.getCol(), soView.getEmployeeName(), CellStyle.TEXT);
	  processValueCell(cells, row, So24ValueCell.PERSONNEL_NUMBER.getCol(), soView.getPersonnelNumber(), CellStyle.INT);
	  processValueCell(cells, row, So24ValueCell.SHIFT_NAME.getCol(), soView.getShiftName(), CellStyle.TEXT);
	  processValueCell(cells, row, So24ValueCell.DEPARTMENT.getCol(), soView.getDepartmentName(), CellStyle.TEXT);
	  processValueCell(cells, row, So24ValueCell.BSO_NUMBER.getCol(), soView.getBsoNumber(), CellStyle.TEXT);
	  processValueCell(cells, row, So24ValueCell.TASK_STATE.getCol(), soView.getTaskState(), CellStyle.TEXT);
	  processValueCell(cells, row, So24ValueCell.IS_BSK.getCol(), soView.getIsBsk(), CellStyle.INT);
	  processValueCell(cells, row, So24ValueCell.IS_VIDEO.getCol(), soView.getIsVideo(), CellStyle.INT);
	  processValueCell(cells, row, So24ValueCell.IS_CLOSED.getCol(), soView.getIsClosed(), CellStyle.INT);
	  return row;
   }

}
