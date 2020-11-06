package ru.armd.pbk.aspose.report.standard;

import com.aspose.words.ControlChar;
import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So19ValueCell;
import ru.armd.pbk.views.report.So19View;
import ru.armd.pbk.views.report.SoView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Генератор печатной формы отчёта "Количество совместных бригад с ГКУ ОП"
 * с заполненными полями отчёта.
 */
public class So19CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

   @Override
   protected void processReport() {
	  headerHeight = 3;
	  autoFit = false;
	  SoReportBeanData data = (SoReportBeanData) dataBean;
	  List<SoView> soViews = data.getSoViews();
	  if (soViews.size() > 0) {
		 data.setTotal(soViews.remove(soViews.size() - 1));
	  }

	  super.processReport();
   }

	@Override
	protected int processRow(int row, SoView view) {
		So19View soView = (So19View) view;
		processValueCell(cells, row, So19ValueCell.NN.getCol(), row + 1 - headerHeight, CellStyle.INT);
		processValueCell(cells, row, So19ValueCell.WORK_DATE.getCol(), soView.getWorkDate() != null ? sdf.format(soView.getWorkDate()) : "", CellStyle.TEXT);
		processValueCell(cells, row, So19ValueCell.SHIFT.getCol(), soView.getShiftName(), CellStyle.TEXT);
		processValueCell(cells, row, So19ValueCell.GKU_PODR_NAME.getCol(), soView.getGkuPodrName(), CellStyle.TEXT);
		String mgtEmployees = soView.getMgtEmployees();
		if (mgtEmployees != null) {
			mgtEmployees = mgtEmployees.replaceAll(", ", "\n");
		}
		processValueCell(cells, row, So19ValueCell.MGT_EMPLOYEES.getCol(), mgtEmployees, CellStyle.TEXT);
		String gkuEmployees = soView.getGkuEmployees();
		if (gkuEmployees != null) {
			gkuEmployees = gkuEmployees.replaceAll(", ", "\n");
		}
		processValueCell(cells, row, So19ValueCell.GKU_EMPLOYEES.getCol(), gkuEmployees, CellStyle.TEXT);
		processValueCell(cells, row, So19ValueCell.VENUE.getCol(), soView.getVenue(), CellStyle.TEXT);
		processValueCell(cells, row, So19ValueCell.COUNTY.getCol(), soView.getCounty(), CellStyle.TEXT);
		processValueCell(cells, row, So19ValueCell.ROUTES.getCol(), soView.getRoutes(), CellStyle.TEXT);
		processValueCell(cells, row, So19ValueCell.BRKIND0.getCol(), soView.getBrKind0(), CellStyle.TEXT);
		processValueCell(cells, row, So19ValueCell.BRKIND11.getCol(), soView.getBrKind11(), CellStyle.TEXT);
		processValueCell(cells, row, So19ValueCell.BRKIND12.getCol(), soView.getBrKind12(), CellStyle.TEXT);
		processValueCell(cells, row, So19ValueCell.BRKIND13.getCol(), soView.getBrKind13(), CellStyle.TEXT);
		processValueCell(cells, row, So19ValueCell.BRKIND21.getCol(), soView.getBrKind21(), CellStyle.TEXT);
		processValueCell(cells, row, So19ValueCell.BRKIND22.getCol(), soView.getBrKind22(), CellStyle.TEXT);
		processValueCell(cells, row, So19ValueCell.BRKIND23.getCol(), soView.getBrKind23(), CellStyle.TEXT);
		processValueCell(cells, row, So19ValueCell.DOPMGT.getCol(), soView.getDopMGT(), CellStyle.INT);
		processValueCell(cells, row, So19ValueCell.DOPGKU.getCol(), soView.getDopGKU(), CellStyle.INT);
		processValueCell(cells, row, So19ValueCell.DOPMGTNOGKU.getCol(), soView.getDopMGTnoGKU(), CellStyle.INT);
		processValueCell(cells, row, So19ValueCell.MGTCOUNTINDAY.getCol(), soView.getMgtCountInDay(), CellStyle.INT);
		processValueCell(cells, row, So19ValueCell.BRCOMMON.getCol(), soView.getBrCommon(), CellStyle.INT);
		processValueCell(cells, row, So19ValueCell.MGTCOUNTINCOMMON.getCol(), soView.getMgtCountInCommonBr(), CellStyle.INT);
		processValueCell(cells, row, So19ValueCell.GKUCOUNTINCOMMON.getCol(), soView.getGkuCountInCommonBr(), CellStyle.INT);
	  return row;
   }

}
