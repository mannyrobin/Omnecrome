package ru.armd.pbk.repositories.unionanalysis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.mappers.unionanalysis.UnionAnalysisByTripStopMapper;

/**
 * Репозиторий сводного анализа по остановкам рейса.
 */
@Repository
public class UnionAnalysisByTripStopRepository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(UnionAnalysisByTripStopRepository.class);

   @Autowired
   UnionAnalysisByTripStopRepository(UnionAnalysisByTripStopMapper mapper) {
	  super(ReportAuditType.UNION_ANALYSIS_BY_TRIP_STOP, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
