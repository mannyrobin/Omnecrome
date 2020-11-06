package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.enums.CellStyle;
import ru.armd.pbk.enums.report.standard.So22ValueCell;
import ru.armd.pbk.views.report.So22View;
import ru.armd.pbk.views.report.SoView;

import static ru.armd.pbk.utils.date.DateUtils.makeGeneralDateFormat;

/**
 * Генератор печатной формы отчёта "Сверка с ГКУ ОП" с заполненными полями отчёта.
 */
public class So22CellsReportProcessor
	extends SoBaseCellsReportProcessor {

	@Override
	protected int processRow(int row, SoView view) {
		So22View soView = (So22View) view;
		int count = 0;
		for (String mgtHead : soView.getMgtHead().split("\n")) {
			if (count != 0) {
				row++;
				cells.insertRow(row);
			}
			if (soView.getWorkDateId() != null) {
				String workDateId = makeGeneralDateFormat().format(soView.getWorkDateId());
				processValueCell(cells, row, So22ValueCell.WORK_DATE_ID.getCol(), workDateId, CellStyle.TEXT);
			}
			processValueCell(cells, row, So22ValueCell.DEPT_NAME.getCol(), soView.getDeptName(), CellStyle.TEXT);
			processValueCell(cells, row, So22ValueCell.GKUOP_NAME.getCol(), soView.getGkuopName(), CellStyle.TEXT);
			processValueCell(cells, row, So22ValueCell.SHIFT_NAME.getCol(), soView.getShiftName(), CellStyle.TEXT);
			processValueCell(cells, row, So22ValueCell.MGT_HEAD.getCol(), mgtHead, CellStyle.TEXT);
			processValueCell(cells, row, So22ValueCell.GKUOP_HEAD.getCol(), soView.getGkuopHead(), CellStyle.TEXT);
			count++;
		}
		return row;
	}
}
