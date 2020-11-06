package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So11ValueCell;
import ru.armd.pbk.views.report.So11View;
import ru.armd.pbk.views.report.SoView;

/**
 * Генератор печатной формы отчёта "Работа контролеров" с заполненными полями отчёта.
 */
public class So11CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected int processRow(int row, SoView view) {
	  So11View soView = (So11View) view;
	  processValueCell(cells, row, So11ValueCell.TO_SDIK.getCol(), soView.getToSdik(), CellStyle.TEXT);
	  processValueCell(cells, row, So11ValueCell.PLAN_EMPLOYEE_COUNT.getCol(), soView.getPlanEmployeeCount(), CellStyle.INT);
	  processValueCell(cells, row, So11ValueCell.FACT_EMPLOYEE_COUNT.getCol(), soView.getFactEmployeeCount(), CellStyle.INT);
	  processValueCell(cells, row, So11ValueCell.EXEMPT_SKM_COUNT.getCol(), soView.getExemptSkmCount(), CellStyle.INT);
	  processValueCell(cells, row, So11ValueCell.EXEMPT_SKMO_COUNT.getCol(), soView.getExemptSkmoCount(), CellStyle.INT);
	  processValueCell(cells, row, So11ValueCell.EXEMPT_VESB_COUNT.getCol(), soView.getExemptVesbCount(), CellStyle.INT);
	  processValueCell(cells, row, So11ValueCell.EXEMPT_LPK_COUNT.getCol(), soView.getExemptLpkCount(), CellStyle.INT);
	  processValueCell(cells, row, So11ValueCell.TICKET_SOLD_COUNT.getCol(), soView.getTicketSoldCount(), CellStyle.INT);
	  processValueCell(cells, row, So11ValueCell.TS_CHECK_COUNT.getCol(), soView.getTsCheckCount(), CellStyle.INT);
	  processValueCell(cells, row, So11ValueCell.EXEMPT_VALIDLESS_COUNT.getCol(), soView.getExemptValidLessCount(), CellStyle.INT);
	  processValueCell(cells, row, So11ValueCell.PLANT_STOWAWAY_COUNT.getCol(), soView.getPlantStowawayCount(), CellStyle.INT);
	  processValueCell(cells, row, So11ValueCell.DELIVERY_OVD_COUNT.getCol(), soView.getDeliveryOvdCount(), CellStyle.INT);
	  processValueCell(cells, row, So11ValueCell.TOTAL_SKM_SKMO_VESB.getCol(), soView.getTotalSkmSkmoVesb(), CellStyle.INT);
	  return row;
   }

}
