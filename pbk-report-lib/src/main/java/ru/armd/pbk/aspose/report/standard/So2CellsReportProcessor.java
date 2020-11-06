package ru.armd.pbk.aspose.report.standard;

import com.aspose.cells.CellsHelper;
import com.aspose.cells.PageSetup;
import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So2ValueCell;
import ru.armd.pbk.utils.NameUtils;
import ru.armd.pbk.views.report.So2EmployeeView;
import ru.armd.pbk.views.report.So2ShiftHoursView;
import ru.armd.pbk.views.report.SoView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static ru.armd.pbk.utils.NameUtils.shiftHoursDataToName;
import static ru.armd.pbk.utils.date.DateUtils.addDays;
import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateFormat;

/**
 * Генератор печатной формы отчёта "Табель фактически отработанного времени"
 * с заполненными полями отчёта.
 */
public class So2CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   private Date startDate;
   private Date endDate;

   @Override
   protected void prepareData() {
	  headerHeight = 3;
	  SoReportBeanData data = (SoReportBeanData) dataBean;
	  List<SoView> soViews = data.getSoViews();
	  So2EmployeeView soView = (So2EmployeeView) soViews.get(0);
	  startDate = soView.getStartDate();
	  endDate = soView.getEndDate();
	  Date nextDate = null;
	  int dateCol = 0;
	  for (nextDate = soView.getStartDate(), dateCol = 0; !nextDate.after(soView.getEndDate()); nextDate = addDays(nextDate, 1), dateCol++) {
		 if (dateCol > 0) {    // исключаем первую итерацию (первый столбец с датой, т.к. он уже есть в шаблоне)
			cells.insertColumn(So2ValueCell.DAY_BASE.getCol() + dateCol);
		 }
		 // а дату в первом столбце надо вставить
		 String nextDateStr = makeGeneralDateFormat().format(nextDate);
		 processValueCell(cells, 2, So2ValueCell.DAY_BASE.getCol() + dateCol, nextDateStr, CellStyle.TEXT, null, 90);
	  }
	  cells.merge(0, 0, 1, dateCol + 4);
	  cells.merge(1, So2ValueCell.DAY_BASE.getCol(), 1, dateCol);
   }

   @Override
   protected int processRow(int row, SoView view) {
	  So2EmployeeView soView = (So2EmployeeView) view;
	  Map<String, So2ShiftHoursView> shifts = soView.getShifts();
	  String surnameAndInitials = NameUtils.toSurnameAndInitials(soView.getSurname(), soView.getName(), soView.getPatronumic());
	  processValueCell(cells, row, So2ValueCell.DEPT_NAME.getCol(), soView.getDepartmentName(), CellStyle.TEXT);
	  processValueCell(cells, row, So2ValueCell.EMPLOYEE.getCol(), surnameAndInitials, CellStyle.TEXT);
	  processValueCell(cells, row, So2ValueCell.PERSONAL_NUMBER.getCol(), soView.getPersonalNumber(), CellStyle.TEXT);
	  Date nextDate = null;
	  int dateCol = 0;
	  SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
	  So2ShiftHoursView shift = null;
	  for (nextDate = startDate, dateCol = 0; !nextDate.after(endDate); nextDate = addDays(nextDate, 1), dateCol++) {
		 shift = shifts.get(df.format(nextDate));
		 if (shift != null) {
			String shiftName = shiftHoursDataToName(shift.getAbsenceShiftId(), shift.getShiftHours());
			processValueCell(cells, row, So2ValueCell.DAY_BASE.getCol() + dateCol, shiftName, CellStyle.TEXT);
		 }
	  }
	  return row;
   }

   @Override
   protected void processTable() {
	  super.processTable();
	  processPrintArea();
   }

   private void processPrintArea() {
	  SoReportBeanData data = (SoReportBeanData) dataBean;
	  List<SoView> soViews = data.getSoViews();
	  So2EmployeeView soView = (So2EmployeeView) soViews.get(0);
	  int columnCount = 3;
	  for (Date nextDate = soView.getStartDate(); !nextDate.after(soView.getEndDate()); nextDate = addDays(nextDate, 1)) {
		 columnCount++;
	  }
	  PageSetup pageSetup = getWorksheet().getPageSetup();
	  String allCellsRange = CellsHelper.cellIndexToName(0, 0) + ":" + CellsHelper.cellIndexToName(soViews.size() + headerHeight - 1, columnCount - 1);
	  pageSetup.setPrintArea(allCellsRange);
	  pageSetup.setFitToPagesWide(1);
   }
}
