package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So24ValueCell;
import ru.armd.pbk.enums.report.standard.So25ValueCell;
import ru.armd.pbk.views.report.So24View;
import ru.armd.pbk.views.report.So25View;
import ru.armd.pbk.views.report.SoView;

import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateFormat;

/**
 * Генератор печатной формы отчёта "Cписок маршрутов АСМ-ПП" с заполненными полями отчёта.
 */
public class So25CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected void prepareData() {
	  headerHeight = 2;
   }

   @Override
   protected int processRow(int row, SoView view) {
	  So25View soView = (So25View) view;

	  processValueCell(cells, row, So25ValueCell.WORK_DATE.getCol(), makeGeneralDateFormat().format(soView.getWorkDate()), CellStyle.TEXT);
	  processValueCell(cells, row, So25ValueCell.ROUTE_NAME.getCol(), soView.getRouteName(), CellStyle.TEXT);
	  processValueCell(cells, row, So25ValueCell.TYPE_TS.getCol(), soView.getTypeTs(), CellStyle.TEXT);
	  processValueCell(cells, row, So25ValueCell.STOPS.getCol(), soView.getStops(), CellStyle.INT);

	  return row;
   }

}
