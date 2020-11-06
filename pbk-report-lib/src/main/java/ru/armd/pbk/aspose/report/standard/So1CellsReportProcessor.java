package ru.armd.pbk.aspose.report.standard;

import com.aspose.cells.CellsHelper;
import com.aspose.cells.Color;
import com.aspose.cells.PageSetup;
import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So1ValueCell;
import ru.armd.pbk.utils.NameUtils;
import ru.armd.pbk.views.report.So1EmployeeScheduleView;
import ru.armd.pbk.views.report.So1EmployeeView;
import ru.armd.pbk.views.report.So1ShiftView;
import ru.armd.pbk.views.report.SoView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static ru.armd.pbk.utils.NameUtils.shiftDataToName;
import static ru.armd.pbk.utils.date.DateUtils.addDays;
import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateFormat;

/**
 * Генератор печатной формы отчёта "График работы контролеров по территориальному подразделению"
 * с заполненными полями отчёта.
 */
public class So1CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   private static final int HEADER_HEIGHT_WITH_SUB_HEADER = 5;
   private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

   private int columnCount;
   private So1EmployeeScheduleView prevSoView = null;


   @Override
   protected void prepareData() {
	  headerHeight = 3;
	  SoReportBeanData data = (SoReportBeanData) dataBean;
	  List<SoView> soViews = data.getSoViews();
	  int count = 4;
	  So1EmployeeScheduleView soView = (So1EmployeeScheduleView) soViews.get(0);
	  for (Date nextDate = soView.getDateStart(); !nextDate.after(soView.getDateEnd()); nextDate = addDays(nextDate, 1)) {
		 count++;
	  }
	  this.columnCount = count;
	  processHeader();
   }

   private void processHeader() {
	  SoReportBeanData data = (SoReportBeanData) dataBean;
	  List<SoView> soViews = data.getSoViews();
	  So1EmployeeScheduleView soView = (So1EmployeeScheduleView) soViews.get(0);
	  Date nextDate = null;
	  int dateCol = 0;
	  for (nextDate = soView.getDateStart(), dateCol = 0; !nextDate.after(soView.getDateEnd()); nextDate = addDays(nextDate, 1), dateCol++) {
		 if (dateCol > 0) {    // исключаем первую итерацию (первый столбец с датой, т.к. он уже есть в шаблоне)
			cells.insertColumn(So1ValueCell.DAY_BASE.getCol() + dateCol);
		 }
		 // а дату в первом столбце надо вставить
		 String nextDateStr = makeGeneralDateFormat().format(nextDate);
		 processValueCell(cells, 2, So1ValueCell.DAY_BASE.getCol() + dateCol, nextDateStr, CellStyle.TEXT, null, 90);
	  }
	  cells.merge(0, 0, 1, dateCol + 4);
	  cells.merge(1, So1ValueCell.DAY_BASE.getCol(), 1, dateCol);
   }

   private void createShiftModeTitle(So1EmployeeScheduleView curSoView, int row) {
	  cells.merge(row, 0, 1, columnCount);
	  switch (curSoView.getShiftMode()) {
		 case H_2_2:
			cells.get(row, 0).setValue("Дневные, ночные");
			break;
		 case H_5_6:
			cells.get(row, 0).setValue("Выходные пятница, суббота");
			break;
		 case H_6_7:
			cells.get(row, 0).setValue("Выходные суббота, воскресенье");
			break;
		 case H_7_1:
			cells.get(row, 0).setValue("Выходные воскресенье, понедельник");
			break;
	  }
   }

   private Color getShiftColor(So1ShiftView shift) {
	  if (shift != null) {
		 if (shift.getIsReserve()) {
			return Color.fromArgb(224, 224, 224);
		 }
		 switch (shift.getShiftId().intValue()) {
			case 1:
			   return Color.fromArgb(192, 192, 192);
			case 4:
			   return Color.fromArgb(160, 160, 164);
			default:
			   return Color.fromArgb(255, 255, 255);
		 }
	  }
	  return null;
   }

   @Override
   protected int processRow(int row, SoView view) {
	  So1EmployeeScheduleView soView = (So1EmployeeScheduleView) view;
	  if (prevSoView == null || prevSoView.getShiftMode() != soView.getShiftMode()) {
		 createShiftModeTitle(soView, row);
		 cells.insertRow(++row);
	  }
	  So1EmployeeView employee = soView.getEmployee();
	  List<So1ShiftView> schedule = soView.getSchedule();
	  String surnameAndInitials = NameUtils.toSurnameAndInitials(employee.getSurname(), employee.getName(), employee.getPatronumic());
	  processValueCell(cells, row, So1ValueCell.DEPT_NAME.getCol(), employee.getDepartmentName(), CellStyle.TEXT);
	  processValueCell(cells, row, So1ValueCell.EMPLOYEE.getCol(), surnameAndInitials, CellStyle.TEXT);
	  processValueCell(cells, row, So1ValueCell.PERSONAL_NUMBER.getCol(), employee.getPersonalNumber(), CellStyle.TEXT);
	  Date nextDate = null;
	  int dateCol = 0;
	  for (nextDate = soView.getDateStart(), dateCol = 0; !nextDate.after(soView.getDateEnd()); nextDate = addDays(nextDate, 1), dateCol++) {
		 for (So1ShiftView shift : schedule) {
			if (nextDate.equals(shift.getDate()) || SDF.format(nextDate).equals(shift.getDate())) {
			   String shiftName = shiftDataToName(shift.getShiftId(), shift.getIsReserve());
			   processValueCell(cells, row, So1ValueCell.DAY_BASE.getCol() + dateCol, shiftName, CellStyle.TEXT, getShiftColor(shift));
			   break;
			}
		 }
	  }
	  prevSoView = soView;
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
	  So1EmployeeScheduleView soView = (So1EmployeeScheduleView) soViews.get(0);
	  int columnCount = 4;
	  for (Date nextDate = soView.getDateStart(); !nextDate.after(soView.getDateEnd()); nextDate = addDays(nextDate, 1)) {
		 columnCount++;
	  }
	  PageSetup pageSetup = getWorksheet().getPageSetup();
	  String allCellsRange = CellsHelper.cellIndexToName(0, 0) + ":" + CellsHelper.cellIndexToName(soViews.size() + HEADER_HEIGHT_WITH_SUB_HEADER, columnCount - 1);
	  pageSetup.setPrintArea(allCellsRange);
	  pageSetup.setFitToPagesWide(1);
   }
}
