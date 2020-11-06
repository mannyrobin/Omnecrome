package ru.armd.pbk.repositories.viss.gfts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gtfs.GtfsCalendar;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gtfs.GtfsCalendarMapper;

/**
 * Репозиторий "Справочник периодов действий маршрутов".
 */
@Repository
public class GtfsCalendarRepository
	extends BaseDomainRepository<GtfsCalendar> {

   @Autowired
   GtfsCalendarRepository(GtfsCalendarMapper mapper) {
	  super(VisAuditType.VIS_GTFS_CALENDAR, mapper);
   }
}
