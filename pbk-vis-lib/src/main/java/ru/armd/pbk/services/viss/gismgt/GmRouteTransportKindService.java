package ru.armd.pbk.services.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.gismgt.GmRouteTransportKind;
import ru.armd.pbk.dto.viss.gismgt.GmRouteTransportKindDTO;
import ru.armd.pbk.repositories.viss.gismgt.GmRouteTransportKindRepository;

/**
 * Сервис ГИС МГТ Типов ТС Маршрутов.
 */
@Service
public class GmRouteTransportKindService
	extends BaseDomainService<GmRouteTransportKind, GmRouteTransportKindDTO> {

   private static final Logger LOGGER = Logger.getLogger(GmRouteTransportKindService.class);

   @Autowired
   GmRouteTransportKindService(GmRouteTransportKindRepository domainRepository) {
	  super(domainRepository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
