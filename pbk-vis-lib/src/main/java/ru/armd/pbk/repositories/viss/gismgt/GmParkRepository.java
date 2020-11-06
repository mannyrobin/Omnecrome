package ru.armd.pbk.repositories.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gismgt.GmPark;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gismgt.GmParkMapper;

/**
 * Репозиторий для работы со сущностью "ГИС МГТ Парк".
 */
@Repository
public class GmParkRepository
	extends BaseDomainRepository<GmPark> {

   public static final Logger LOGGER = Logger.getLogger(GmParkRepository.class);

   @Autowired
   GmParkRepository(GmParkMapper mapper) {
	  super(VisAuditType.VIS_GISMGT_PARK, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
