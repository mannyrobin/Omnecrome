package ru.armd.pbk.services.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.gismgt.GmRegion;
import ru.armd.pbk.dto.viss.gismgt.GmRegionDTO;
import ru.armd.pbk.repositories.viss.gismgt.GmRegionRepository;

/**
 * Сервис ГИС МГТ Районов.
 */
@Service
public class GmRegionService
	extends BaseDomainService<GmRegion, GmRegionDTO> {

   private static final Logger LOGGER = Logger.getLogger(GmTransportKindService.class);

   @Autowired
   GmRegionService(GmRegionRepository domainRepository) {
	  super(domainRepository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
