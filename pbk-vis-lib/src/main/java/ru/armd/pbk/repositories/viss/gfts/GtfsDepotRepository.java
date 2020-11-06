package ru.armd.pbk.repositories.viss.gfts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gtfs.GtfsDepot;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gtfs.GtfsDepotMapper;

/**
 * Репозиторий "автотранспортных предприятий".
 */
@Repository
public class GtfsDepotRepository
	extends BaseDomainRepository<GtfsDepot> {

   @Autowired
   GtfsDepotRepository(GtfsDepotMapper mapper) {
	  super(VisAuditType.VIS_GTFS_DEPOTS, mapper);
   }
}
