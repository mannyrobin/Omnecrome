package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So14ValueCell;
import ru.armd.pbk.views.report.So14View;
import ru.armd.pbk.views.report.SoView;

/**
 * Генератор печатной формы отчёта "Результаты ПБК за период" с заполненными полями отчёта.
 */
public class So14CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected void prepareData() {
	  headerHeight = 3;
	  autoFit = false;
   }

   @Override
   protected int processRow(int row, SoView view) {
	  So14View soView = (So14View) view;
	  processValueCell(cells, row, So14ValueCell.TO_SDIK.getCol(), soView.getToSdik(), CellStyle.TEXT);
	  processValueCell(cells, row, So14ValueCell.CUR_FACT_EMPLOYEE_TOTAL.getCol(), soView.getCurFactEmployeeTotal(), CellStyle.INT);
	  processValueCell(cells, row, So14ValueCell.CUR_EXEMPT_SKM_SKMO_VESB.getCol(), soView.getCurExemptSkmSkmoVesbCount(), CellStyle.INT);
	  processValueCell(cells, row, So14ValueCell.CUR_EXEMPT_LPK_COUNT.getCol(), soView.getCurExemptLpkCount(), CellStyle.INT);
	  processValueCell(cells, row, So14ValueCell.CUR_EXEMPT_VALIDLESS_COUNT.getCol(), soView.getCurExemptValidlessCount(), CellStyle.INT);
	  processValueCell(cells, row, So14ValueCell.CUR_PLANT_STOWAWAY_COUNT.getCol(), soView.getCurPlantStowawayCount(), CellStyle.INT);
	  processValueCell(cells, row, So14ValueCell.CUR_DELIVERY_OVD_COUNT.getCol(), soView.getCurDeliveryOvdCount(), CellStyle.INT);
	  processValueCell(cells, row, So14ValueCell.CUR_TICKET_SOLD_COUNT.getCol(), soView.getCurTicketSoldCount(), CellStyle.INT);
	  processValueCell(cells, row, So14ValueCell.CUR_EMPLOYEE_TASK_COUNT.getCol(), soView.getCurEmployeeTaskCount(), CellStyle.INT);
	  processValueCell(cells, row, So14ValueCell.PREV_FACT_EMPLOYEE_TOTAL.getCol(), soView.getPrevFactEmployeeTotal(), CellStyle.INT);
	  processValueCell(cells, row, So14ValueCell.PREV_EXEMPT_SKM_SKMO_VESB.getCol(), soView.getPrevExemptSkmSkmoVesbCount(), CellStyle.INT);
	  processValueCell(cells, row, So14ValueCell.PREV_EXEMPT_LPK_COUNT.getCol(), soView.getPrevExemptLpkCount(), CellStyle.INT);
	  processValueCell(cells, row, So14ValueCell.PREV_EXEMPT_VALIDLESS_COUNT.getCol(), soView.getPrevExemptValidlessCount(), CellStyle.INT);
	  processValueCell(cells, row, So14ValueCell.PREV_PLANT_STOWAWAY_COUNT.getCol(), soView.getPrevPlantStowawayCount(), CellStyle.INT);
	  processValueCell(cells, row, So14ValueCell.PREV_DELIVERY_OVD_COUNT.getCol(), soView.getPrevDeliveryOvdCount(), CellStyle.INT);
	  processValueCell(cells, row, So14ValueCell.PREV_TICKET_SOLD_COUNT.getCol(), soView.getPrevTicketSoldCount(), CellStyle.INT);
	  processValueCell(cells, row, So14ValueCell.PREV_EMPLOYEE_TASK_COUNT.getCol(), soView.getPrevEmployeeTaskCount(), CellStyle.INT);
	  return row;
   }

}
