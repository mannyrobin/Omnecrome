package ru.armd.pbk.services.unionanalysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.repositories.unionanalysis.UnionAnalysisByTripStopRepository;

/**
 * Сервис сводного анализа по остановкам рейса.
 */
@Service
public class UnionAnalysisByTripStopService
	extends BaseDomainService<BaseDomain, BaseDTO> {

   @Autowired
   UnionAnalysisByTripStopService(UnionAnalysisByTripStopRepository domainRepository) {
	  super(domainRepository);
   }

}
