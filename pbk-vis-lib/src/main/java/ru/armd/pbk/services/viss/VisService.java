package ru.armd.pbk.services.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.Vis;
import ru.armd.pbk.dto.viss.VisDTO;
import ru.armd.pbk.matcher.IVisMatcher;
import ru.armd.pbk.repositories.viss.VisRepository;

/**
 * Сервис ВИС.
 */
@Service
public class VisService
	extends BaseDomainService<Vis, VisDTO> {

   private static final Logger LOGGER = Logger.getLogger(VisService.class);

   @Autowired
   VisService(VisRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public VisDTO toDTO(Vis domain) {
	  return IVisMatcher.INSTANCE.toDTO(domain);
   }

   @Override
   public Vis toDomain(VisDTO dto) {
	  return IVisMatcher.INSTANCE.toDomain(dto);
   }
}
