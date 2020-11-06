package ru.armd.pbk.services.unionanalysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.repositories.unionanalysis.UnionAnalysisByTripMoveRouteRepository;

/**
 * Сервис сводного анализа по выходам маршрута рейса.
 */
@Service
public class UnionAnalysisByTripMoveRouteService
	extends BaseDomainService<BaseDomain, BaseDTO> {

   @Autowired
   UnionAnalysisByTripMoveRouteService(UnionAnalysisByTripMoveRouteRepository domainRepository) {
	  super(domainRepository);
   }

}
