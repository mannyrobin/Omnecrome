package ru.armd.pbk.aspose.utils;

import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import ru.armd.pbk.utils.StringUtils;

/**
 * Утилит для генерации отчетов.
 */
public class ReportUtils {

   public static final int MAX_ROW_HEIGHT = 546; //pixels
   public static final double POINT_TO_PIXEL = 16.0 / 12.0;


   public static void setCellWidthByContent(Cells cells, int col, int minWidth, String content) {
	  int len = StringUtils.isBlank(content) ? 0 : content.length();
	  if (len < minWidth) {
		 cells.setColumnWidth(col, minWidth);
	  } else {
		 cells.setColumnWidth(col, len);
	  }
   }

   public static Color getNotWorkDayColor() {
	  return Color.fromArgb(204, 255, 255);
   }

   /**
	* Получить высоту (в пикаселях) заданной ячейки в котором нужно помещать текст.
	*
	* @param cells        aspose.cells
	* @param row          номер сторки ячейки
	* @param col          номер колонки ячейки
	* @param linesSpacing межстроковое расстояние (в пикселах).
	* @param text         тект который нужно пемещать в ячейку.
	* @return нужную высоту (в пиекселях) ячейки для текста.
	*/
   public static int getRowHeightInPixels(Cells cells, int row, int col, int linesSpacing, String text) {
	  if (text == null || text.isEmpty()) {
		 return 0;
	  }
	  String[] lines = text.split(StringUtils.CRLF);

	  // количество пустых строк
	  int h = lines.length;
	  int fontSize = cells.get(row, col).getStyle().getFont().getSize();
	  int colWidth = cells.getColumnWidthPixel(col);
	  // получить количество строк на которое разбывает текущая строка
	  for (String l : lines) {
		 h += l.length() * fontSize * POINT_TO_PIXEL * 0.5 / colWidth;
	  }
	  // получить высоту в пикслях
	  h = (int) (h * (fontSize * POINT_TO_PIXEL + linesSpacing));
	  return h;
   }

   /**
	* Получить нужное количество строк (excel) для помещения заданного текста в ячейку.
	*
	* @param cells        aspose.cells
	* @param row          номер сторки ячейки
	* @param col          номер колонки ячейки
	* @param linesSpacing межстроковое расстояние (в пикселах).
	* @param text         тект который нужно пемещать в ячейку.
	* @return нужную высоту (в пиекселях) ячейки для текста.
	*/
   public static int getRowsCountNeed(Cells cells, int row, int col, int linesSpacing, String text) {
	  return getRowHeightInPixels(cells, row, col, linesSpacing, text) / MAX_ROW_HEIGHT + 1;
   }
}
