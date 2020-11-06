package ru.armd.pbk.repositories.viss.gfts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gtfs.GtfsImpactCode;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gtfs.GtfsImpactCodeMapper;

/**
 * Репозиторий "Коды управляющих воздействий".
 */
@Repository
public class GtfsImpactCodeRepository
	extends BaseDomainRepository<GtfsImpactCode> {

   @Autowired
   GtfsImpactCodeRepository(GtfsImpactCodeMapper mapper) {
	  super(VisAuditType.VIS_GTFS_IMPACT_CODE, mapper);
   }

}
