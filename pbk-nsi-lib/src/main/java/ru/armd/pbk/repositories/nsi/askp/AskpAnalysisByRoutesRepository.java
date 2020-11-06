package ru.armd.pbk.repositories.nsi.askp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.askp.AskpAnalysisByRoutesMapper;

@Repository
public class AskpAnalysisByRoutesRepository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(AskpAnalysisByRoutesRepository.class);

   @Autowired
   AskpAnalysisByRoutesRepository(AskpAnalysisByRoutesMapper mapper) {
	  super(NsiAuditType.NSI_ANALYSIS_BY_ROUTES, mapper);
   }
}
