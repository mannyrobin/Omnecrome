package ru.armd.pbk.services.nsi.bsotype;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.bsotype.BsoType;
import ru.armd.pbk.dto.nsi.bsotype.BsoTypeDTO;
import ru.armd.pbk.matcher.nsi.IBsoTypeMatcher;
import ru.armd.pbk.repositories.nsi.bsotype.BsoTypeRepository;

/**
 * Сервисы типов БСО.
 */
@Service
public class BsoTypesService
	extends BaseDomainService<BsoType, BsoTypeDTO> {

   private static final Logger LOGGER = Logger.getLogger(BsoTypesService.class);

   @Autowired
   BsoTypesService(BsoTypeRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public BsoType toDomain(BsoTypeDTO dto) {
	  return IBsoTypeMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public BsoTypeDTO toDTO(BsoType domain) {
	  return IBsoTypeMatcher.INSTANCE.toDTO(domain);
   }
}
