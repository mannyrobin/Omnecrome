package ru.armd.pbk.repositories.viss.gfts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gtfs.GtfsReplaceVehicle;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gtfs.GtfsReplaceVehicleMapper;

/**
 * Репозиторий "Замена ТС".
 */
@Repository
public class GtfsReplaceVehicleRepository
	extends BaseDomainRepository<GtfsReplaceVehicle> {

   @Autowired
   GtfsReplaceVehicleRepository(GtfsReplaceVehicleMapper mapper) {
	  super(VisAuditType.VIS_GTFS_REPLACE_VEHICLE, mapper);
   }

}
