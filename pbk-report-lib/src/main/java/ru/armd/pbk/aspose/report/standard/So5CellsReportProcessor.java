package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So5ValueCell;
import ru.armd.pbk.views.report.So5BranchEmplSummariesView;
import ru.armd.pbk.views.report.So5DaySummariesView;
import ru.armd.pbk.views.report.SoView;

import java.util.Date;
import java.util.List;

import static ru.armd.pbk.utils.date.DateUtils.addDays;
import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateFormat;

/**
 * Генератор печатной формы отчёта "Посменная численность контролёров ГУП "Мосгортранс" и среднее
 * значение численности за период" с заполненными полями отчёта.
 */
public class So5CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected void prepareData() {
	  headerHeight = 3;
	  SoReportBeanData data = (SoReportBeanData) dataBean;
	  List<SoView> soViews = data.getSoViews();
	  So5BranchEmplSummariesView soView = (So5BranchEmplSummariesView) soViews.get(0);
	  Date nextDate = null;
	  int dateCount = 0;
	  for (nextDate = soView.getDateStart(), dateCount = 0; !nextDate.after(soView.getDateEnd()); nextDate = addDays(nextDate, 1), dateCount++) {
		 if (dateCount > 0) {    // исключаем первую итерацию (первую тройку заголовков столбцов, т.к. она уже есть в шаблоне)
			cells.insertColumn(So5ValueCell.DAYS_SUMMARIES_BASE.getCol() + 3 * dateCount);
			cells.insertColumn(So5ValueCell.DAYS_SUMMARIES_BASE.getCol() + 3 * dateCount + 1);
			cells.insertColumn(So5ValueCell.DAYS_SUMMARIES_BASE.getCol() + 3 * dateCount + 2);
			cells.merge(1, So5ValueCell.DAYS_SUMMARIES_BASE.getCol() + 3 * dateCount, 1, 3);
			String firstShiftHeaderText = cells.get(2, So5ValueCell.DAYS_SUMMARIES_BASE.getCol()).getStringValue();
			String secondShiftHeaderText = cells.get(2, So5ValueCell.DAYS_SUMMARIES_BASE.getCol() + 1).getStringValue();
			String totalHeaderText = cells.get(2, So5ValueCell.DAYS_SUMMARIES_BASE.getCol() + 2).getStringValue();
			processValueCell(this.cells, 2, So5ValueCell.DAYS_SUMMARIES_BASE.getCol() + 3 * dateCount, firstShiftHeaderText, CellStyle.TEXT);
			processValueCell(this.cells, 2, So5ValueCell.DAYS_SUMMARIES_BASE.getCol() + 3 * dateCount + 1, secondShiftHeaderText, CellStyle.TEXT);
			processValueCell(this.cells, 2, So5ValueCell.DAYS_SUMMARIES_BASE.getCol() + 3 * dateCount + 2, totalHeaderText, CellStyle.TEXT);
		 }
		 // а дату в первой тройке надо вставить
		 String nextDateStr = makeGeneralDateFormat().format(nextDate);
		 processValueCell(cells, 1, So5ValueCell.DAYS_SUMMARIES_BASE.getCol() + 3 * dateCount, nextDateStr, CellStyle.TEXT);
	  }
   }

   @Override
   protected int processTotal(int row, SoView view) {
	  processValue((So5BranchEmplSummariesView) view, row, true);
	  return row + 1;
   }

   protected void processValue(So5BranchEmplSummariesView soView, int row, Boolean total) {
	  processValueCell(cells, row, So5ValueCell.BRANCH_NAME.getCol(), total ? "Итого" : soView.getBranch().getName(), CellStyle.TEXT);
	  processValueCell(cells, row, So5ValueCell.PLAN_COUNT.getCol(), soView.getPlanCount(), CellStyle.INT);
	  processValueCell(cells, row, So5ValueCell.FACT_COUNT.getCol(), soView.getFactCount(), CellStyle.INT);
	  List<So5DaySummariesView> daysSummaries = soView.getDaysSummaries();
	  Date nextDate = null;
	  int dateCount = 0;
	  for (nextDate = soView.getDateStart(), dateCount = 0; !nextDate.after(soView.getDateEnd()); nextDate = addDays(nextDate, 1), dateCount++) {
		 boolean processed = false;
		 int daySummariesBase = So5ValueCell.DAYS_SUMMARIES_BASE.getCol() + 3 * dateCount;
		 for (So5DaySummariesView daySummaries : daysSummaries) {
			if (nextDate.equals(daySummaries.getDate())) {
			   processValueCell(cells, row, daySummariesBase, daySummaries.getFirstShift(), CellStyle.INT);
			   processValueCell(cells, row, daySummariesBase + 1, daySummaries.getSecondShift(), CellStyle.INT);
			   processValueCell(cells, row, daySummariesBase + 2, daySummaries.getTotal(), CellStyle.INT);
			   processed = true;
			}
		 }
		 if (!processed) {
			processValueCell(cells, row, daySummariesBase, 0, CellStyle.INT);
			processValueCell(cells, row, daySummariesBase + 1, 0, CellStyle.INT);
			processValueCell(cells, row, daySummariesBase + 2, 0, CellStyle.INT);
		 }
	  }
	  int daysTotalSummariesBase = So5ValueCell.DAYS_TOTAL_SUMMARIES_INIT_BASE.getCol() + 3 * dateCount;
	  processValueCell(cells, row, daysTotalSummariesBase, soView.getDaysTotalSummaries().getFirstShift(), CellStyle.INT);
	  processValueCell(cells, row, daysTotalSummariesBase + 1, soView.getDaysTotalSummaries().getSecondShift(), CellStyle.INT);
	  processValueCell(cells, row, daysTotalSummariesBase + 2, soView.getDaysTotalSummaries().getTotal(), CellStyle.INT);
	  int averageCol = So5ValueCell.AVERAGE_INIT.getCol() + 3 * dateCount;
	  processValueCell(cells, row, averageCol, soView.getAverage(), CellStyle.DOUBLE_2);
   }

   @Override
   protected int processRow(int row, SoView view) {
	  So5BranchEmplSummariesView soView = (So5BranchEmplSummariesView) view;
	  processValue(soView, row, false);
	  return row;
   }

}
