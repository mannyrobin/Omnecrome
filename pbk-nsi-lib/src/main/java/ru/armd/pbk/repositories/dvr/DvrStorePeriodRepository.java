package ru.armd.pbk.repositories.dvr;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.dvr.DvrStorePeriod;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.dvr.DvrStorePeriodMapper;

/**
 * Репозиторий для периуда хранения записи с видеорегистратора.
 */
@Repository
public class DvrStorePeriodRepository
	extends BaseDomainRepository<DvrStorePeriod> {

   public static final Logger LOGGER = Logger.getLogger(DvrStorePeriodRepository.class);

   @Autowired
   DvrStorePeriodRepository(DvrStorePeriodMapper mapper) {
	  super(NsiAuditType.NSI_DVR_STORE_PERIOD, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
