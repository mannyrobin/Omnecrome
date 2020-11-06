package ru.armd.pbk.services.nsi.venue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseVersionDomainService;
import ru.armd.pbk.domain.nsi.venue.VenueRoute;
import ru.armd.pbk.dto.nsi.venue.VenueRouteDTO;
import ru.armd.pbk.mappers.nsi.district.DistrictRouteMapper;
import ru.armd.pbk.matcher.nsi.IVenueMatcher;
import ru.armd.pbk.repositories.nsi.district.DistrictRepository;
import ru.armd.pbk.repositories.nsi.venue.VenueRouteRepository;

/**
 * Сервис для работы с "Сопутствующие маршруты".
 */
@Service
public class VenueRouteService
	extends BaseVersionDomainService<VenueRoute, VenueRouteDTO> {

   private static final Logger LOGGER = Logger.getLogger(VenueRouteService.class);

   @Autowired
   private DistrictRepository districtRepository;

   @Autowired
   private DistrictRouteMapper districtRouteMapper;

   @Autowired
   private VenueService venueService;

   @Autowired
   VenueRouteService(VenueRouteRepository domainRepository) {
	  super(domainRepository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public VenueRoute toDomain(VenueRouteDTO dto) {
	  return IVenueMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public VenueRouteDTO toDTO(VenueRoute domain) {
	  return IVenueMatcher.INSTANCE.toDTO(domain);
   }

   @Transactional
   @Override
   public VenueRouteDTO saveDTO(VenueRouteDTO dto) {
	  VenueRoute resultDomain = null;
	  for (Long routeId : dto.getRouteIds()) {
		 dto.setRouteId(routeId);
		 VenueRoute domain = toDomain(dto);
		 resultDomain = domainRepository.save(domain);
	  }
	  return toDTO(resultDomain);
   }
}
