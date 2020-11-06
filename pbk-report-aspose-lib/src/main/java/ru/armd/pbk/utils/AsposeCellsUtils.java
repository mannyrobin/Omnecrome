package ru.armd.pbk.utils;

import com.aspose.cells.*;
import com.aspose.cells.Range;
import ru.armd.pbk.exceptions.PBKAsposeReportException;

import java.io.File;
import java.util.List;

/**
 * Класс-утилита, содержащий методы для обработки XLS(X) документов.
 */
public class AsposeCellsUtils {

   /**
	* Объединить сгенерированные XLSX файлы в один новый (добавляя друг за другом справа).
	*
	* @param reportFiles    файлы для объединения
	* @param resultFileName имя файла итогового отчёта
	* @param reportWidth    ширина объединяемых репортов в столбцах (все репорты - одинаковой ширины)
	* @return выходной файл
	*/
   public static File mergeReportsToFile(List<File> reportFiles, String resultFileName, int reportWidth) {
	  return mergeReportsToFile(reportFiles, resultFileName, reportWidth, null);
   }

   /**
	* Объединить сгенерированные XLSX файлы в один новый (добавляя друг за другом справа).
	*
	* @param reportFiles    файлы для объединения
	* @param resultFileName имя файла итогового отчёта
	* @param reportWidth    ширина объединяемых репортов в столбцах (все репорты - одинаковой ширины)
	* @param countOnPage    количество страниц на одном листе
	* @return выходной файл
	*/
   public static File mergeReportsToFile(List<File> reportFiles, String resultFileName, int reportWidth, Integer countOnPage) {
	  Workbook resWb = null;
	  Worksheet wsResult;
	  String absolutePath = reportFiles.get(0).getAbsolutePath();
	  String resultReportPath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator) + 1) + resultFileName + ".xlsx";
	  try {
		 resWb = new Workbook(reportFiles.get(0).getPath());
		 wsResult = resWb.getWorksheets().get(0);
		 if (countOnPage != null && reportFiles != null && reportFiles.size() > 0) {
			int count = reportFiles.size() / countOnPage;
			if (reportFiles.size() % countOnPage != 0) {
			   count++;
			}
			wsResult.getPageSetup().setFitToPagesWide(count);
		 }
		 for (int i = 1; i < reportFiles.size(); i++) {
			mergeNextReport(wsResult, reportFiles.get(i), i, reportWidth);
		 }
		  for(int j=wsResult.getShapes().getCount()-1; j>=0; j--)
		  {
			  Shape shp = wsResult.getShapes().get(j);

			  if (shp instanceof RectangleShape)
				  wsResult.getShapes().remove(shp);
		  }
		 resWb.save(resultReportPath);
		 return new File(resultReportPath);
	  } catch (Exception e) {
		 throw new PBKAsposeReportException("Не удалось объединить сгенерированные XLSX файлы в один.", e);
	  }
   }

   private static void mergeNextReport(Worksheet wsResult, File reportFile, int reportIndex, int reportWidth)
	   throws Exception {
	  Workbook wb;
	  Worksheet worksheet;
	  wb = new Workbook(reportFile.getPath());
	  worksheet = wb.getWorksheets().get(0);
	  Range sourceRange = worksheet.getCells().getMaxDisplayRange();
	  int mergePos = reportWidth * reportIndex;
	  int colCount = reportWidth;
	  Range destRange = wsResult.getCells().createRange(sourceRange.getFirstRow(), mergePos, sourceRange.getRowCount(), colCount);
	  PasteOptions options = new PasteOptions();
	  options.setPasteType(PasteType.ALL);
	  destRange.copy(sourceRange, options);
	  options.setPasteType(PasteType.COLUMN_WIDTHS);
	  destRange.copy(sourceRange, options);
   }
}
