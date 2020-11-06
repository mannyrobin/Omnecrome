package ru.armd.pbk.repositories.viss.gfts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gtfs.GtfsVehicleTask;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gtfs.GtfsVehicleTaskMapper;

/**
 * Репозиторий "Справочник назначений ТС на маршрут".
 */
@Repository
public class GtfsVehicleTaskRepository
	extends BaseDomainRepository<GtfsVehicleTask> {

   @Autowired
   GtfsVehicleTaskRepository(GtfsVehicleTaskMapper mapper) {
	  super(VisAuditType.VIS_GTFS_VEHICLE_TASK, mapper);
   }

}
