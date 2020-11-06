package ru.armd.pbk.services.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.gismgt.GmStop;
import ru.armd.pbk.dto.viss.gismgt.GmStopDTO;
import ru.armd.pbk.repositories.viss.gismgt.GmStopRepository;

/**
 * Сервис ГИС МГТ Остановочных пунктов.
 */
@Service
public class GmStopService
	extends BaseDomainService<GmStop, GmStopDTO> {

   private static final Logger LOGGER = Logger.getLogger(GmTransportKindService.class);

   @Autowired
   GmStopService(GmStopRepository domainRepository) {
	  super(domainRepository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
