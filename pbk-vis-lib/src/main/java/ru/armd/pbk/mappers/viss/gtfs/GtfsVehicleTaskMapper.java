package ru.armd.pbk.mappers.viss.gtfs;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.viss.gtfs.GtfsVehicleTask;

/**
 * Маппер "Справочник назначений ТС на маршрут".
 */
@IsMapper
public interface GtfsVehicleTaskMapper
	extends IDomainMapper<GtfsVehicleTask> {

}
