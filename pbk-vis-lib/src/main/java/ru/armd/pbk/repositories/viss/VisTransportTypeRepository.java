package ru.armd.pbk.repositories.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.VisTransportType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.VisTransportTypeMapper;

/**
 * Репозиторий видов транспорта ВИС.
 */
@Repository
public class VisTransportTypeRepository
	extends BaseDomainRepository<VisTransportType> {

   public static final Logger LOGGER = Logger.getLogger(VisTransportTypeRepository.class);

   /**
	* Конструктор.
	*
	* @param visMapper Маппер репозитория.
	*/
   @Autowired
   public VisTransportTypeRepository(VisTransportTypeMapper visMapper) {
	  super(VisAuditType.VIS_TRANSPORT_TYPE, visMapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
