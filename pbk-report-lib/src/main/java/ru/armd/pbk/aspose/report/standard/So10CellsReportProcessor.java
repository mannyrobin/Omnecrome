package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So10ValueCell;
import ru.armd.pbk.views.report.So10View;
import ru.armd.pbk.views.report.So10ViewFourtRow;
import ru.armd.pbk.views.report.SoView;

/**
 * Генератор печатной формы отчёта "Сводная форма по эффективности работы контролеров"
 * с заполненными полями отчёта.
 */
public class So10CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected int processRow(int row, SoView view) {
	  if (view instanceof So10View) {
		 So10View soView = (So10View) view;
		 processValueCell(cells, row, So10ValueCell.CRITERION.getCol(), soView.getCriterion(), CellStyle.TEXT);
		 processValueCell(cells, row, So10ValueCell.CUR_PERIOD.getCol(), soView.getCurPeriod(), CellStyle.INT);
		 processValueCell(cells, row, So10ValueCell.PREV_PERIOD.getCol(), soView.getPrevPeriod(), CellStyle.INT);
	  } else if (view instanceof So10ViewFourtRow) {
		 So10ViewFourtRow soView = (So10ViewFourtRow) view;
		 processValueCell(cells, row, So10ValueCell.CRITERION.getCol(), soView.getCriterion(), CellStyle.TEXT);
		 processValueCell(cells, row, So10ValueCell.CUR_PERIOD.getCol(), soView.getCurPeriod(), CellStyle.TEXT);
	  }

	  return row;
   }

}
