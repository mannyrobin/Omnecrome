package ru.armd.pbk.repositories.viss.gfts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gtfs.GtfsStops;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gtfs.GtfsStopsMapper;

/**
 * Репозиторий "Остановок".
 */
@Repository
public class GtfsStopsRepository
	extends BaseDomainRepository<GtfsStops> {

   @Autowired
   GtfsStopsRepository(GtfsStopsMapper mapper) {
	  super(VisAuditType.VIS_GTFS_STOPS, mapper);
   }

}
