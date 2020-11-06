package ru.armd.pbk.mappers.nsi;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.StopIntervalStat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Маппер статистических данные для Интервалов.
 */
@IsMapper
public interface StopIntervalsStatsMapper
	extends IDomainMapper<StopIntervalStat> {

   /**
	* Вставка статистические данные на дату.
	*
	* @param workDate дата
	* @return
	*/
   int insertStatsByWorkDate(@Param("workDate") Date workDate);

   /**
	* Обновляет признак наличии интервалов на дату.
	*
	* @param workDate дата
	* @return
	*/
   int updateHasIntervalByWorkDate(@Param("workDate") Date workDate);

   /**
	* Удалить статистические данные на дату.
	*
	* @param workDate дата
	* @return
	*/
   int deleteByWorkDate(@Param("workDate") Date workDate);

   /**
	* Получить список маршрутов.
	*
	* @param <SelectItem> тип элемента из списка.
	* @param params       параметры.
	* @return
	*/
   <SelectItem extends ISelectItem> List<SelectItem> getRouteSelectList(Map<String, Object> params);

   /**
	* Получить список парков.
	*
	* @param <SelectItem> тип элемента из списка.
	* @param params       параметры.
	* @return
	*/
   <SelectItem extends ISelectItem> List<SelectItem> getParkSelectList(Map<String, Object> params);

   /**
	* Получить список выходов маршрутов.
	*
	* @param <SelectItem> тип элемента из списка.
	* @param params       параметры.
	* @return
	*/
   <SelectItem extends ISelectItem> List<SelectItem> getRouteMoveSelectList(Map<String, Object> params);

   /**
	* Получить список рейсов маршрутов.
	*
	* @param <SelectItem> тип элемента из списка.
	* @param params       параметры.
	* @return
	*/
   <SelectItem extends ISelectItem> List<SelectItem> getRouteTripSelectList(Map<String, Object> params);

   /**
	* Получить список остановок.
	*
	* @param <SelectItem> тип элемента из списка.
	* @param params       параметры.
	* @return
	*/
   <SelectItem extends ISelectItem> List<SelectItem> getStopSelectList(Map<String, Object> params);
}
