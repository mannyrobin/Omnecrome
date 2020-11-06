package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So3ValueCell;
import ru.armd.pbk.views.report.So3View;
import ru.armd.pbk.views.report.SoView;

import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateFormat;

/**
 * Генератор печатной формы отчёта "Количество бригад по местам встречи" с заполненными полями отчёта.
 */
public class So3CellsReportProcessor
	extends SoBaseCellsReportProcessor {

   @Override
   protected int processRow(int row, SoView view) {
	  So3View soView = (So3View) view;
	  if (soView.getDate() != null) {
		 processValueCell(cells, row, So3ValueCell.DATE.getCol(), makeGeneralDateFormat().format(soView.getDate()), CellStyle.TEXT);
	  }
	  //processValueCell(cells, row, So3ValueCell.COUNTY.getCol(), soView.getCounty(), CellStyle.TEXT);
	  //processValueCell(cells, row, So3ValueCell.DISTRICT.getCol(), soView.getDistrict(), CellStyle.TEXT);
	  processValueCell(cells, row, So3ValueCell.VENUE.getCol(), soView.getVenue(), CellStyle.TEXT);
	  processValueCell(cells, row, So3ValueCell.BRIGADE_TYPE.getCol(), soView.getBrigadeType(), CellStyle.TEXT);
	  processValueCell(cells, row, So3ValueCell.BRIGADE_COUNT.getCol(), soView.getBrigadeCount(), CellStyle.INT);
	  return row;
   }

}
