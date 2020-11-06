package ru.armd.pbk.repositories.nsi.askp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.askp.TicketCheck;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.askp.TicketCheckMapper;
import ru.armd.pbk.repositories.nsi.TsTypeRepository;

@Repository
public class TicketCheckRepository
	extends BaseDomainRepository<TicketCheck> {

   public static final Logger LOGGER = Logger.getLogger(TsTypeRepository.class);

   @Autowired
   TicketCheckRepository(TicketCheckMapper mapper) {
	  super(NsiAuditType.NSI_ASKP_TICKET_CHECK, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
