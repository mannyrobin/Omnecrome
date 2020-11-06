package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.Duty;
import ru.armd.pbk.dto.nsi.DutyDTO;
import ru.armd.pbk.matcher.nsi.IDutyMatcher;
import ru.armd.pbk.repositories.nsi.DutyRepository;

/**
 * Сервис нарядов.
 */
@Service
public class DutyService
	extends BaseDomainService<Duty, DutyDTO> {

   private static final Logger LOGGER = Logger.getLogger(DutyService.class);

   @Autowired
   DutyService(DutyRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Duty toDomain(DutyDTO dto) {
	  return IDutyMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public DutyDTO toDTO(Duty domain) {
	  return IDutyMatcher.INSTANCE.toDTO(domain);
   }

}
