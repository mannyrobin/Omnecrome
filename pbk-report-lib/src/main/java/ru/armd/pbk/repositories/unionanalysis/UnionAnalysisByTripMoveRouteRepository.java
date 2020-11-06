package ru.armd.pbk.repositories.unionanalysis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.mappers.unionanalysis.UnionAnalysisByTripMoveRouteMapper;

/**
 * Репозиторий сводного анализа по выходам маршрута рейса.
 */
@Repository
public class UnionAnalysisByTripMoveRouteRepository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(UnionAnalysisByTripMoveRouteRepository.class);

   @Autowired
   UnionAnalysisByTripMoveRouteRepository(UnionAnalysisByTripMoveRouteMapper mapper) {
	  super(ReportAuditType.UNION_ANALYSIS_BY_TRIP_MOVE_ROUTE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
