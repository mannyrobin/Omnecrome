package ru.armd.pbk.repositories.unionanalysis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.mappers.unionanalysis.UnionAnalysisByRouteMapper;

/**
 * Репозиторий сводного анализа по маршрутам.
 */
@Repository
public class UnionAnalysisByRouteRepository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(UnionAnalysisByRouteRepository.class);

   @Autowired
   UnionAnalysisByRouteRepository(UnionAnalysisByRouteMapper mapper) {
	  super(ReportAuditType.UNION_ANALYSIS_BY_ROUTE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
