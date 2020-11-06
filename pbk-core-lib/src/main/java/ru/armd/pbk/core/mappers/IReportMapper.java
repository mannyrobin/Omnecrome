package ru.armd.pbk.core.mappers;

import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.IReportView;

import java.util.List;

/**
 * Базовый маппер для отчетов.
 */
public interface IReportMapper {

   /**
	* Получить список представлений для генерации отчетов.
	* Данный метод принимает параметры фильтрации {@code params}
	*
	* @param <Params> тип параметров
	* @param params   параметры фильтрации
	* @return список представлений
	*/
   <Params extends BaseGridViewParams> List<IReportView> getReportViews(Params params);

}
