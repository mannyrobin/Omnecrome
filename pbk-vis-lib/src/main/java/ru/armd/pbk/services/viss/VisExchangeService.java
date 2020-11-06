package ru.armd.pbk.services.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.dto.viss.VisExchangeDTO;
import ru.armd.pbk.matcher.VisExchangeMatcher;
import ru.armd.pbk.repositories.viss.VisExchangeConfigRepository;
import ru.armd.pbk.repositories.viss.VisExchangeObjectRepository;
import ru.armd.pbk.repositories.viss.VisExchangeOperationRepository;
import ru.armd.pbk.repositories.viss.VisExchangeRepository;
import ru.armd.pbk.repositories.viss.VisExchangeStateRepository;
import ru.armd.pbk.repositories.viss.VisRepository;
import ru.armd.pbk.repositories.viss.VisTransportTypeRepository;
import ru.armd.pbk.services.nsi.DriverService;

/**
 * Сервис журнала взаимодействия с ВИС.
 */
@Service
public class VisExchangeService
	extends BaseDomainService<VisExchange, VisExchangeDTO> {

   private static final Logger LOGGER = Logger.getLogger(DriverService.class);


   private VisExchangeConfigRepository visExchangeConfigRepository;
   private VisExchangeStateRepository visExchangeStateRepository;
   private VisTransportTypeRepository visTransportTypeRepository;
   private VisExchangeObjectRepository visExchangeObjectRepository;
   private VisExchangeOperationRepository visExchangeOperationRepository;
   private VisRepository visRepository;

   @Autowired
   private VisExchangeConfigService visExchangeConfigService;

   @Autowired
   VisExchangeService(VisExchangeRepository repository, VisExchangeConfigRepository visExchangeConfig, VisExchangeStateRepository visExchangeState,
					  VisTransportTypeRepository visTransportType, VisExchangeObjectRepository visExchangeObject,
					  VisExchangeOperationRepository visExchangeOperation, VisRepository vis) {
	  super(repository);

	  visExchangeConfigRepository = visExchangeConfig;
	  visExchangeStateRepository = visExchangeState;
	  visTransportTypeRepository = visTransportType;
	  visExchangeObjectRepository = visExchangeObject;
	  visExchangeOperationRepository = visExchangeOperation;
	  visRepository = vis;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public VisExchangeDTO toDTO(VisExchange domain) {
	  return VisExchangeMatcher.INSTANCE.toDTOForView(domain,
		  visExchangeConfigRepository, visExchangeStateRepository, visTransportTypeRepository,
		  visExchangeObjectRepository, visExchangeOperationRepository, visRepository);
   }

   @Override
   public VisExchange toDomain(VisExchangeDTO dto) {
	  return VisExchangeMatcher.INSTANCE.toDomain(dto);
   }

   /**
	* Повторить обмен с ВИС.
	*
	* @param exchangeId - ИД обмена с ВИС.
	*/
   public void repeat(Long exchangeId) {
	  VisExchange exchange = domainRepository.getById(exchangeId);
	  visExchangeConfigService.start(exchange.getConfigId(), exchange.getParameter(), exchange.getWorkDate(), exchange.getWorkDate());
   }
}
