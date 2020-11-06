package ru.armd.pbk.repositories.nsi.venue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.venue.VenueRouteType;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.venue.VenueRouteTypeMapper;

/**
 * Репозиторий "Сопутствующие маршруты".
 */
@Repository
public class VenueRouteTypeRepository
	extends BaseDomainRepository<VenueRouteType> {

   public static final Logger LOGGER = Logger.getLogger(VenueRouteTypeRepository.class);

   @Autowired
   VenueRouteTypeRepository(VenueRouteTypeMapper mapper) {
	  super(NsiAuditType.NSI_VENUE_ROUTE_TYPE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
