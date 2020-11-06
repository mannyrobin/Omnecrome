package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So18ValueCell;
import ru.armd.pbk.views.report.So18View;
import ru.armd.pbk.views.report.SoView;

import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateFormat;

/**
 * Генератор печатной формы отчёта "Сопоставительный анализ данных по наряд-заданию и из АСКП" с заполненными полями отчёта.
 */
public class So18CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected void prepareData() {
	  headerHeight = 3;
   }

   @Override
   protected int processRow(int row, SoView view) {
	  So18View soView = (So18View) view;
	  if (soView.getDate() != null) {
		 processValueCell(cells, row, So18ValueCell.DATE.getCol(), makeGeneralDateFormat().format(soView.getDate()), CellStyle.TEXT);
	  }
	  processValueCell(cells, row, So18ValueCell.EMPLOYEE.getCol(), soView.getEmployee(), CellStyle.TEXT);
	  processValueCell(cells, row, So18ValueCell.ROUTE_NUMBER.getCol(), soView.getRouteNumber(), CellStyle.TEXT);
	  processValueCell(cells, row, So18ValueCell.TS_CHECK_TASK_REPORT_COUNT.getCol(), soView.getTsCheckTaskReportCount(), CellStyle.INT);
	  processValueCell(cells, row, So18ValueCell.TS_CHECK_ASKP_COUNT.getCol(), soView.getTsCheckAskpCount(), CellStyle.INT);
	  return row;
   }

}
