package ru.armd.pbk.mappers.viss.gtfs;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.viss.gtfs.GtfsTripsStops;

/**
 * Маппер "Справочник рейсов с последовательностями остановок из ЭРМ".
 */
@IsMapper
public interface GtfsTripsStopsMapper
	extends IDomainMapper<GtfsTripsStops> {

}
