package ru.armd.pbk.repositories.viss.gfts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gtfs.GtfsRoute;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gtfs.GtfsRouteMapper;

/**
 * Репозиторий "Маршрутов".
 */
@Repository
public class GtfsRouteRepository
	extends BaseDomainRepository<GtfsRoute> {

   @Autowired
   GtfsRouteRepository(GtfsRouteMapper mapper) {
	  super(VisAuditType.VIS_GTFS_ROUTE, mapper);
   }

}
