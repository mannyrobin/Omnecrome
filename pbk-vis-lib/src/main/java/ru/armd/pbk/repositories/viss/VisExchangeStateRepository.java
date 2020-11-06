package ru.armd.pbk.repositories.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.VisExchangeState;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.VisExchangeStateMapper;

/**
 * Репозиторий статусов взаимодействия с ВИС.
 */
@Repository
public class VisExchangeStateRepository
	extends BaseDomainRepository<VisExchangeState> {

   public static final Logger LOGGER = Logger.getLogger(VisExchangeStateRepository.class);

   /**
	* Конструктор.
	*
	* @param visExchangeState Маппер репозитория.
	*/
   @Autowired
   public VisExchangeStateRepository(VisExchangeStateMapper visExchangeState) {
	  super(VisAuditType.VIS_EXCHANGE_STATE, visExchangeState);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
