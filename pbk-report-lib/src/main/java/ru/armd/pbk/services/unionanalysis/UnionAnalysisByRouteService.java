package ru.armd.pbk.services.unionanalysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.repositories.unionanalysis.UnionAnalysisByRouteRepository;

/**
 * Сервис сводного анализа по маршрутам.
 */
@Service
public class UnionAnalysisByRouteService
	extends BaseDomainService<BaseDomain, BaseDTO> {

   @Autowired
   UnionAnalysisByRouteService(UnionAnalysisByRouteRepository domainRepository) {
	  super(domainRepository);
   }

}
