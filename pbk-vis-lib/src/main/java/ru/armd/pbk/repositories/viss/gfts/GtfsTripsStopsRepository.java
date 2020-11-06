package ru.armd.pbk.repositories.viss.gfts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gtfs.GtfsTripsStops;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gtfs.GtfsTripsStopsMapper;

/**
 * Репозиторий "Справочник рейсов с последовательностями остановок из ЭРМ".
 */
@Repository
public class GtfsTripsStopsRepository
	extends BaseDomainRepository<GtfsTripsStops> {

   @Autowired
   GtfsTripsStopsRepository(GtfsTripsStopsMapper mapper) {
	  super(VisAuditType.VIS_GTFS_TRIPS_STOPS, mapper);
   }

}
