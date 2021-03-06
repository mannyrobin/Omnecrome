package ru.armd.pbk.repositories.viss.gfts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gtfs.GtfsTrips;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gtfs.GtfsTripsMapper;

/**
 * Репозиторий "Рейсов".
 */
@Repository
public class GtfsTripsRepository
	extends BaseDomainRepository<GtfsTrips> {

   @Autowired
   GtfsTripsRepository(GtfsTripsMapper mapper) {
	  super(VisAuditType.VIS_GTFS_TRIPS, mapper);
   }

}
