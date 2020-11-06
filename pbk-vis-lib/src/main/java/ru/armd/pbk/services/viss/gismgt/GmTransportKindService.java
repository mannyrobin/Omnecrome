package ru.armd.pbk.services.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.gismgt.GmTransportKind;
import ru.armd.pbk.dto.viss.gismgt.GmTransportKindDTO;
import ru.armd.pbk.repositories.viss.gismgt.GmTransportKindRepository;

/**
 * Сервис ГИС МГТ Типов ТС.
 */
@Service
public class GmTransportKindService
	extends BaseDomainService<GmTransportKind, GmTransportKindDTO> {

   private static final Logger LOGGER = Logger.getLogger(GmTransportKindService.class);

   @Autowired
   GmTransportKindService(GmTransportKindRepository domainRepository) {
	  super(domainRepository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
