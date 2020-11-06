package ru.armd.pbk.aspose.report.standard;

import com.aspose.cells.AutoFitterOptions;
import com.aspose.cells.Cells;
import com.aspose.cells.Worksheet;
import ru.armd.pbk.aspose.cells.BaseCellsReportProcessor;
import ru.armd.pbk.aspose.core.IReportNameData;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.exceptions.PBKReportException;
import ru.armd.pbk.views.report.SoView;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Базовый процессор для генерации стандартных отчетов.
 */
public abstract class SoBaseCellsReportProcessor
	extends BaseCellsReportProcessor {

   protected int firstColumn = 0;
   protected int headerHeight = 2;
   protected Cells cells;
   protected Boolean autoFit = true;

   private Worksheet worksheet;


   protected abstract int processRow(int row, SoView view);

   @Override
   protected void processReport() {
	  if (dataBean == null) {
		 return;
	  }
	  worksheet = wb.getWorksheets().get(0);
	  cells = worksheet.getCells();
	  prepareData();
	  processTable();
	  if (autoFit) {
		 setAutoFitOptions();
	  }
   }

   protected void processTable() {
	  SoReportBeanData data = (SoReportBeanData) dataBean;
	  List<SoView> soViews = data.getSoViews();
	  int row = headerHeight;
	  for (int i = 0; i < soViews.size(); i++, row++) {
		 cells.insertRow(row + 1);
		 row = processRow(row, soViews.get(i));
	  }
	  if (row > headerHeight && data.getTotal() != null) {
		 row = processTotal(row, data.getTotal());
	  } else {
		 cells.deleteRow(row);

	  }
	  cells.deleteRow(row);
   }

   protected void setAutoFitOptions() {
	  AutoFitterOptions options = new AutoFitterOptions();
	  options.setAutoFitMergedCells(true);
	  try {
		 worksheet.autoFitRows(options);
	  } catch (Exception e) {
		 throw new PBKReportException("Ошибка при выполнении autoFit", e);
	  }
   }

   @Override
   public File merge(List<File> reports, String mergedReportName, ReportFormat reportFormat) {
	  return reports.get(0);
   }

   @Override
   public IReportNameData getResultReportNameData(List<ReportData> reportDataList) {
	  Date reportDateTime = new Date();
	  SoReportBeanData soReportData = (SoReportBeanData) reportDataList.get(0).getDataBean();
	  return new SoReportSingleNameData(soReportData.getSoNumber(), reportDateTime);
   }

   protected int processTotal(int row, SoView view) {
	  processRow(row, view);
	  processValueCell(cells, row, firstColumn, "Итого", CellStyle.TEXT);
	  return row + 1;
   }

   protected void prepareData() {

   }

   protected Worksheet getWorksheet() {
	  return worksheet;
   }

}
