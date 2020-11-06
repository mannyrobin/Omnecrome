package ru.armd.pbk.mappers.plans.routes;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.plans.routes.ASKPTicketCkecks;
import ru.armd.pbk.domain.plans.routes.RouteRatio;
import ru.armd.pbk.views.plans.routes.PlanRouteView;

import java.util.Date;
import java.util.List;

/**
 * Маппер маршрутов плана отдела.
 */
@IsMapper
public interface PlanRouteMapper
	extends IDomainMapper<RouteRatio> {

   /**
	* Получает список маршрутов плана отдела по параметрам.
	*
	* @param param параметры
	* @return список маршрутов плана отдела
	*/
   List<PlanRouteView> getPlanRouteViews(BaseGridViewParams param);

   /**
	* Получает список рейтингов маршрутов для переданного плана отдела и списка
	* маршрутов.
	*
	* @param param  параметры.
	* @param routes список маршрутов
	* @return список рейтингов маршрутов
	*/
   List<RouteRatio> getRouteRatios(@Param("params") BaseGridViewParams param,
								   @Param("routes") List<PlanRouteView> routes);

   /**
	* Получение списка билетов.
	*
	* @param routeId   маршрут
	* @param workDates даты
	* @return
	*/
   List<ASKPTicketCkecks> getPassengersCount(@Param("routeId") Long routeId, @Param("workDates") Date[] workDates);

   /**
	* Получение рейтинга маршрута на дату.
	*
	* @param routeId  маршрут
	* @param workDate дата
	* @return
	*/
   List<RouteRatio> getRouteRatiosByDate(@Param("workDate") Date workDate);
}