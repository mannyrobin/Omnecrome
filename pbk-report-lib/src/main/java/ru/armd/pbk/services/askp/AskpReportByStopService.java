package ru.armd.pbk.services.askp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.repositories.askp.AskpReportByStopRepository;

/**
 * Сервис поостановочного отчета АСКП.
 */
@Service
public class AskpReportByStopService
	extends BaseDomainService<BaseDomain, BaseDTO> {

   @Autowired
   AskpReportByStopService(AskpReportByStopRepository domainRepository) {
	  super(domainRepository);
   }

}
