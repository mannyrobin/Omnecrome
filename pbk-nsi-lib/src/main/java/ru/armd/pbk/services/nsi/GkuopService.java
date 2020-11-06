package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseVersionDomainService;
import ru.armd.pbk.domain.nsi.Gkuop;
import ru.armd.pbk.dto.nsi.GkuopDTO;
import ru.armd.pbk.matcher.nsi.IGkuopMatcher;
import ru.armd.pbk.repositories.nsi.GkuopRepository;

/**
 * Сервис ГКУ ОП.
 */
@Service
public class GkuopService
	extends BaseVersionDomainService<Gkuop, GkuopDTO> {

   private static final Logger LOGGER = Logger.getLogger(GkuopService.class);

   @Autowired
   GkuopService(GkuopRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Gkuop toDomain(GkuopDTO dto) {
	  return IGkuopMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public GkuopDTO toDTO(Gkuop domain) {
	  return IGkuopMatcher.INSTANCE.toDTO(domain);
   }
}
