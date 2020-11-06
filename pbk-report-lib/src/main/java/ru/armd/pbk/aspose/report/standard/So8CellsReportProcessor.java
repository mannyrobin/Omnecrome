package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So8ValueCell;
import ru.armd.pbk.views.report.So8View;
import ru.armd.pbk.views.report.SoView;

import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateFormat;

/**
 * Генератор печатной формы отчёта "Работа контролеров на маршруте" с заполненными полями отчёта.
 */
public class So8CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected void prepareData() {
	  autoFit = false;
   }

   @Override
   protected int processRow(int row, SoView view) {
	  So8View soView = (So8View) view;
	  if (soView.getDate() != null) {
		 processValueCell(cells, row, So8ValueCell.TASK_DATE.getCol(), makeGeneralDateFormat().format(soView.getDate()), CellStyle.TEXT);
	  }
	  processValueCell(cells, row, So8ValueCell.TS_CHECK_COUNT.getCol(), soView.getTsCheckCount(), CellStyle.TEXT);
	  processValueCell(cells, row, So8ValueCell.EXEMPT_SKM_COUNT.getCol(), soView.getExemptSkmCount(), CellStyle.INT);
	  processValueCell(cells, row, So8ValueCell.EXEMPT_SKMO_COUNT.getCol(), soView.getExemptSkmoCount(), CellStyle.INT);
	  processValueCell(cells, row, So8ValueCell.EXEMPT_VESB_COUNT.getCol(), soView.getExemptVesbCount(), CellStyle.INT);
	  processValueCell(cells, row, So8ValueCell.EXEMPT_VALIDLESS_COUNT.getCol(), soView.getExemptValidlessCount(), CellStyle.INT);
	  processValueCell(cells, row, So8ValueCell.TICKET_SOLD_COUNT.getCol(), soView.getTicketSoldCount(), CellStyle.INT);
	  processValueCell(cells, row, So8ValueCell.PROTOCOL_2500_COUNT.getCol(), soView.getProtocol2500Count(), CellStyle.INT);
	  processValueCell(cells, row, So8ValueCell.PROTOCOL_1000_COUNT.getCol(), soView.getProtocol1000Count(), CellStyle.INT);
	  processValueCell(cells, row, So8ValueCell.ORDINANCE_2500_COUNT.getCol(), soView.getOrdinance2500Count(), CellStyle.INT);
	  processValueCell(cells, row, So8ValueCell.ORDINANCE_1000_COUNT.getCol(), soView.getOrdinance1000Count(), CellStyle.INT);
	  processValueCell(cells, row, So8ValueCell.MGT_EMPLOYEE_COUNT.getCol(), soView.getMgtEmployeeCount(), CellStyle.INT);
	  processValueCell(cells, row, So8ValueCell.GKUOP_EMPLOYEE_COUNT.getCol(), soView.getGkuopEmployeeCount(), CellStyle.INT);
	  processValueCell(cells, row, So8ValueCell.DELIVERY_OVD_COUNT.getCol(), soView.getDeliveryOvdCount(), CellStyle.INT);
	  processValueCell(cells, row, So8ValueCell.EXEMPT_OTHER_LPK_COUNT.getCol(), soView.getExemptOtherLpkCount(), CellStyle.INT);
	  return row;
   }

}
