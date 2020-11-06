package ru.armd.pbk.services.unionanalysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.repositories.unionanalysis.UnionAnalysisByMoveRouteRepository;

/**
 * Сервис сводного анализа по выходам маршрута.
 */
@Service
public class UnionAnalysisByMoveRouteService
	extends BaseDomainService<BaseDomain, BaseDTO> {

   @Autowired
   UnionAnalysisByMoveRouteService(UnionAnalysisByMoveRouteRepository domainRepository) {
	  super(domainRepository);
   }

}
