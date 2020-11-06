package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So17ValueCell;
import ru.armd.pbk.views.report.So17View;
import ru.armd.pbk.views.report.SoView;

import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateFormat;

/**
 * Генератор печатной формы отчёта "Сводный сравнительный анализ данных пассажиропотока (АСКП/АСМ-ПП)" с заполненными полями отчёта.
 */
public class So17CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected void prepareData() {
	  headerHeight = 3;
   }

   @Override
   protected int processRow(int row, SoView view) {
	  So17View soView = (So17View) view;
	  processValueCell(cells, row, So17ValueCell.WORK_DATE.getCol(), makeGeneralDateFormat().format(soView.getDate()), CellStyle.TEXT);
	  processValueCell(cells, row, So17ValueCell.ROUTE_NUMBER.getCol(), soView.getRouteNumber(), CellStyle.TEXT);
	  processValueCell(cells, row, So17ValueCell.ASKP_PASSENGER_COUNT.getCol(), soView.getAskpPassengerCount(), CellStyle.INT);
	  processValueCell(cells, row, So17ValueCell.ASMPP_PASSENGER_COUNT.getCol(), soView.getAsmppPassengerCount(), CellStyle.INT);
	  processValueCell(cells, row, So17ValueCell.DIFFS.getCol(), soView.getDiffs(), CellStyle.INT);
	  return row;
   }

}
