package ru.armd.pbk.aspose.plan;

import com.aspose.cells.BorderType;
import com.aspose.cells.CellBorderType;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Worksheet;
import ru.armd.pbk.aspose.cells.BaseCellsReportProcessor;
import ru.armd.pbk.aspose.core.IReportNameData;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.nsi.RouteRateValueCell;
import ru.armd.pbk.exceptions.PBKReportException;
import ru.armd.pbk.utils.date.DateUtils;
import ru.armd.pbk.views.plans.routes.PlanRouteView;
import ru.armd.pbk.views.plans.routes.RouteRatioView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static ru.armd.pbk.utils.AsposeCellsUtils.mergeReportsToFile;
import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateFormat;

/**
 * Created by Yakov Volkov.
 */
public class RouteRateCellReportProcessor
	extends BaseCellsReportProcessor {

   private static final SimpleDateFormat DAY_OF_WEEK_FORMAT = new SimpleDateFormat("E");
   private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
   private RouteRateReportBeanData data;
   private Worksheet worksheet;
   private Cells cells;

   private int columnDateCount;

   @Override
   protected void processReport() {
	  if (dataBean == null) {
		 return;
	  }
	  data = (RouteRateReportBeanData) dataBean;
	  worksheet = wb.getWorksheets().get(0);
	  cells = worksheet.getCells();

	  prepareData();

	  int row = 3;
	  for (PlanRouteView view : data.getPlanRouteViews()) {
		 processValueCellStyled(row, RouteRateValueCell.ROUTE_NUMBER.getCol(), view.getRouteNumber(), CellStyle.TEXT);
		 processValueCellStyled(row, RouteRateValueCell.TRANSPORT_TYPE.getCol(), view.getRouteType(), CellStyle.TEXT);

		 int dateCol = 2;

		 Date endDate = data.getEndDate();
		 Date currentDate = data.getStartDate();
		 while (!currentDate.after(endDate)) {
			String dateToString = DATE_FORMAT.format(currentDate);

			RouteRatioView ratioView = view.getRoutes().get(dateToString);

			if (ratioView != null) {
			   processValueCellStyled(row, dateCol, ratioView.getRouteRatio(), CellStyle.TEXT);
			} else {
			   processValueCellStyled(row, dateCol, "", CellStyle.TEXT);
			}

			dateCol++;
			currentDate = DateUtils.addDays(currentDate, 1);
		 }
		 row++;
	  }


	  cells.merge(0, 0, 1, columnDateCount + 3);
	  setAutoFitOptions();
   }

   private void setAutoFitOptions() {
	  try {
		 worksheet.autoFitColumns();
	  } catch (Exception e) {
		 throw new PBKReportException("Ошибка при выполнении autoFit", e);
	  }
   }

   private void prepareData() {
	  RouteRateReportBeanData data = (RouteRateReportBeanData) dataBean;
	  if (data.getPlanRouteViews().isEmpty()) {
		 return;
	  }

	  columnDateCount = DateUtils.getDaysDifference(data.getStartDate(), data.getEndDate());
	  Date startDate = data.getStartDate();
	  Date endDate = data.getEndDate();

	  Date currentDate = startDate;
	  int currentCol = 2;
	  while (!currentDate.after(endDate)) {
		 cells.insertColumn(currentCol);

		 processValueCellStyled(1, currentCol, makeGeneralDateFormat().format(currentDate), CellStyle.TEXT, 90);
		 processValueCellStyled(2, currentCol, DAY_OF_WEEK_FORMAT.format(currentDate), CellStyle.TEXT);

		 currentCol++;
		 currentDate = DateUtils.addDays(currentDate, 1);
	  }
   }


   @Override
   public File merge(List<File> reports, String mergedReportName, ReportFormat reportFormat) {
	  return mergeReportsToFile(reports, mergedReportName, columnDateCount + 2);
   }

   @Override
   public IReportNameData getResultReportNameData(List<ReportData> reportDataList) {
	  return new IReportNameData() {
		 @Override
		 public String buildName() {
			return "Рейтинги маршрутов ";
		 }
	  };
   }

   private void processValueCellStyled(Integer row, Integer col, Object value, CellStyle cellStyle, Integer angle) {
	  if (angle != null) {
		 processValueCell(cells, row, col, value, cellStyle, null, angle);
	  } else {
		 processValueCell(cells, row, col, value, cellStyle);
	  }
	  Style style = cells.getCellStyle(row, col);
	  style.setVerticalAlignment(TextAlignmentType.CENTER);
	  style.setHorizontalAlignment(TextAlignmentType.CENTER);
	  style.setBorder(BorderType.TOP_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.BOTTOM_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.LEFT_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.RIGHT_BORDER, CellBorderType.THIN, Color.getBlack());
	  cells.get(row, col).setStyle(style);
   }

   protected void processValueCellStyled(Integer row, Integer col, Object value, CellStyle cellStyle) {
	  processValueCellStyled(row, col, value, cellStyle, null);
   }

}
