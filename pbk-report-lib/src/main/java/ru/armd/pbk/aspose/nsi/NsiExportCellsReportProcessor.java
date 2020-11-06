package ru.armd.pbk.aspose.nsi;

import com.aspose.cells.AutoFitterOptions;
import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.CellBorderType;
import com.aspose.cells.Cells;
import com.aspose.cells.CellsHelper;
import com.aspose.cells.Color;
import com.aspose.cells.ImageOrPrintOptions;
import com.aspose.cells.PageOrientationType;
import com.aspose.cells.SheetRender;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Worksheet;
import org.apache.log4j.Logger;
import ru.armd.pbk.aspose.cells.BaseCellsReportProcessor;
import ru.armd.pbk.aspose.core.IReportNameData;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.core.views.IReportView;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Процессор для генерации отчетов excel НСИ.
 */
public class NsiExportCellsReportProcessor
	extends BaseCellsReportProcessor {

   private static final Logger LOGGER = Logger.getLogger(NsiExportCellsReportProcessor.class);

   private static final String CELL_CAPTION = "A1";
   private static final String START_CELL = "A4";

   private static final int ZERO_ANGLE = 0;

   protected NsiReportBeanData data;
   protected Worksheet worksheet;
   protected Cells cells;

   @Override
   protected void processReport() {
	  if (dataBean == null) {
		 return;
	  }
	  data = (NsiReportBeanData) dataBean;
	  worksheet = wb.getWorksheets().get(0);
	  cells = worksheet.getCells();
	  prepareData();
	  processHeader();
	  processTable();
	  //calcColumnsWidth();
	  setAutoFitOptions();
   }

   protected void prepareData() {
   }

   protected void calcColumnsWidth() {
	  try {
		 ImageOrPrintOptions options = new ImageOrPrintOptions();
		 SheetRender render = new SheetRender(worksheet, options);
		 float[] size = render.getPageSize(0);
		 int sizeList = data.getData().get(0).getReportList().size();
		 double width = size[0] / sizeList;
		 for (int i = 0; i < sizeList; i++) {
			cells.setColumnWidthPixel(i, (int) width);
		 }

	  } catch (Exception e) {
		 LOGGER.error("Ошибка в расчете ширины колонок.", e);
	  }
   }

   protected void processTable() {
	  int[] startCell = CellsHelper.cellNameToIndex(START_CELL);
	  processTableHeaders(startCell);
	  processTableCells(startCell);
   }

   protected void processTableHeaders(int[] startCell) {
	  int i = startCell[0];
	  int j = startCell[1];
	  if (data.getData() != null && data.getData().size() > 0) {
		 for (String header : data.getData().get(0).getReportHeaders()) {
			precessTableHeader(i, j, header);
			j++;
		 }
	  }
   }

   protected void processTableCells(int[] startCell) {
	  int i = startCell[0] + 1;
	  if (data.getData() != null && data.getData().size() > 0) {
		 for (IReportView view : data.getData()) {
			int j = startCell[1];
			for (Object value : view.getReportList()) {
			   precessTableCell(i, j, value);
			   j++;
			}
			i++;
		 }
	  }
   }

   protected void precessTableCell(int i, int j, Object value) {
	  Cell cell = cells.get(i, j);
	  cell.setValue(value);
	  Style style = cell.getStyle();
	  style.setRotationAngle(ZERO_ANGLE);
	  style.setTextWrapped(true);
	  style.setVerticalAlignment(TextAlignmentType.CENTER);
	  style.setHorizontalAlignment(TextAlignmentType.CENTER);
	  style.setBorder(BorderType.TOP_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.BOTTOM_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.LEFT_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.RIGHT_BORDER, CellBorderType.THIN, Color.getBlack());
	  cell.setStyle(style);
   }

   protected void precessTableHeader(int i, int j, Object value) {
	  Cell cell = cells.get(i, j);
	  cell.setValue(value);
	  Style style = cell.getStyle();
	  style.setRotationAngle(ZERO_ANGLE);
	  style.setTextWrapped(true);
	  style.setVerticalAlignment(TextAlignmentType.CENTER);
	  style.setHorizontalAlignment(TextAlignmentType.CENTER);
	  style.getFont().setBold(true);
	  style.setBorder(BorderType.TOP_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.BOTTOM_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.LEFT_BORDER, CellBorderType.THIN, Color.getBlack());
	  style.setBorder(BorderType.RIGHT_BORDER, CellBorderType.THIN, Color.getBlack());
	  cell.setStyle(style);
   }

   protected void setAutoFitOptions() {
	  AutoFitterOptions options = new AutoFitterOptions();
	  options.setAutoFitMergedCells(true);
	  try {
		 worksheet.autoFitColumns(options);
		 worksheet.getPageSetup().setFitToPagesWide(1);
		 worksheet.getPageSetup().setFitToPagesTall(0);
		 worksheet.getPageSetup().setOrientation(PageOrientationType.LANDSCAPE);
	  } catch (Exception e) {
		 LOGGER.error("Ошибка генерации отчета.", e);
	  }
   }

   protected void processHeader() {
	  int[] taskNumberIndex = CellsHelper.cellNameToIndex(CELL_CAPTION);
	  processHeaderCell(taskNumberIndex[0], taskNumberIndex[1], "Справочник " + data.getCaption(), true, false);
   }

   protected void processHeaderCell(int rowNum, int colNum, Object cellData, boolean isBold, boolean isDate) {
	  Cell cell = cells.get(rowNum, colNum);
	  Style style = cell.getStyle();
	  style.getFont().setBold(isBold);
	  style.setRotationAngle(ZERO_ANGLE);
	  style.setTextWrapped(true);
	  style.setVerticalAlignment(TextAlignmentType.CENTER);
	  style.setHorizontalAlignment(TextAlignmentType.CENTER);
	  cell.setValue(cellData);
	  cell.setStyle(style);
	  if (!data.getData().isEmpty()) {
		 cells.merge(rowNum, colNum, 1, data.getData().get(0).getReportHeaders().size());
	  }
   }

   @Override
   public File merge(List<File> reports, String mergedReportName, ReportFormat reportFormat) {
	  return reports.get(0);
   }

   @Override
   public IReportNameData getResultReportNameData(List<ReportData> reportData) {
	  Date reportDateTime = new Date();
	  return new NsiExportReportNameData(reportDateTime, ((NsiReportBeanData) dataBean).getCaption());
   }
}
