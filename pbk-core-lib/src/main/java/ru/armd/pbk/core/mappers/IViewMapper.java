package ru.armd.pbk.core.mappers;

import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.ISelectItem;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс маппера с общими методами получения данных для гридов и списков.
 */
public interface IViewMapper
	extends IMapper {

   /**
	* Получения постраничного списка read only view по заданным параметрам фильтрации.
	*
	* @param params   Параметры фильтрации.
	* @param <Views>  Тип view объектов
	* @param <Params> Тип параметров фильтрации.
	* @return Список view объектов.
	*/
   <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params);

   /**
	* Получение списка ISelectItem для отображения в комбобоксах.
	*
	* @param <SelectItem> Тип ISelectItem объектов
	* @param params       Параметры фильтрации.
	* @return Список ISelectItem объектов.
	*/
   <SelectItem extends ISelectItem> List<SelectItem> getSelectItems(Map<String, Object> params);

}
