package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So13ValueCell;
import ru.armd.pbk.views.report.So13TicketTypePassCountsView;
import ru.armd.pbk.views.report.So13TicketTypeView;
import ru.armd.pbk.views.report.SoView;

import java.util.Map;

/**
 * Генератор печатной формы отчёта "Маршруты и выходы" с заполненными полями отчёта.
 */
public class So13CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected void prepareData() {
	  headerHeight = 3;
	  autoFit = false;
   }

   @Override
   protected int processRow(int row, SoView view) {
	  So13TicketTypePassCountsView soView = (So13TicketTypePassCountsView) view;
	  So13TicketTypeView ticketType = soView.getTicketType();
	  Map<String, Integer> passCounts = soView.getPassCounts();
	  processValueCell(cells, row, So13ValueCell.WORK_DATE.getCol(), ticketType.getWorkDate(), CellStyle.TEXT);
	  processValueCell(cells, row, So13ValueCell.TICKET_TYPE_CODE.getCol(), ticketType.getTicketTypeCode(), CellStyle.TEXT);
	  processValueCell(cells, row, So13ValueCell.TICKET_TYPE_NAME.getCol(), ticketType.getTicketTypeName(), CellStyle.TEXT);
	  Integer value = null;
	  for (int j = 0; j < 24; j++) {
		 value = passCounts.get(String.valueOf((j + 3) % 24));
		 processValueCell(cells, row, So13ValueCell.PASS_COUNTS_BASE.getCol() + j, value != null ? value : 0, CellStyle.INT);
	  }
	  processValueCell(cells, row, So13ValueCell.PASS_COUNTS_TOTAL.getCol(), passCounts.get("ticketTypeTotalPassCount"), CellStyle.INT);
	  return row;
   }

}
