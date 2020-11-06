package ru.armd.pbk.services.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.gismgt.GmPark;
import ru.armd.pbk.dto.viss.gismgt.GmParkDTO;
import ru.armd.pbk.repositories.viss.gismgt.GmParkRepository;

/**
 * Сервис ГИС МГТ Парков.
 */
@Service
public class GmParkService
	extends BaseDomainService<GmPark, GmParkDTO> {

   private static final Logger LOGGER = Logger.getLogger(GmParkService.class);

   @Autowired
   GmParkService(GmParkRepository domainRepository) {
	  super(domainRepository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
