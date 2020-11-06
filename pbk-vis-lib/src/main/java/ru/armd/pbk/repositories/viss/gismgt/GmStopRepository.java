package ru.armd.pbk.repositories.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gismgt.GmStop;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gismgt.GmStopMapper;

/**
 * Репозиторий для работы со сущностью "ГИС МГТ Остановочный пункт".
 */
@Repository
public class GmStopRepository
	extends BaseDomainRepository<GmStop> {

   public static final Logger LOGGER = Logger.getLogger(GmStopRepository.class);

   @Autowired
   GmStopRepository(GmStopMapper mapper) {
	  super(VisAuditType.VIS_GISMGT_STOP, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
