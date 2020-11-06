package ru.armd.pbk.repositories.viss.gfts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gtfs.GtfsStopTimes;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gtfs.GtfsStopTimesMapper;

/**
 * Репозиторий "Поостановочного расписания".
 */
@Repository
public class GtfsStopTimesRepository
	extends BaseDomainRepository<GtfsStopTimes> {

   @Autowired
   GtfsStopTimesRepository(GtfsStopTimesMapper mapper) {
	  super(VisAuditType.VIS_GTFS_STOP_TIMES, mapper);
   }

}
