package ru.armd.pbk.repositories.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.VisExchangeOperation;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.VisExchangeOperationMapper;

/**
 * Репозиторий типов операций с ВИС.
 */
@Repository
public class VisExchangeOperationRepository
	extends BaseDomainRepository<VisExchangeOperation> {

   public static final Logger LOGGER = Logger.getLogger(VisExchangeOperationRepository.class);

   /**
	* Конструктор.
	*
	* @param visExchangeOperationMapper Маппер репозитория.
	*/
   @Autowired
   public VisExchangeOperationRepository(VisExchangeOperationMapper visExchangeOperationMapper) {
	  super(VisAuditType.VIS_OPERATION_TYPE, visExchangeOperationMapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
