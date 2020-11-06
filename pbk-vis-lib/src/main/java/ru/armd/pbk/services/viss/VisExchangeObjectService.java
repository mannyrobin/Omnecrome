package ru.armd.pbk.services.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.VisExchangeObject;
import ru.armd.pbk.dto.viss.VisExchangeObjectDTO;
import ru.armd.pbk.matcher.IVisExchangeObjectMatcher;
import ru.armd.pbk.repositories.viss.VisExchangeObjectRepository;

/**
 * Сервис типов взаимодействия с ВИС.
 */
@Service
public class VisExchangeObjectService
	extends BaseDomainService<VisExchangeObject, VisExchangeObjectDTO> {

   private static final Logger LOGGER = Logger.getLogger(VisExchangeObjectService.class);

   @Autowired
   VisExchangeObjectService(VisExchangeObjectRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public VisExchangeObjectDTO toDTO(VisExchangeObject domain) {
	  return IVisExchangeObjectMatcher.INSTANCE.toDTO(domain);
   }

   @Override
   public VisExchangeObject toDomain(VisExchangeObjectDTO dto) {
	  return IVisExchangeObjectMatcher.INSTANCE.toDomain(dto);
   }
}
