package ru.armd.pbk.mappers.viss.gismgt;

import ru.armd.pbk.annotations.mappers.IsMapper;

import java.util.List;

/**
 * Маппер, с методами для работы с обменом ГИС,
 * которые не относиться напрямую к обмену. Например,
 * методы для обновления остановок, которые отображаются
 * на карте.
 */
@IsMapper
public interface GmGisMapper {

   /**
	* Перевести остановки из состояния "Активные" в состояние "Не активные".
	*
	* @return
	*/
   int updateInactiveRouteStops();

   /**
	* Удалить не активные остановки.
	*
	* @return
	*/
   int deleteInactiveRouteStops();

	/**
	 * Вставить активные остановки.
	 *
	 * @param routeId айдишник маршрута
	 * @return
	 */
	int insertActiveRouteStops(Long routeId);


	/**
	 * Возвращает список айдишников маршрутов
	 *
	 * @return
	 */
	List<Long> getRouteIdsList();
}
