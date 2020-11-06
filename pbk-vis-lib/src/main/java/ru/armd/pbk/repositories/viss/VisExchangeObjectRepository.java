package ru.armd.pbk.repositories.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.VisExchangeObject;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.VisExchangeObjectMapper;

/**
 * Репозиторий типов взаимодействия с ВИС.
 */
@Repository
public class VisExchangeObjectRepository
	extends BaseDomainRepository<VisExchangeObject> {

   public static final Logger LOGGER = Logger.getLogger(VisExchangeObjectRepository.class);

   /**
	* Конструктор.
	*
	* @param visExchangeObject Маппер репозитория.
	*/
   @Autowired
   public VisExchangeObjectRepository(VisExchangeObjectMapper visExchangeObject) {
	  super(VisAuditType.VIS_EXCHANGE_TYPE, visExchangeObject);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
