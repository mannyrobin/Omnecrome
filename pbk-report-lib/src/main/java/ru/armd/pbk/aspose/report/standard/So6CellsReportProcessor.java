package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So6ValueCell;
import ru.armd.pbk.views.report.So6View;
import ru.armd.pbk.views.report.SoView;

/**
 * Генератор печатной формы отчёта "Результаты контроля (мотивация)" с заполненными полями отчёта.
 */
public class So6CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected int processRow(int row, SoView view) {
	  So6View soView = (So6View) view;
	  processValueCell(cells, row, So6ValueCell.ID.getCol(), soView.getId(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.TO_SDIK.getCol(), soView.getToSdik(), CellStyle.TEXT);
	  processValueCell(cells, row, So6ValueCell.SURNAME.getCol(), soView.getSurname(), CellStyle.TEXT);
	  processValueCell(cells, row, So6ValueCell.NAME.getCol(), soView.getName(), CellStyle.TEXT);
	  processValueCell(cells, row, So6ValueCell.PATRONUMIC.getCol(), soView.getPatronumic(), CellStyle.TEXT);
	  processValueCell(cells, row, So6ValueCell.PERSONAL_NUMBER.getCol(), soView.getPersonalNumber(), CellStyle.TEXT);
	  processValueCell(cells, row, So6ValueCell.FOR_PLAN_USE.getCol(), soView.getForPlanUse(), CellStyle.TEXT);
	  processValueCell(cells, row, So6ValueCell.SCHEDULE_NUMBER.getCol(), soView.getScheduleNumber(), CellStyle.TEXT);
	  processValueCell(cells, row, So6ValueCell.PLAN_SHIFT_COUNT.getCol(), soView.getPlanShiftCount(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.FACT_SHIFT_COUNT.getCol(), soView.getFactShiftCount(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.TOTAL_PLAN_COUNT.getCol(), soView.getTotalPlanCount(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.TOTAL_FACT_COUNT.getCol(), soView.getTotalFactCount(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.FACT_SKM_COUNT.getCol(), soView.getFactSkmCount(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.FACT_SKMO_COUNT.getCol(), soView.getFactSkmoCount(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.FACT_VESB_COUNT.getCol(), soView.getFactVesbCount(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.FACT_VALIDLESS_COUNT.getCol(), soView.getFactValidlessCount(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.FACT_LPK_COUNT.getCol(), soView.getFactLpkCount(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.FACT_SOLD_COUNT.getCol(), soView.getFactLpkCount(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.FACT_STOWAWAY_COUNT.getCol(), soView.getFactLpkCount(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.FACT_DELIVERY_COUNT.getCol(), soView.getFactLpkCount(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.FACT_1000_COUNT.getCol(), soView.getFactLpkCount(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.FACT_2500_COUNT.getCol(), soView.getFactLpkCount(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.EXCESS_SKM_COUNT.getCol(), soView.getExcessSkmCount(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.UNDER_SKM.getCol(), soView.getUnderSkm(), CellStyle.TEXT);
	  processValueCell(cells, row, So6ValueCell.UNDER_SKM_VALUE.getCol(), soView.getUnderSkmValue(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.PLAN_SUBTR.getCol(), soView.getPlanSubtr(), CellStyle.INT);
	  processValueCell(cells, row, So6ValueCell.PCNT.getCol(), soView.getPcnt(), CellStyle.DOUBLE_2);
	  return row;
   }

}
