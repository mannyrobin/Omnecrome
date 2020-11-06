package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.TicketTypeMapper;

/**
 * Репозиторий типов билетов.
 */
@Repository
public class TicketTypeRepository
	extends BaseDomainRepository<BaseDomain> {
   public static final Logger LOGGER = Logger.getLogger(TicketTypeRepository.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер.
	*/
   @Autowired
   public TicketTypeRepository(TicketTypeMapper mapper) {
	  super(NsiAuditType.NSI_TICKET_TYPE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
