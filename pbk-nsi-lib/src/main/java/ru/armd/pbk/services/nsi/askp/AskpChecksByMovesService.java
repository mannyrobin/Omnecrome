package ru.armd.pbk.services.nsi.askp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.dto.nsi.askp.AskpChecksUpdateDTO;
import ru.armd.pbk.repositories.nsi.askp.AskpChecksByMovesRepository;

/**
 * Сервис данных от АСКП.
 */
@Service
public class AskpChecksByMovesService
	extends BaseDomainService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(AskpChecksByMovesService.class);

   @Autowired
   AskpChecksByMovesRepository repository;

   @Autowired
   AskpChecksByMovesService(AskpChecksByMovesRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   public void update(AskpChecksUpdateDTO dto) {
	  ((AskpChecksByMovesRepository) this.repository).update(dto);
   }
}
