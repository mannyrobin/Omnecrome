package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So15ValueCell;
import ru.armd.pbk.views.report.So15View;
import ru.armd.pbk.views.report.SoView;

import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateTimeFormat;

/**
 * Генератор печатной формы отчёта "Итоги ПБК по контролеру по данным АСУ ПБК" с заполненными полями отчёта.
 */
public class So15CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected int processRow(int row, SoView view) {
	  So15View soView = (So15View) view;
	  if (soView.getCheckStartDateTime() != null) {
		 String checkStartDateTime = makeGeneralDateTimeFormat().format(soView.getCheckStartDateTime());
		 processValueCell(cells, row, So15ValueCell.CHECK_START_DATETIME.getCol(), checkStartDateTime, CellStyle.TEXT);
	  }
	  processValueCell(cells, row, So15ValueCell.ROUTE_NUMBER.getCol(), soView.getRouteNumber(), CellStyle.TEXT);
	  processValueCell(cells, row, So15ValueCell.TS_OUTGO_NUMBER.getCol(), soView.getTsOutgoNumber(), CellStyle.INT);
	  processValueCell(cells, row, So15ValueCell.EMPLOYEE.getCol(), soView.getEmployee(), CellStyle.TEXT);
	  processValueCell(cells, row, So15ValueCell.DEPARTMENT.getCol(), soView.getDepartment(), CellStyle.TEXT);
	  processValueCell(cells, row, So15ValueCell.CHECKED_PASSENGER_COUNT.getCol(), soView.getCheckedPassengerCount(), CellStyle.INT);
	  return row;
   }

}
