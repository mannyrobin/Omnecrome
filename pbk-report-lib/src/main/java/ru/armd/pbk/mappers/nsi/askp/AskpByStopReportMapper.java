package ru.armd.pbk.mappers.nsi.askp;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IReportMapper;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;

import java.util.List;

/**
 * Маппер для отчета АСКП поостановочно.
 */
@IsMapper
public interface AskpByStopReportMapper
	extends IReportMapper {

   /**
	* Получения постраничного списка read only view по заданным параметрам фильтрации.
	*
	* @param params   Параметры фильтрации.
	* @param <Views>  Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @return Список view объектов.
	*/
   <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params);
}
