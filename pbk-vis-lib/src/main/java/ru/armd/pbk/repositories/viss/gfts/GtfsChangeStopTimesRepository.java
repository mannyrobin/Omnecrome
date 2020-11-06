package ru.armd.pbk.repositories.viss.gfts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gtfs.GtfsChangeStopTimes;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gtfs.GtfsChangeStopTimesMapper;

/**
 * Репозиторий "Оперативные изменения поостановочных расписаний".
 */
@Repository
public class GtfsChangeStopTimesRepository
	extends BaseDomainRepository<GtfsChangeStopTimes> {

   @Autowired
   GtfsChangeStopTimesRepository(GtfsChangeStopTimesMapper mapper) {
	  super(VisAuditType.VIS_GTFS_CHANGE_STOP_TIMES, mapper);
   }

}
