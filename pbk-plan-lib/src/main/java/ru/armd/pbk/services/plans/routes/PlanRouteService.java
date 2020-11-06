package ru.armd.pbk.services.plans.routes;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.plans.routes.RouteRatio;
import ru.armd.pbk.dto.plans.routes.RouteRatioDTO;
import ru.armd.pbk.matcher.plans.PlanRouteMatcher;
import ru.armd.pbk.repositories.plans.routes.PlanRouteRepository;
import ru.armd.pbk.services.plans.PlansService;
import ru.armd.pbk.utils.date.DateUtils;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.plans.routes.PlanRouteView;

import java.util.List;

/**
 * Сервисов маршрутов планов отдела.
 */
@Service
public class PlanRouteService
	extends BaseDomainService<RouteRatio, RouteRatioDTO> {

   private static final Logger LOGGER = Logger.getLogger(PlanRouteService.class);

   private PlanRouteRepository repository;

   @Autowired
   private PlansService planService;

   @Autowired
   PlanRouteService(PlanRouteRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public RouteRatio toDomain(RouteRatioDTO dto) {
	  return PlanRouteMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public RouteRatioDTO toDTO(RouteRatio domain) {
	  return PlanRouteMatcher.INSTANCE.toDTO(domain);
   }

   @Override
   public JsonGridData getGridViews(BaseGridViewParams params) {
	  List<PlanRouteView> planBrigadeView = PlanRouteMatcher.INSTANCE.getPlanRouteViews(repository.getPlanRoutes(params));
	  return createJsonGridData(planBrigadeView, params.getPage(), params.getCount());
   }

   @Override
   public RouteRatioDTO saveDTO(RouteRatioDTO dto) {
	  boolean b = false;
	  if (dto.getId() != null && !dto.isManual()) {
		 RouteRatio rr = repository.getById(dto.getId());
		 b = rr.isManual() != dto.isManual();
	  }
	  if (dto.isManual()) {
		 dto.setWorkDate(DateUtils.addDays(dto.getWorkDate(), -14));
	  }
	  dto = super.saveDTO(dto);
	  if (b) {
		 planService.makeRouteRating(dto.getWorkDate(), null);
	  }
	  return dto;
   }
}
