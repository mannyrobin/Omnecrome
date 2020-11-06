package ru.armd.pbk.mappers.viss.gtfs;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.viss.gtfs.GtfsChangeStopTimes;

/**
 * Маппер "Оперативные изменения поостановочных расписаний".
 */
@IsMapper
public interface GtfsChangeStopTimesMapper
	extends IDomainMapper<GtfsChangeStopTimes> {

}
