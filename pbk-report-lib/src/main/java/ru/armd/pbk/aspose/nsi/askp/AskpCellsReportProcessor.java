package ru.armd.pbk.aspose.nsi.askp;

import com.aspose.cells.AutoFitterOptions;
import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.CellBorderType;
import com.aspose.cells.Cells;
import com.aspose.cells.CellsHelper;
import com.aspose.cells.Color;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Worksheet;
import org.apache.log4j.Logger;
import ru.armd.pbk.aspose.cells.BaseCellsReportProcessor;
import ru.armd.pbk.aspose.core.IReportNameData;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.nsi.NsiExportCellsReportProcessor;
import ru.armd.pbk.aspose.nsi.NsiReportBeanData;
import ru.armd.pbk.core.views.IReportView;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Процессор для построения отчетов АСКП.
 */
public class AskpCellsReportProcessor
	extends BaseCellsReportProcessor {

   private static final Logger LOGGER = Logger.getLogger(NsiExportCellsReportProcessor.class);

   private static final String CELL_CAPTION = "A1";
   private static final String START_CELL = "A4";

   private static final int ZERO_ANGLE = 0;

   private NsiReportBeanData data;
   private Worksheet worksheet;
   private Cells cells;

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
	  setAutoFitOptions();
   }

   private void prepareData() {

   }

   private void processTable() {
	  int[] startCell = CellsHelper.cellNameToIndex(START_CELL);
	  processTableHeaders(startCell);
	  processTableCells(startCell);
   }

   private void processTableHeaders(int[] startCell) {
	  int i = startCell[0];
	  int j = startCell[1];
	  if (data.getData() != null && data.getData().size() > 0) {
		 for (String header : data.getData().get(0).getReportHeaders()) {
			precessTableHeader(i, j, header);
			j++;
		 }
	  }
   }

   private void processTableCells(int[] startCell) {
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

   private void precessTableCell(int i, int j, Object value) {
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

   private void precessTableHeader(int i, int j, Object value) {
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

   private void setAutoFitOptions() {
	  AutoFitterOptions options = new AutoFitterOptions();
	  options.setAutoFitMergedCells(true);
	  try {
		 worksheet.autoFitRows(options);
	  } catch (Exception e) {
		 LOGGER.error("Ошибка генерации отчета.", e);
	  }
   }

   private void processHeader() {
	  int[] taskNumberIndex = CellsHelper.cellNameToIndex(CELL_CAPTION);
	  processHeaderCell(taskNumberIndex[0], taskNumberIndex[1], data.getCaption(), true, false);
	  if (data.getData().size() > 0) {
		 int sizeList = data.getData().get(0).getReportList().size();
		 cells.merge(0, 0, 2, sizeList);
	  }
   }

   private void processHeaderCell(int rowNum, int colNum, Object cellData, boolean isBold, boolean isDate) {
	  Cell cell = cells.get(rowNum, colNum);
	  Style style = cell.getStyle();
	  style.getFont().setBold(isBold);
	  style.setRotationAngle(ZERO_ANGLE);
	  style.setTextWrapped(true);
	  style.setVerticalAlignment(TextAlignmentType.CENTER);
	  style.setHorizontalAlignment(TextAlignmentType.CENTER);
	  cell.setValue(cellData);
	  cell.setStyle(style);
   }

   @Override
   public File merge(List<File> reports, String mergedReportName, ReportFormat reportFormat) {
	  return reports.get(0);
   }

   @Override
   public IReportNameData getResultReportNameData(List<ReportData> reportData) {
	  Date reportDateTime = new Date();
	  return new AskpExportReportNameData(reportDateTime, ((NsiReportBeanData) dataBean).getCaption());
   }

}
