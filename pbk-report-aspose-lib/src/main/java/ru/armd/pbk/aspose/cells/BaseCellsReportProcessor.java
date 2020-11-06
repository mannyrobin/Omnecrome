package ru.armd.pbk.aspose.cells;

import com.aspose.cells.BackgroundType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.CellsHelper;
import com.aspose.cells.Color;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import org.apache.log4j.Logger;
import ru.armd.pbk.aspose.core.AbstractReportProcessor;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.enums.CellStyle;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Базовый класс-обработчик отчетов форматов xls, xlsx.
 */
public abstract class BaseCellsReportProcessor
	extends AbstractReportProcessor {
   protected static final String WS = " ";

   private static final Logger logger = Logger.getLogger(BaseCellsReportProcessor.class);

   private static final int ZERO_ANGLE = 0;

   protected Workbook wb = null;
   protected StringBuilder sb = new StringBuilder();

   @Override
   public Map<ReportFormat, File> processTemplate(String filename, ReportFormat... formats)
	   throws Exception {
	  long t1 = 0L;
	  if (TRACE_PROCESS_TEMPLATE) {
		 t1 = System.currentTimeMillis();
	  }

	  if (reportType.getSkipTemplate()) {
		 wb = new Workbook();
	  } else {
		 wb = getWorkbook();
	  }
	  processReport();
	  List<ReportFormat> formatsList = new ArrayList<>();
	  if (formats == null || formats.length == 0) {
		 formatsList.add(ReportFormat.MSWORD);
	  } else {
		 for (ReportFormat format : formats) {
			if (!formatsList.contains(format)) {
			   formatsList.add(format);
			}
		 }
	  }
	  Map<ReportFormat, File> result = saveDocument(formatsList, filename);
	  if (TRACE_PROCESS_TEMPLATE) {
		 long t2 = System.currentTimeMillis();

		 logger.debug(System.lineSeparator() + "\tReady:");
		 if (result != null) {
			for (Entry<ReportFormat, File> entry : result.entrySet()) {
			   logger.debug(String.format("%s\t%s: %s", System.lineSeparator(), entry.getKey().getFileExtension(false).toUpperCase(), entry.getValue().getAbsolutePath()));
			}
		 }
		 logger.debug(String.format("%d ms", t2 - t1));
	  }
	  return result;
   }

   @Override
   public void cleanup() {
	  wb = null;
   }

   protected abstract void processReport();

   protected void processValueCell(Cells cells, String position, Object value, CellStyle style) {
	  int[] cellCoords = CellsHelper.cellNameToIndex(position);
	  Cell cell = cells.get(cellCoords[0], cellCoords[1]);
	  processValueCell(cell, value, style, null, null, TextAlignmentType.CENTER);
   }

   protected void processValueCell(Cells cells, String position, Object value, CellStyle style, int textAlignmentType) {
	  int[] cellCoords = CellsHelper.cellNameToIndex(position);
	  Cell cell = cells.get(cellCoords[0], cellCoords[1]);
	  processValueCell(cell, value, style, null, null, textAlignmentType);
   }

   protected void processValueCell(Cells cells, Integer row, Integer col, Object value, CellStyle style) {
	  Cell cell = cells.get(row, col);
	  processValueCell(cell, value, style, null, null, TextAlignmentType.CENTER);
   }

   protected void processValueCell(Cells cells, Integer row, Integer col, Object value, CellStyle style, Color color) {
	  Cell cell = cells.get(row, col);
	  processValueCell(cell, value, style, color, null, TextAlignmentType.CENTER);
   }

   protected void processValueCell(Cells cells, Integer row, Integer col, Object value, CellStyle style, Color color, Integer angle) {
	  Cell cell = cells.get(row, col);
	  processValueCell(cell, value, style, color, angle, TextAlignmentType.CENTER);
   }

   private void processValueCell(Cell cell, Object value, CellStyle style, Color color, Integer angle, int textAlignmentType) {
	  cell.setValue(value);
	  Style cellsStyle = cell.getStyle();
	  cellsStyle.setRotationAngle(angle == null ? ZERO_ANGLE : angle);
	  cellsStyle.setTextWrapped(true);
	  cellsStyle.setHorizontalAlignment(textAlignmentType);
	  cellsStyle.setNumber(style.getStyleNumber());
	  if (color != null) {
		 cellsStyle.setPattern(BackgroundType.SOLID);
		 cellsStyle.setForegroundColor(color);
	  }
	  cell.setStyle(cellsStyle);
   }

   protected Workbook getWorkbook()
	   throws Exception {
	  String dataDir = rootDirectory.getAbsolutePath();
	  return new Workbook(dataDir + File.separator + templateName);
   }

   private Map<ReportFormat, File> saveDocument(List<ReportFormat> formats, String filename) {
	  if (formats == null) {
		 return null;
	  }

	  Map<ReportFormat, File> result = new HashMap<>();
	  resultFiles = new HashMap<>();
	  for (ReportFormat format : formats) {
		 try {
			File tempFile = File.createTempFile(filename + "_", format.getFileExtension(true), rootDirectory);
			String absolutePath = tempFile.getAbsolutePath();
			wb.save(absolutePath);

			result.put(format, tempFile);
			resultFiles.put(format, absolutePath);
		 } catch (Throwable e) {
			logger.error(String.format("Cannot save report in %s format", format.getFileExtension(true)), e);
		 }
	  }
	  return result;
   }
}
