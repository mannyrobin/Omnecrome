package ru.armd.pbk.aspose.report.standard;

import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateTimeFormat;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So21ValueCell;
import ru.armd.pbk.views.report.So21View;
import ru.armd.pbk.views.report.SoView;

/**
 * Генератор печатной формы отчёта "Проходы по БСК контролера" с заполненными полями отчёта.
 */
public class So21CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected int processRow(int row, SoView view) {
	  So21View soView = (So21View) view;
	  if (soView.getDateTimes() != null) {
			 String dateTimes = makeGeneralDateTimeFormat().format(soView.getDateTimes());
			 processValueCell(cells, row, So21ValueCell.DATETIMES.getCol(), dateTimes, CellStyle.TEXT);
	  }
	  processValueCell(cells, row, So21ValueCell.BRANCH.getCol(), soView.getBranch(), CellStyle.TEXT);
	  processValueCell(cells, row, So21ValueCell.EMPLOYEE.getCol(), soView.getEmployee(), CellStyle.TEXT);
	  processValueCell(cells, row, So21ValueCell.BSK.getCol(), soView.getBsk(), CellStyle.TEXT);
	  processValueCell(cells, row, So21ValueCell.OPERATOR.getCol(), soView.getOperator(), CellStyle.TEXT);
	  processValueCell(cells, row, So21ValueCell.ROUTE.getCol(), soView.getRoute(), CellStyle.TEXT);
	  processValueCell(cells, row, So21ValueCell.RUN.getCol(), soView.getRun(), CellStyle.INT);
	  return row;
   }

}
