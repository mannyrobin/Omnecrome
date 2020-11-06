package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseVersionDomainService;
import ru.armd.pbk.domain.nsi.Venicle;
import ru.armd.pbk.dto.nsi.VenicleDTO;
import ru.armd.pbk.matcher.nsi.IVenicleMatcher;
import ru.armd.pbk.repositories.nsi.VenicleRepository;

/**
 * Сервис ТС.
 */
@Service
public class VenicleService
	extends BaseVersionDomainService<Venicle, VenicleDTO> {

   private static final Logger LOGGER = Logger.getLogger(VenicleService.class);

   @Autowired
   VenicleService(VenicleRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public VenicleDTO toDTO(Venicle domain) {
	  return IVenicleMatcher.INSTANCE.toDTO(domain);
   }

   @Override
   public Venicle toDomain(VenicleDTO dto) {
	  return IVenicleMatcher.INSTANCE.toDomain(dto);
   }
}
