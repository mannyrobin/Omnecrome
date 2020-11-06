package ru.armd.pbk.matcher.plans;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.plans.routes.PlanRoute;
import ru.armd.pbk.domain.plans.routes.RouteRatio;
import ru.armd.pbk.dto.plans.routes.RouteRatioDTO;
import ru.armd.pbk.utils.date.DateUtils;
import ru.armd.pbk.views.plans.routes.PlanRouteView;
import ru.armd.pbk.views.plans.routes.RouteRatioView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Преобразует входные данные для получения view маршрутов плана отдела.
 */
@Mapper
public abstract class PlanRouteMatcher
	implements IDomainMatcher<RouteRatio, RouteRatioDTO> {

   public static final PlanRouteMatcher INSTANCE = Mappers.getMapper(PlanRouteMatcher.class);

   /**
	* Получает из домена рейтинга маршрута плана отдела view.
	*
	* @param routeRatio домен рейтинга маршрута плана отдела
	* @return view рейтинга маршрута плана отдела
	*/
   abstract RouteRatioView getRouteRatioView(RouteRatio routeRatio);

   /**
	* Получает из домена маршрутов плана отдела view.
	*
	* @param planRoute домен маршрутов плана отдела
	* @return view маршрутов плана отдела
	*/
   public List<PlanRouteView> getPlanRouteViews(PlanRoute planRoute) {
	  if (planRoute != null) {
		 List<PlanRouteView> planRouteViews = initPlanRouteViewList(planRoute.getRoutes());
		 if (planRoute.getRoutes() != null && planRoute.getRoutes().size() > 0) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			for (RouteRatio routeRatio : planRoute.getRouteRatios()) {
			   for (PlanRouteView view : planRouteViews) {
				  if (view.getId().equals(routeRatio.getRouteId())) {
					 view.getRoutes().put(df.format(DateUtils.addDays(routeRatio.getWorkDate(), 14)), getRouteRatioView(routeRatio));
					 continue;
				  }
			   }
			}
		 }
		 if (planRouteViews.isEmpty()) {
			planRouteViews.add(new PlanRouteView());
			planRouteViews.get(0).setCnt(0L);
		 }
		 return planRouteViews;
	  }
	  return null;
   }

   /**
	* Начальная инициализация view маршрутов плана отдела.
	*
	* @param planRouteViews список views
	* @return список проинициализированных views
	*/
   private List<PlanRouteView> initPlanRouteViewList(List<PlanRouteView> planRouteViews) {
	  if (planRouteViews == null) {
		 return null;
	  }
	  for (PlanRouteView planRouteView : planRouteViews) {
		 planRouteView.setRoutes(new HashMap<String, RouteRatioView>());
	  }
	  return planRouteViews;
   }
}
