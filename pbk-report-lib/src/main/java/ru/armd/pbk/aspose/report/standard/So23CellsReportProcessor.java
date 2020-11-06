package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So23ValueCell;
import ru.armd.pbk.views.report.So23EmplSummariesView;
import ru.armd.pbk.views.report.So23DaySummariesView;
import ru.armd.pbk.views.report.SoView;

import java.util.Date;
import java.util.List;

import static ru.armd.pbk.utils.date.DateUtils.addDays;
import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateFormat;

/**
 * Генератор печатной формы отчёта "Сводные данные по работе контролеров за период.
 */
public class So23CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected void prepareData() {
	  headerHeight = 3;
	  SoReportBeanData data = (SoReportBeanData) dataBean;
	  List<SoView> soViews = data.getSoViews();
	  So23EmplSummariesView soView = (So23EmplSummariesView) soViews.get(0);
	  Date nextDate = null;
	  int dateCount = 0;
	  for (nextDate = soView.getDateStart(), dateCount = 0; !nextDate.after(soView.getDateEnd()); nextDate = addDays(nextDate, 1), dateCount++) {
		 if (dateCount > 0) {    // исключаем первую итерацию (первую тройку заголовков столбцов, т.к. она уже есть в шаблоне)
			cells.insertColumn(So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount);
			cells.insertColumn(So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 1);
			cells.insertColumn(So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 2);
			cells.insertColumn(So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 3);
			cells.insertColumn(So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 4);
			cells.insertColumn(So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 5);
			cells.insertColumn(So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 6);
			cells.insertColumn(So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 7);
			cells.insertColumn(So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 8);
			cells.insertColumn(So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 9);
			cells.insertColumn(So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 10);
			cells.merge(1, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount, 1, 11);
			String exemptSkmCountText = cells.get(2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol()).getStringValue();
			String exemptSkmoCountText = cells.get(2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 1).getStringValue();
			String exemptVesbCountText = cells.get(2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 2).getStringValue();
			String lpkCountText = cells.get(2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 3).getStringValue();
			String tsCheckCountText = cells.get(2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 4).getStringValue();
			String exemptValidLessCountText = cells.get(2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 5).getStringValue();
			String plantStowawayCountText = cells.get(2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 6).getStringValue();
			String deliveryOvdCountText = cells.get(2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 7).getStringValue();
			String ticketSoldCountText = cells.get(2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 8).getStringValue();
			String ordinance1000CountText = cells.get(2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 9).getStringValue();
			String ordinance2500CountText = cells.get(2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 10).getStringValue();
			processValueCell(this.cells, 2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount, exemptSkmCountText, CellStyle.TEXT);
			processValueCell(this.cells, 2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 1, exemptSkmoCountText, CellStyle.TEXT);
			processValueCell(this.cells, 2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 2, exemptVesbCountText, CellStyle.TEXT);
			processValueCell(this.cells, 2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 3, lpkCountText, CellStyle.TEXT);
			processValueCell(this.cells, 2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 4, tsCheckCountText, CellStyle.TEXT);
			processValueCell(this.cells, 2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 5, exemptValidLessCountText, CellStyle.TEXT);
			processValueCell(this.cells, 2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 6, plantStowawayCountText, CellStyle.TEXT);
			processValueCell(this.cells, 2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 7, deliveryOvdCountText, CellStyle.TEXT);
			processValueCell(this.cells, 2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 8, ticketSoldCountText, CellStyle.TEXT);
			processValueCell(this.cells, 2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 9, ordinance1000CountText, CellStyle.TEXT);
			processValueCell(this.cells, 2, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount + 10, ordinance2500CountText, CellStyle.TEXT);
			 }
		 // а дату в первой тройке надо вставить
		 String nextDateStr = makeGeneralDateFormat().format(nextDate);
		 processValueCell(cells, 1, So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount, nextDateStr, CellStyle.TEXT);
	  }
   }

   @Override
   protected int processTotal(int row, SoView view) {
	  processValue((So23EmplSummariesView) view, row, true);
	  return row + 1;
   }

   protected void processValue(So23EmplSummariesView soView, int row, Boolean total) {
	  processValueCell(cells, row, So23ValueCell.EMPLOYEE_NAME.getCol(), total ? "Итого" : soView.getEmployee().getName(), CellStyle.TEXT);
	  processValueCell(cells, row, So23ValueCell.PERSONAL_NUMBER.getCol(), total ? "" : soView.getEmployee().getPersonalNumberId(), CellStyle.TEXT);
	  List<So23DaySummariesView> daysSummaries = soView.getDaysSummaries();
	  Date nextDate = null;
	  int dateCount = 0;
	  for (nextDate = soView.getDateStart(), dateCount = 0; !nextDate.after(soView.getDateEnd()); nextDate = addDays(nextDate, 1), dateCount++) {
		 boolean processed = false;
		 int daySummariesBase = So23ValueCell.DAYS_SUMMARIES_BASE.getCol() + 11 * dateCount;
		 for (So23DaySummariesView daySummaries : daysSummaries) {
			if (nextDate.equals(daySummaries.getDate())) {
			   processValueCell(cells, row, daySummariesBase, daySummaries.getExemptSkm(), CellStyle.INT);
			   processValueCell(cells, row, daySummariesBase + 1, daySummaries.getExemptSkmo(), CellStyle.INT);
			   processValueCell(cells, row, daySummariesBase + 2, daySummaries.getExemptVesb(), CellStyle.INT);
			   processValueCell(cells, row, daySummariesBase + 3, daySummaries.getLpk(), CellStyle.INT);
			   processValueCell(cells, row, daySummariesBase + 4, daySummaries.getTsCheck(), CellStyle.INT);
			   processValueCell(cells, row, daySummariesBase + 5, daySummaries.getExemptValidLess(), CellStyle.INT);
			   processValueCell(cells, row, daySummariesBase + 6, daySummaries.getPlantStowaway(), CellStyle.INT);
			   processValueCell(cells, row, daySummariesBase + 7, daySummaries.getDeliveryOvd(), CellStyle.INT);
			   processValueCell(cells, row, daySummariesBase + 8, daySummaries.getTicketSold(), CellStyle.INT);
			   processValueCell(cells, row, daySummariesBase + 9, daySummaries.getOrdinance1000(), CellStyle.INT);
			   processValueCell(cells, row, daySummariesBase + 10, daySummaries.getOrdinance2500(), CellStyle.INT);
			   processed = true;
			}
		 }

		 if (!processed) {
			processValueCell(cells, row, daySummariesBase, 0, CellStyle.INT);
			processValueCell(cells, row, daySummariesBase + 1, 0, CellStyle.INT);
			processValueCell(cells, row, daySummariesBase + 2, 0, CellStyle.INT);
			processValueCell(cells, row, daySummariesBase + 3, 0, CellStyle.INT);
			processValueCell(cells, row, daySummariesBase + 4, 0, CellStyle.INT);
			processValueCell(cells, row, daySummariesBase + 5, 0, CellStyle.INT);
			processValueCell(cells, row, daySummariesBase + 6, 0, CellStyle.INT);
			processValueCell(cells, row, daySummariesBase + 7, 0, CellStyle.INT);
			processValueCell(cells, row, daySummariesBase + 8, 0, CellStyle.INT);
			processValueCell(cells, row, daySummariesBase + 9, 0, CellStyle.INT);
			processValueCell(cells, row, daySummariesBase + 10, 0, CellStyle.INT);
		 }
	  }
	  int daysTotalSummariesBase = So23ValueCell.DAYS_TOTAL_SUMMARIES_INIT_BASE.getCol() + 11 * dateCount - 1;
	  processValueCell(cells, row, daysTotalSummariesBase, soView.getDaysTotalSummaries().getExemptSkm(), CellStyle.INT);
	  processValueCell(cells, row, daysTotalSummariesBase + 1, soView.getDaysTotalSummaries().getExemptSkmo(), CellStyle.INT);
	  processValueCell(cells, row, daysTotalSummariesBase + 2, soView.getDaysTotalSummaries().getExemptVesb(), CellStyle.INT);
	  processValueCell(cells, row, daysTotalSummariesBase + 3, soView.getDaysTotalSummaries().getLpk(), CellStyle.INT);
	  processValueCell(cells, row, daysTotalSummariesBase + 4, soView.getDaysTotalSummaries().getTsCheck(), CellStyle.INT);
	  processValueCell(cells, row, daysTotalSummariesBase + 5, soView.getDaysTotalSummaries().getExemptValidLess(), CellStyle.INT);
	  processValueCell(cells, row, daysTotalSummariesBase + 6, soView.getDaysTotalSummaries().getPlantStowaway(), CellStyle.INT);
	  processValueCell(cells, row, daysTotalSummariesBase + 7, soView.getDaysTotalSummaries().getDeliveryOvd(), CellStyle.INT);
	  processValueCell(cells, row, daysTotalSummariesBase + 8, soView.getDaysTotalSummaries().getTicketSold(), CellStyle.INT);
	  processValueCell(cells, row, daysTotalSummariesBase + 9, soView.getDaysTotalSummaries().getOrdinance1000(), CellStyle.INT);
	  processValueCell(cells, row, daysTotalSummariesBase + 10, soView.getDaysTotalSummaries().getOrdinance2500(), CellStyle.INT);
	  }

   @Override
   protected int processRow(int row, SoView view) {
	  So23EmplSummariesView soView = (So23EmplSummariesView) view;
	  processValue(soView, row, false);
	  return row;
   }

}
