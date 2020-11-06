package ru.armd.pbk.mappers.nsi;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.Route;
import ru.armd.pbk.views.nsi.route.RouteSelectItem;
import ru.armd.pbk.views.nsi.route.RouteStop;
import ru.armd.pbk.views.nsi.route.RouteTrajectory;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Маппер маршрута.
 */
@IsMapper
public interface RouteMapper
	extends IVersionDomainMapper<Route> {

   /**
	* Возвращает траектории маршрута.
	*
	* @param routeId идентификатор маршрута.
	* @return
	*/
   List<RouteTrajectory> getRouteTrajectory(Long routeId);

   /**
	* Возвращает траектории маршрутов.
	*
	* @param ids идентификаторы маршрутов.
	* @return
	*/
   List<RouteTrajectory> getRoutesTrajectory(List<Long> ids);

   /**
	* Возвращает траектории маршрута по номеру.
	*
	* @param routeNum номер маршрута.
	* @return
	*/
   List<RouteTrajectory> getRouteTrajectoryByRouteNum(String routeNum);

   /**
	* Возвращает остановки(координаты и описание) маршрута.
	*
	* @param routeId     идентификатор маршрута.
	* @param trajectType - тип траектории.
	* @return
	*/
   List<RouteStop> getRouteStops(@Param("routeId") Long routeId, @Param("trajectType") Long trajectType);

   /**
	* Получение списка маршрутов для отображения в комбобоксах при создании
	* задания.
	*
	* @param params Параметры фильтрации.
	* @return список ISelectItem объектов.
	*/
   List<RouteSelectItem> getSelectItemsForTask(Map<String, Object> params);

   /**
	* Получение списка маршрутов для отображения в комбобоксах при добавлении
	* сопутствующих маршрутов к месту встречи.
	*
	* @param params - параметры
	* @return - список ISelectItem объектов.
	*/
   List<SelectItem> getSelectItemsForDistrict(Map<String, Object> params);

   /**
	* Получение списка маршрутов для отображения в комбобоксах при добавлении
	* сопутствующих маршрутов к месту встречи.
	*
	* @param id - ИД места встречи
	* @return - список ISelectItem объектов.
	*/
   List<SelectItem> getSelectItemsForVenue(@Param("id") Long id);

   /**
	* Обновление количество выходов маршрутов на указаную дату.
	*
	* @param workDate - дата
	*/
   void updateRouteMoveCount(@Param("workDate") Date workDate);

   /**
	* Получает список маршрутов района, которые еще не добавлены для текущего
	* места встречи.
	*
	* @param params параметры
	* @return список маршрутов района
	*/
   List<SelectItem> getSelectItemsForDistrictAdd(Map<String, Object> params);

   /**
	* Получение маршрутов для вкладки районов мест встреч.
	*
	* @param params параметры
	* @return список маршрутов
	*/
   List<SelectItem> getLazySelectItemsForVenues(Map<String, Object> params);

   /**
	* Получения списка ночных маршрутов.
	*
	* @param <Items> тип элементов в списке.
	* @param params  фильтры
	* @return
	*/
   <Items extends ISelectItem> List<Items> getNightRoutes(Map<String, Object> params);

   /**
	* Получения списка маршрутов из АСДУ ГТФС для фильтра.
	*
	* @param <Items> тип элементов в списке.
	* @param params  фильтры
	* @return
	*/
   <Items extends ISelectItem> List<Items> getGtfsRouteLists(Map<String, Object> params);


   /**
	* Получение маршрутов округа для вкладки добавления сопутствующих маршрутов
	* мест встреч.
	*
	* @param params параметры
	* @return список маршрутов
	*/
   List<SelectItem> getRoutesOfCounty(Map<String, Object> params);

   /**
	* Получить название маршрута с типом ТС.
	*
	* @param id индефикатор маршрута
	* @return
	*/
   SelectItem getRouteTsName(@Param("id") Long id);

   /**
	* Получить список маршрутов района  места встречи
	* для отображения на карте.
	*
	* @param params параметры
	* @return
	*/
   List<SelectItem> getSelectItemsForVenueDistrict(Map<String, Object> params);

   /**
	* Получить остановки для маршрутов из списка {@code list}.
	*
	* @param list список маршрутов.
	* @return
	*/
   List<RouteStop> getRoutesStops(@Param("list") List<Long> list);
}
