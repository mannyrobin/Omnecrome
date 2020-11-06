package ru.armd.pbk.mappers.viss.gtfs;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.viss.gtfs.GtfsCalendar;

/**
 * Маппер "Справочник периодов действий маршрутов".
 */
@IsMapper
public interface GtfsCalendarMapper
	extends IDomainMapper<GtfsCalendar> {

}
