package ru.armd.pbk.repositories.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gismgt.GmRouteTransportKind;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteTransportKindMapper;

/**
 * Репозиторий для работы со сущностью "ГИС МГТ ТС Тип Маршрута".
 */
@Repository
public class GmRouteTransportKindRepository
	extends BaseDomainRepository<GmRouteTransportKind> {

   public static final Logger LOGGER = Logger.getLogger(GmRouteTransportKindRepository.class);

   @Autowired
   GmRouteTransportKindRepository(GmRouteTransportKindMapper mapper) {
	  super(VisAuditType.VIS_GISMGT_ROUTE_TS_KIND, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
