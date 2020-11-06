package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseVersionDomainService;
import ru.armd.pbk.domain.nsi.Park;
import ru.armd.pbk.dto.nsi.ParkDTO;
import ru.armd.pbk.matcher.nsi.IParkMatcher;
import ru.armd.pbk.repositories.nsi.ParkRepository;

/**
 * Сервис парков.
 *
 * @author nikita_chebotaryov
 */
@Service
public class ParkService
	extends BaseVersionDomainService<Park, ParkDTO> {

   private static final Logger LOGGER = Logger.getLogger(ParkService.class);

   @Autowired
   ParkService(ParkRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Park toDomain(ParkDTO dto) {
	  return IParkMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public ParkDTO toDTO(Park domain) {
	  return IParkMatcher.INSTANCE.toDTO(domain);
   }
}
