package ru.armd.pbk.mappers.nsi.askp;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;

import java.util.List;

/**
 * Маппер для работы с агрегированными по часам
 * данными АСКП.
 */
@IsMapper
public interface AskpChecksByHoursMapper
	extends IDomainMapper<BaseDomain> {
   /**
	* Получения постраничного списка read only view по заданным параметрам фильтрации.
	*
	* @param params   Параметры фильтрации.
	* @param <Views>  Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @return Список view объектов.
	*/
   <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getDetailedViews(Params params);
}
