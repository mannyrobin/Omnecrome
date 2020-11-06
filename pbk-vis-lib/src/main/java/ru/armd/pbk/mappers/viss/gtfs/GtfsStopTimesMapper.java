package ru.armd.pbk.mappers.viss.gtfs;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.viss.gtfs.GtfsStopTimes;

/**
 * Маппер "Поостановочного расписания".
 */
@IsMapper
public interface GtfsStopTimesMapper
	extends IDomainMapper<GtfsStopTimes> {

}

