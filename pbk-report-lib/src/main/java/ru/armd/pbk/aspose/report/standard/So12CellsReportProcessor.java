package ru.armd.pbk.aspose.report.standard;

import com.aspose.cells.CellsHelper;
import com.aspose.cells.PageSetup;
import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So12ValueCell;
import ru.armd.pbk.views.report.So12EmployeeCountView;
import ru.armd.pbk.views.report.So12VenueShiftEmployeeCountsView;
import ru.armd.pbk.views.report.So12VenueShiftView;
import ru.armd.pbk.views.report.SoView;

import java.util.Date;
import java.util.List;

import static ru.armd.pbk.utils.date.DateUtils.addDays;
import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateFormat;

/**
 * Генератор печатной формы отчёта "Совместный график по местам встреч" с заполненными полями отчёта.
 */
public class So12CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected void prepareData() {
	  headerHeight = 3;
	  SoReportBeanData data = (SoReportBeanData) dataBean;
	  List<SoView> soViews = data.getSoViews();
	  So12VenueShiftEmployeeCountsView soView = (So12VenueShiftEmployeeCountsView) soViews.get(0);
	  Date nextDate = null;
	  int dateCol = 0;
	  for (nextDate = soView.getDateStart(), dateCol = 0; !nextDate.after(soView.getDateEnd()); nextDate = addDays(nextDate, 1), dateCol++) {
		 if (dateCol > 0) {    // исключаем первую итерацию (первый столбец с датой, т.к. он уже есть в шаблоне)
			cells.insertColumn(So12ValueCell.DAY_BASE.getCol() + dateCol);
		 }
		 // а дату в первом столбце надо вставить
		 String nextDateStr = makeGeneralDateFormat().format(nextDate);
		 processValueCell(cells, 2, So12ValueCell.DAY_BASE.getCol() + dateCol, nextDateStr, CellStyle.TEXT, null, 90);
	  }
	  cells.merge(0, 0, 1, dateCol + 5);
	  cells.merge(1, So12ValueCell.DAY_BASE.getCol(), 1, dateCol);
   }

   @Override
   protected int processRow(int row, SoView view) {
	  So12VenueShiftEmployeeCountsView soView = (So12VenueShiftEmployeeCountsView) view;
	  So12VenueShiftView venue = soView.getVenue();
	  List<So12EmployeeCountView> employeeCounts = soView.getEmployeeCounts();
	  processValueCell(cells, row, So12ValueCell.COUNTY.getCol(), venue.getCounty(), CellStyle.TEXT);
	  processValueCell(cells, row, So12ValueCell.DISTRICT.getCol(), venue.getDistrict(), CellStyle.TEXT);
	  processValueCell(cells, row, So12ValueCell.VENUE.getCol(), venue.getVenue(), CellStyle.TEXT);
	  processValueCell(cells, row, So12ValueCell.VENUE_HOURS.getCol(), venue.getShiftHours(), CellStyle.TEXT);
	  Date nextDate = null;
	  int dateCol = 0;
	  for (nextDate = soView.getDateStart(), dateCol = 0; !nextDate.after(soView.getDateEnd()); nextDate = addDays(nextDate, 1), dateCol++) {
		 boolean processed = false;
		 for (So12EmployeeCountView employeeCount : employeeCounts) {
			if (nextDate.equals(employeeCount.getDate())) {
			   processValueCell(cells, row, So12ValueCell.DAY_BASE.getCol() + dateCol, employeeCount.getEmployeeCount(), CellStyle.INT);
			   processed = true;
			}
		 }
		 if (!processed) {
			processValueCell(cells, row, So12ValueCell.DAY_BASE.getCol() + dateCol, 0, CellStyle.INT);
		 }
	  }
	  return row;
   }

   @Override
   protected int processTotal(int row, SoView view) {
	  So12VenueShiftEmployeeCountsView total = (So12VenueShiftEmployeeCountsView) view;
	  processValueCell(cells, row, So12ValueCell.COUNTY.getCol(), "Итого", CellStyle.TEXT);
	  int dateCol = 0;
	  for (So12EmployeeCountView employeeCount : total.getEmployeeCounts()) {
		 processValueCell(cells, row, So12ValueCell.DAY_BASE.getCol() + dateCol, employeeCount.getEmployeeCount(), CellStyle.INT);
		 dateCol++;
	  }
	  return row + 1;
   }


   @Override
   protected void processTable() {
	  super.processTable();
	  processPrintArea();
   }

   private void processPrintArea() {
	  SoReportBeanData data = (SoReportBeanData) dataBean;
	  List<SoView> soViews = data.getSoViews();
	  So12VenueShiftEmployeeCountsView soView = (So12VenueShiftEmployeeCountsView) soViews.get(0);
	  int columnCount = 4;
	  for (Date nextDate = soView.getDateStart(); !nextDate.after(soView.getDateEnd()); nextDate = addDays(nextDate, 1)) {
		 columnCount++;
	  }
	  PageSetup pageSetup = getWorksheet().getPageSetup();
	  String allCellsRange = CellsHelper.cellIndexToName(0, 0) + ":" + CellsHelper.cellIndexToName(soViews.size() + headerHeight, columnCount - 1);
	  pageSetup.setPrintArea(allCellsRange);
	  pageSetup.setFitToPagesWide(1);
   }
}
