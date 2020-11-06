package ru.armd.pbk.services.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.gismgt.GmRoute;
import ru.armd.pbk.dto.viss.gismgt.GmRouteDTO;
import ru.armd.pbk.repositories.viss.gismgt.GmRouteRepository;

/**
 * Сервис ГИС МГТ Маршрутов.
 */
@Service
public class GmRouteService
	extends BaseDomainService<GmRoute, GmRouteDTO> {

   private static final Logger LOGGER = Logger.getLogger(GmTransportKindService.class);

   @Autowired
   GmRouteService(GmRouteRepository domainRepository) {
	  super(domainRepository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
