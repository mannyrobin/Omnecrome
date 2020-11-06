package ru.armd.pbk.repositories.viss.gfts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gtfs.GtfsVehicle;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gtfs.GtfsVehicleMapper;

/**
 * Репозиторий ТС.
 */
@Repository
public class GtfsVehicleRepository
	extends BaseDomainRepository<GtfsVehicle> {

   @Autowired
   GtfsVehicleRepository(GtfsVehicleMapper mapper) {
	  super(VisAuditType.VIS_GTFS_VEHICLES, mapper);
   }
}
