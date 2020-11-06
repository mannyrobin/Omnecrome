package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseVersionDomainService;
import ru.armd.pbk.domain.nsi.Ticket;
import ru.armd.pbk.dto.nsi.TicketDTO;
import ru.armd.pbk.matcher.nsi.ITicketMatcher;
import ru.armd.pbk.repositories.nsi.TicketRepository;

/**
 * Сервис билетов.
 */
@Service
public class TicketService
	extends BaseVersionDomainService<Ticket, TicketDTO> {

   private static final Logger LOGGER = Logger.getLogger(TicketService.class);

   @Autowired
   TicketService(TicketRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public TicketDTO toDTO(Ticket domain) {
	  return ITicketMatcher.INSTANCE.toDTO(domain);
   }

   @Override
   public Ticket toDomain(TicketDTO dto) {
	  return ITicketMatcher.INSTANCE.toDomain(dto);
   }
}
