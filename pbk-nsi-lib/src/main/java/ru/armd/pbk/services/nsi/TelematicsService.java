package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.Telematics;
import ru.armd.pbk.dto.nsi.TelematicsDTO;
import ru.armd.pbk.matcher.nsi.ITelematicsMatcher;
import ru.armd.pbk.repositories.nsi.TelematicsRepository;

/**
 * Сервис телематики.
 */
@Service
public class TelematicsService
	extends BaseDomainService<Telematics, TelematicsDTO> {

   private static final Logger LOGGER = Logger.getLogger(TelematicsService.class);

   @Autowired
   TelematicsService(TelematicsRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Telematics toDomain(TelematicsDTO dto) {
	  return ITelematicsMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public TelematicsDTO toDTO(Telematics domain) {
	  return ITelematicsMatcher.INSTANCE.toDTO(domain);
   }
}
