package ru.armd.pbk.mappers.report.standard;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridView;

import java.util.Map;

/**
 * Базовый маппер стандартных отчетов.
 *
 * @param <Domain> домен
 */
public interface SoMapper<Domain extends BaseDomain>
	extends IDomainMapper<BaseDomain> {

   /**
	* Получение данных для записи "Итого" по параметрам.
	*
	* @param <View> тип представления.
	* @param params параметры
	* @return данные для записи "Итого"
	*/
   <View extends BaseGridView> View getGridViewTotal(Map<String, Object> params);
}
