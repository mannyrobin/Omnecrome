package ru.armd.pbk.repositories.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.VisExchangeResult;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.VisExchangeResultMapper;

/**
 * Репозиторий журнальной записи отправки обмена.
 */
@Repository
public class VisExchangeResultRepository
	extends BaseDomainRepository<VisExchangeResult> {

   public static final Logger LOGGER = Logger.getLogger(VisExchangeResultRepository.class);

   /**
	* Конструктор.
	*
	* @param visExchangeResultMapper Маппер репозитория.
	*/
   @Autowired
   public VisExchangeResultRepository(VisExchangeResultMapper visExchangeResultMapper) {
	  super(VisAuditType.VIS_EXCHANGE_RESULT, visExchangeResultMapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
