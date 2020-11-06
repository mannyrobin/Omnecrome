package ru.armd.pbk.aspose.task;

import com.aspose.cells.PageOrientationType;
import org.apache.log4j.Logger;
import ru.armd.pbk.aspose.nsi.NsiExportCellsReportProcessor;

/**
 * Процессор для генерации отчета справочника задний.
 */
public class TaskExportCellsReportProcessor
	extends NsiExportCellsReportProcessor {


   private static final Logger LOGGER = Logger.getLogger(TaskExportCellsReportProcessor.class);

   private final int WIDTH_ONE_PART_COULMN = 115;

   @Override
   protected void prepareData() {
	  worksheet.getPageSetup().setOrientation(PageOrientationType.LANDSCAPE);
	  worksheet.getPageSetup().setFitToPagesWide(1);
   }

   @Override
   protected void calcColumnsWidth() {
	  try {
		 cells.setColumnWidthPixel(0, WIDTH_ONE_PART_COULMN);
		 cells.setColumnWidthPixel(1, 3 * WIDTH_ONE_PART_COULMN);
		 cells.setColumnWidthPixel(2, WIDTH_ONE_PART_COULMN);
		 cells.setColumnWidthPixel(3, 3 * WIDTH_ONE_PART_COULMN);
		 cells.setColumnWidthPixel(4, WIDTH_ONE_PART_COULMN);
		 cells.setColumnWidthPixel(5, 2 * WIDTH_ONE_PART_COULMN);
		 cells.setColumnWidthPixel(6, WIDTH_ONE_PART_COULMN);
		 cells.setColumnWidthPixel(7, WIDTH_ONE_PART_COULMN);
	  } catch (Exception e) {
		 LOGGER.error("Ошибка в расчете ширины колонок.", e);
	  }
   }
}
