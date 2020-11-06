package ru.armd.pbk.aspose.nsi.bsos;

import com.aspose.cells.*;
import org.apache.commons.io.FilenameUtils;
import ru.armd.pbk.aspose.cells.BaseCellsReportProcessor;
import ru.armd.pbk.aspose.core.IReportNameData;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.bsos.BsoValueCell;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.exceptions.PBKReportException;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ru.armd.pbk.utils.AsposeCellsUtils.mergeReportsToFile;

/**
 * Генератор печатной формы задания с заполненными полями задания.
 */
public class BsoCellsReportProcessor
	extends BaseCellsReportProcessor {

   private static final int BSO_REPORT_WIDTH = 15; //ширина печатной формы XLS по акту об изъятии проездного документа в столбцах
   private static final int BSO_REPORT_COUNT_ON_PAGE = 2; //количество отчетов на одной странице
   private static final int MAX_COUNT_BSO_IN_FILE_NAME = 5;

   private BsoReportBeanData data;
   private Worksheet worksheet;
   private Cells cells;

   @Override
   protected void processReport() {
	  if (dataBean == null) {
		 return;
	  }
	  data = (BsoReportBeanData) dataBean;
	  worksheet = wb.getWorksheets().get(0);
	  cells = worksheet.getCells();

	  processBsoCells();
	  setAutoFitOptions();
   }

   private void processBsoCells() {
	  SimpleDateFormat df = new SimpleDateFormat("YYYY");
	  processValueCell(cells, BsoValueCell.ACT_NUMBER.getPos(), data.getActNumber(), CellStyle.TEXT);
	  processValueCell(cells, BsoValueCell.FIO.getPos(), getFio(data), CellStyle.TEXT);
	  processValueCell(cells, BsoValueCell.PERSONAL_NUMBER.getPos(), data.getPersonalNumber(), CellStyle.TEXT);
	  processValueCell(cells, BsoValueCell.YEAR.getPos(), df.format(new Date()) + "г.", CellStyle.TEXT, TextAlignmentType.LEFT);
	  addQrCode();
   }

   private void addQrCode() {
   		BsoQrGenerator qrGenerator = new BsoQrGenerator();
   		try {
			byte[] picture = qrGenerator.generate(data.getActNumber() + "\n" + getFio(data) + "\n" + data.getPersonalNumber());
			PictureCollection collection = worksheet.getPictures();
			collection.get(1).setData(picture);
		} catch (Exception e) {
	  		throw new PBKReportException("Ошибка при генерации qrCode для БСО: ", e);
   		}
   }

   private String getFio(BsoReportBeanData bso) {
	  String result = "";
	  if (bso.getSurname() != null) {
		 result += bso.getSurname();
	  }
	  if (bso.getName() != null && !bso.getName().isEmpty()) {
		 result += " " + bso.getName().substring(0, 1) + ".";
		 result.trim();
	  }
	  if (bso.getPatronumic() != null && !bso.getPatronumic().isEmpty()) {
		 result += " " + bso.getPatronumic().substring(0, 1) + ".";
		 result.trim();
	  }
	  return result;
   }

   private void setAutoFitOptions() {
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
	  File result = null;
	  try {
		 File file = mergeReportsToFile(reports, mergedReportName, BSO_REPORT_WIDTH, BSO_REPORT_COUNT_ON_PAGE);
		 Workbook book = new Workbook(file.getAbsolutePath());
		 result = File.createTempFile(FilenameUtils.removeExtension(file.getName()) + "_", reportFormat.getFileExtension(true), rootDirectory);
		 book.getWorksheets().get(0).getPageSetup().setFitToPagesTall(BSO_REPORT_COUNT_ON_PAGE);
		 book.save(result.getAbsolutePath());
	  } catch (Exception e) {
		 throw new PBKException("Ошибка генерации отчета", e);
	  }
	  return result;
   }

   @Override
   public IReportNameData getResultReportNameData(List<ReportData> reportDataList) {
	  Date reportDateTime = new Date();
	  if (reportDataList.size() == 1) {
		 String bsoNumber = ((BsoReportBeanData) reportDataList.get(0).getDataBean()).getActNumber();
		 return new BsoReportSingleNameData(reportDateTime, bsoNumber);
	  } else {
		 List<String> bsoNumbers = new ArrayList<>();
		 for (int i = 0; i < reportDataList.size() && i < MAX_COUNT_BSO_IN_FILE_NAME; i++) {
			bsoNumbers.add(((BsoReportBeanData) reportDataList.get(i).getDataBean()).getActNumber());
		 }
		 return new BsoReportMultipleNameData(reportDateTime, bsoNumbers);
	  }
   }
}
