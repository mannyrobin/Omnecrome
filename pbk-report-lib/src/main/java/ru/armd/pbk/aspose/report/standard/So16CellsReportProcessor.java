package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So16ValueCell;
import ru.armd.pbk.views.report.So16View;
import ru.armd.pbk.views.report.SoView;

import static ru.armd.pbk.utils.date.DateUtils.makeGeneralTimeFormat;

/**
 * Генератор печатной формы отчёта "Сравнительный анализ данных пассажиропотока (АСКП/АСМ-ПП) поостановочно"
 * с заполненными полями отчёта.
 */
public class So16CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected void prepareData() {
	  headerHeight = 3;
   }

   @Override
   protected int processRow(int row, SoView view) {
	  So16View soView = (So16View) view;
	  processValueCell(cells, row, So16ValueCell.STOP_NAME.getCol(), soView.getStopName(), CellStyle.TEXT);
	  String askpArrivalTime = makeGeneralTimeFormat().format(soView.getAskpArrivalTime());
	  processValueCell(cells, row, So16ValueCell.ASKP_ARRIVAL_TIME.getCol(), askpArrivalTime, CellStyle.TEXT);
	  String asmppArrivalTime = makeGeneralTimeFormat().format(soView.getAsmppArrivalTime());
	  processValueCell(cells, row, So16ValueCell.ASMPP_ARRIVAL_TIME.getCol(), asmppArrivalTime, CellStyle.TEXT);
	  processValueCell(cells, row, So16ValueCell.ASKP_PASSENGER_COUNT.getCol(), soView.getAskpPassengerCount(), CellStyle.INT);
	  processValueCell(cells, row, So16ValueCell.ASMPP_PASSENGER_COUNT.getCol(), soView.getAsmppPassengerCount(), CellStyle.INT);
	  processValueCell(cells, row, So16ValueCell.DIFFS.getCol(), soView.getDiffs(), CellStyle.INT);
	  return row;
   }

}
