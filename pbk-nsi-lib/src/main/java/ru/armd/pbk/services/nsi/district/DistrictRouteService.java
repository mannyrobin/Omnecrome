package ru.armd.pbk.services.nsi.district;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseVersionDomainService;
import ru.armd.pbk.domain.nsi.district.DistrictRoute;
import ru.armd.pbk.dto.nsi.district.DistrictRouteDTO;
import ru.armd.pbk.matcher.nsi.IDistrictMatcher;
import ru.armd.pbk.repositories.nsi.district.DistrictRouteRepository;

/**
 * Сервис для работы с маршрутами районов.
 */
@Service
public class DistrictRouteService
	extends BaseVersionDomainService<DistrictRoute, DistrictRouteDTO> {

   private static final Logger LOGGER = Logger.getLogger(DistrictRouteService.class);

   @Autowired
   DistrictRouteService(DistrictRouteRepository domainRepository) {
	  super(domainRepository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public DistrictRoute toDomain(DistrictRouteDTO dto) {
	  return IDistrictMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public DistrictRouteDTO toDTO(DistrictRoute domain) {
	  return IDistrictMatcher.INSTANCE.toDTO(domain);
   }

   @Transactional
   @Override
   public DistrictRouteDTO saveDTO(DistrictRouteDTO dto) {
	  DistrictRoute resultDomain = null;
	  for (Long routeId : dto.getRouteIds()) {
		 dto.setRouteId(routeId);
		 DistrictRoute domain = toDomain(dto);
		 resultDomain = domainRepository.save(domain);
	  }
	  return toDTO(resultDomain);
   }

}
