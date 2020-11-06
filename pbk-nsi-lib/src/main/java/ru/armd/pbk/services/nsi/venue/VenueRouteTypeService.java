package ru.armd.pbk.services.nsi.venue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.venue.VenueRouteType;
import ru.armd.pbk.dto.nsi.venue.VenueRouteTypeDTO;
import ru.armd.pbk.matcher.nsi.IVenueMatcher;
import ru.armd.pbk.repositories.nsi.venue.VenueRouteTypeRepository;

/**
 * Сервис для работы с "Тип сопутствующие маршруты".
 */
@Service
public class VenueRouteTypeService
	extends BaseDomainService<VenueRouteType, VenueRouteTypeDTO> {

   private static final Logger LOGGER = Logger.getLogger(VenueRouteTypeService.class);

   @Autowired
   VenueRouteTypeService(VenueRouteTypeRepository domainRepository) {
	  super(domainRepository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public VenueRouteType toDomain(VenueRouteTypeDTO dto) {
	  return IVenueMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public VenueRouteTypeDTO toDTO(VenueRouteType domain) {
	  return IVenueMatcher.INSTANCE.toDTO(domain);
   }

}
