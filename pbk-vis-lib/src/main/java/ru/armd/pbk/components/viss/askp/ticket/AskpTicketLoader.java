package ru.armd.pbk.components.viss.askp.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseCsvDomainLoader;
import ru.armd.pbk.domain.nsi.Ticket;
import ru.armd.pbk.repositories.nsi.TicketRepository;

/**
 * Загрузчик билетов из АКСП.
 */
@Component
public class AskpTicketLoader
	extends BaseCsvDomainLoader<Ticket> {

   private static String CHARSET_NAME = "windows-1251";

   private TicketRepository repository;

   @Autowired
   AskpTicketLoader(TicketRepository domainRepository) {
	  super(domainRepository, false, CHARSET_NAME);
	  repository = domainRepository;
   }

   @Override
   protected Ticket createDomain(String[] fields) {
	  Ticket domain = new Ticket();
	  domain.setCode(deleteFirstAndLast(fields[0]));
	  domain.setApplicationCode(deleteFirstAndLast(fields[1]));
	  domain.setName(deleteFirstAndLast(fields[2]));
	  return domain;
   }

   @Override
   protected Ticket getExistedDomain(Ticket newDomain) {
	  return repository.getByCode(newDomain.getCode());
   }

   @Override
   protected void updateDomain(Ticket newDomain, Ticket existedDomain) {
	  super.updateDomain(newDomain, existedDomain);
	  newDomain.setHeadId(existedDomain.getHeadId());
	  newDomain.setVersionEndDate(existedDomain.getVersionEndDate());
	  newDomain.setUseInAnalysis(existedDomain.getUseInAnalysis());
   }

   protected String deleteFirstAndLast(String str) {
	  return str.substring(1, str.length() - 1);
   }
}
