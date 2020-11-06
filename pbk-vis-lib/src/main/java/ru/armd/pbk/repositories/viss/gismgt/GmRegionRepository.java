package ru.armd.pbk.repositories.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gismgt.GmRegion;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gismgt.GmRegionMapper;

/**
 * Репозиторий для работы со сущностью "ГИС МГТ Район".
 */
@Repository
public class GmRegionRepository
	extends BaseDomainRepository<GmRegion> {

   public static final Logger LOGGER = Logger.getLogger(GmRegionRepository.class);

   @Autowired
   GmRegionRepository(GmRegionMapper mapper) {
	  super(VisAuditType.VIS_GISMGT_REGION, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}