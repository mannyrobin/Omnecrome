package ru.armd.pbk.repositories.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gismgt.GmRoute;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteMapper;

/**
 * Репозиторий для работы со сущностью "ГИС МГТ Маршрут".
 */
@Repository
public class GmRouteRepository
	extends BaseDomainRepository<GmRoute> {

   public static final Logger LOGGER = Logger.getLogger(GmRouteRepository.class);

   @Autowired
   GmRouteRepository(GmRouteMapper mapper) {
	  super(VisAuditType.VIS_GISMGT_ROUTE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
