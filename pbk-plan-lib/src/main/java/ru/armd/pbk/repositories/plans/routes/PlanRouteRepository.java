package ru.armd.pbk.repositories.plans.routes;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.plans.routes.PlanRoute;
import ru.armd.pbk.domain.plans.routes.RouteRatio;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.PlanAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.plans.routes.PlanRouteMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Репозиторий маршрутов планов отдела.
 */
@Repository
public class PlanRouteRepository
	extends BaseDomainRepository<RouteRatio> {

   public static final Logger LOGGER = Logger.getLogger(PlanRouteRepository.class);

   @Autowired
   private PlanRouteMapper planRouteMapper;

   @Autowired
   PlanRouteRepository(PlanRouteMapper mapper) {
	  super(PlanAuditType.PLAN_ROUTE, mapper);
   }

   /**
	* Получает домен маршрутов плана отдела по параметрам.
	*
	* @param params параметры
	* @return домен маршрутов плана отдела
	*/
   public PlanRoute getPlanRoutes(BaseGridViewParams params) {
	  PlanRoute planRoute = new PlanRoute();
	  try {
		 if (params.getOrderBy().startsWith("day")) {
			params.getFilter().put("daySort", new SimpleDateFormat("yyyy-MM-dd").parse(params.getOrderBy().substring(3)));
			params.setOrderBy("daySort " + (params.isOrderByAsc() ? " asc" : " desc") + ", routeNumber");
			params.setOrderByAsc(true);
		 } else {
			params.getFilter().put("daySort", new Date());
		 }
		 if (params.getFilter().get("rating") != null) {
			try {
			   Integer.valueOf((String) params.getFilter().get("rating"));
			} catch (Exception e) {
			   params.getFilter().put("rating", -100);
			}
		 }
		 planRoute.setRoutes(planRouteMapper.getPlanRouteViews(params));
		 if (planRoute.getRoutes() != null && !planRoute.getRoutes().isEmpty()) {
			planRoute.setRouteRatios(planRouteMapper.getRouteRatios(params,
				planRoute.getRoutes()));
		 }
	  } catch (Exception e) {
		 String message = "Не удалось получить список для отображения маршрутов. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, PlanAuditType.PLAN_ROUTE, AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }
	  return planRoute;
   }
}
