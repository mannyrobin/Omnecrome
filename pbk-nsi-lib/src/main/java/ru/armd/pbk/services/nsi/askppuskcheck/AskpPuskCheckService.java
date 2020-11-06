package ru.armd.pbk.services.nsi.askppuskcheck;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.askppuskcheck.AskpPuskCheck;
import ru.armd.pbk.dto.nsi.askppuskcheck.AskpPuskCheckDTO;
import ru.armd.pbk.matcher.nsi.IAskpPuskCheckMatcher;
import ru.armd.pbk.repositories.nsi.askppuskcheck.AskpPuskCheckRepository;

/**
 * Сервис данных от АСКП.
 */
@Service
public class AskpPuskCheckService
	extends BaseDomainService<AskpPuskCheck, AskpPuskCheckDTO> {

   private static final Logger LOGGER = Logger.getLogger(AskpPuskCheckService.class);

   private AskpPuskCheckRepository repository;

   @Autowired
   AskpPuskCheckService(AskpPuskCheckRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public AskpPuskCheck toDomain(AskpPuskCheckDTO dto) {
	  return IAskpPuskCheckMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public AskpPuskCheckDTO toDTO(AskpPuskCheck domain) {
	  return IAskpPuskCheckMatcher.INSTANCE.toDTO(domain);
   }

   /**
	* Привязать проверку ПУсК к заданию.
	*
	* @param taskId - ИД задания.
	*/
   @Transactional
   public int bind(Long taskId) {
	  return repository.bind(taskId);
   }
}
