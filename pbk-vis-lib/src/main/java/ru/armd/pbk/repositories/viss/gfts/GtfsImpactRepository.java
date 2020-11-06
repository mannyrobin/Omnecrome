package ru.armd.pbk.repositories.viss.gfts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gtfs.GtfsImpact;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gtfs.GtfsImpactMapper;

/**
 * Репозиторий "Управляющие воздействия".
 */
@Repository
public class GtfsImpactRepository
	extends BaseDomainRepository<GtfsImpact> {

   @Autowired
   GtfsImpactRepository(GtfsImpactMapper mapper) {
	  super(VisAuditType.VIS_GTFS_IMPACT, mapper);
   }

}
