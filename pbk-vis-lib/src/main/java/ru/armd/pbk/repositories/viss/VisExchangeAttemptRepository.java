package ru.armd.pbk.repositories.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.VisExchangeAttempt;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.VisExchangeAttemptMapper;

/**
 * Репозиторий журнальной записи отправки обмена.
 */
@Repository
public class VisExchangeAttemptRepository
	extends BaseDomainRepository<VisExchangeAttempt> {

   public static final Logger LOGGER = Logger.getLogger(VisExchangeAttemptRepository.class);

   /**
	* Конструктор.
	*
	* @param visExchangeAttemptMapper Маппер репозитория.
	*/
   @Autowired
   public VisExchangeAttemptRepository(VisExchangeAttemptMapper visExchangeAttemptMapper) {
	  super(VisAuditType.VIS_EXCHANGE_ATTEMPT, visExchangeAttemptMapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
