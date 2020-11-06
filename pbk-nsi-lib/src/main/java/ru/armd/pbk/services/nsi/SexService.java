package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.Sex;
import ru.armd.pbk.dto.nsi.SexDTO;
import ru.armd.pbk.matcher.nsi.ISexMatcher;
import ru.armd.pbk.repositories.nsi.SexRepository;

/**
 * Сервис "пола".
 */
@Service
public class SexService
	extends BaseDomainService<Sex, SexDTO> {

   private static final Logger LOGGER = Logger.getLogger(SexService.class);

   /**
	* Конструктор.
	*
	* @param sexRepository репозиторий.
	*/
   @Autowired
   public SexService(SexRepository sexRepository) {
	  super(sexRepository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Sex toDomain(SexDTO dto) {
	  return ISexMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public SexDTO toDTO(Sex domain) {
	  return ISexMatcher.INSTANCE.toDTO(domain);
   }
}
