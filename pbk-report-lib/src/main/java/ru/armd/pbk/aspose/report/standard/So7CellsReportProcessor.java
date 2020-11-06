package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So7ValueCell;
import ru.armd.pbk.views.report.So7View;
import ru.armd.pbk.views.report.SoView;

/**
 * Генератор печатной формы отчёта "Количество перевезенных пассажиров по маршрутам" с заполненными полями отчёта.
 */
public class So7CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected int processRow(int row, SoView view) {
	  So7View soView = (So7View) view;
	  processValueCell(cells, row, So7ValueCell.BRANCH.getCol(), soView.getBranch(), CellStyle.TEXT);
	  processValueCell(cells, row, So7ValueCell.COUNTY.getCol(), soView.getCounty(), CellStyle.TEXT);
	  processValueCell(cells, row, So7ValueCell.ROUTE.getCol(), soView.getRoute(), CellStyle.TEXT);
	  processValueCell(cells, row, So7ValueCell.CUR_PASSENGER_COUNT.getCol(), soView.getCurPassengerCount(), CellStyle.INT);
	  processValueCell(cells, row, So7ValueCell.PREV_PASSENGER_COUNT.getCol(), soView.getPrevPassengerCount(), CellStyle.INT);
	  return row;
   }

}
