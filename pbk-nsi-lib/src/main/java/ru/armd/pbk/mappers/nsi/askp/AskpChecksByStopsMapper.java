package ru.armd.pbk.mappers.nsi.askp;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;

import java.util.Date;
import java.util.List;

/**
 * Маппер для работы с агрегированными данными
 * АСКП по остановкам.
 */
@IsMapper
public interface AskpChecksByStopsMapper
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

   /**
	* Обновить количество проверок билетов в интервале.
	*
	* @param from начало.
	* @param to   конец.
	* @param d    количество.
	*/
   void updateAskpCounts(@Param("from") Date from, @Param("to") Date to, @Param("d") Integer d);

   /**
	* Обновить количество проверок платных билетов в интервале.
	*
	* @param from начало.
	* @param to   конец.
	* @param d    количество.
	*/
   void updateAskpCountsPaid(@Param("from") Date from, @Param("to") Date to, @Param("d") Integer d);
}
