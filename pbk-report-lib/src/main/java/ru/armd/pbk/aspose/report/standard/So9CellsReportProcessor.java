package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So9ValueCell;
import ru.armd.pbk.views.report.So9View;
import ru.armd.pbk.views.report.SoView;


/**
 * Генератор печатной формы отчёта "Сводные данные по работе контролеров по подразделениям" с заполненными полями отчёта.
 */
public class So9CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected int processRow(int row, SoView view) {
	  So9View soView = (So9View) view;
	  processValueCell(cells, row, So9ValueCell.EMPLOYEE.getCol(), soView.getEmployee(), CellStyle.TEXT);
	  processValueCell(cells, row, So9ValueCell.PERSONAL_NUMBER.getCol(), soView.getPersonalNumber(), CellStyle.TEXT);
	  processValueCell(cells, row, So9ValueCell.EXEMPT_SKM_COUNT.getCol(), soView.getExemptSkmCount(), CellStyle.INT);
	  processValueCell(cells, row, So9ValueCell.EXEMPT_SKMO_COUNT.getCol(), soView.getExemptSkmoCount(), CellStyle.INT);
	  processValueCell(cells, row, So9ValueCell.EXEMPT_VESB_COUNT.getCol(), soView.getExemptVesbCount(), CellStyle.INT);
	  processValueCell(cells, row, So9ValueCell.LPK_COUNT.getCol(), soView.getLpkCount(), CellStyle.INT);
	  processValueCell(cells, row, So9ValueCell.TS_CHECK_COUNT.getCol(), soView.getTsCheckCount(), CellStyle.INT);
	  processValueCell(cells, row, So9ValueCell.EXEMPT_VALIDLESS_COUNT.getCol(), soView.getExemptValidLessCount(), CellStyle.INT);
	  processValueCell(cells, row, So9ValueCell.PLANT_STOWAWAY_COUNT.getCol(), soView.getPlantStowawayCount(), CellStyle.INT);
	  processValueCell(cells, row, So9ValueCell.DELIVERY_OVD_COUNT.getCol(), soView.getDeliveryOvdCount(), CellStyle.INT);
	  processValueCell(cells, row, So9ValueCell.ORDINANCE_1000_COUNT.getCol(), soView.getOrdinance1000Count(), CellStyle.INT);
	  processValueCell(cells, row, So9ValueCell.ORDINANCE_2500_COUNT.getCol(), soView.getOrdinance2500Count(), CellStyle.INT);
	  processValueCell(cells, row, So9ValueCell.PROTOCOL_1000_COUNT.getCol(), soView.getProtocol1000Count(), CellStyle.INT);
	  processValueCell(cells, row, So9ValueCell.PROTOCOL_2500_COUNT.getCol(), soView.getProtocol2500Count(), CellStyle.INT);
	  processValueCell(cells, row, So9ValueCell.TICKET_SOLD_COUNT.getCol(), soView.getTicketSoldCount(), CellStyle.INT);
	  return row;
   }

}
