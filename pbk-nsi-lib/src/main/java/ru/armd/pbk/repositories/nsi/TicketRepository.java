package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;
import ru.armd.pbk.domain.nsi.Ticket;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.TicketMapper;

/**
 * Репозиторий билетов.
 */
@Repository
public class TicketRepository
	extends BaseVersionDomainRepository<Ticket> {

   public static final Logger LOGGER = Logger.getLogger(TicketRepository.class);

   @Autowired
   TicketRepository(TicketMapper mapper) {
	  super(NsiAuditType.NSI_TICKET, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}