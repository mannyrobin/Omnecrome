package ru.armd.pbk.mappers.report.standard;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.views.report.So13View;

import java.util.List;

/**
 * Маппер стандартного отчёта "Маршруты и выходы".
 */
@IsMapper
public interface So13Mapper
	extends IDomainMapper<BaseDomain> {

   /**
	* Получить список видов билетов.
	*
	* @param params параметры фильтрации
	* @return список видов билетов
	*/
   List<So13View> getTicketTypes(BaseGridViewParams params);
}
