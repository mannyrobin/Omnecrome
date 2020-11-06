package ru.armd.pbk.repositories.nsi.venue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;
import ru.armd.pbk.domain.nsi.venue.VenueDistrict;
import ru.armd.pbk.domain.nsi.venue.VenueRoute;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.venue.VenueMapper;
import ru.armd.pbk.mappers.nsi.venue.VenueRouteMapper;
import ru.armd.pbk.utils.date.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * Репозиторий "Сопутствующие маршруты".
 */
@Repository
public class VenueRouteRepository
	extends BaseVersionDomainRepository<VenueRoute> {

   public static final Logger LOGGER = Logger.getLogger(VenueRouteRepository.class);

   @Autowired
   private VenueMapper venueMapper;

   private VenueRouteMapper mapper;

   @Autowired
   VenueRouteRepository(VenueRouteMapper mapper) {
	  super(NsiAuditType.NSI_VENUE_ROUTE, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public VenueRoute save(VenueRoute domain) {
	  VenueRoute actualVersionDomain = getActual(domain.getId());
	  if (actualVersionDomain != null) {
		 actualVersionDomain.setVersionEndDate(new Date());
		 update(actualVersionDomain);
	  }
	  domain.setVersionStartDate(new Date());
	  domain.setVersionEndDate(DateUtils.getVersionEndDate());
	  return create(domain);
   }

   @Override
   public int deleteSoft(List<Long> ids, Boolean tryDelete) {
	  int count = 0;
	  for (Long id : ids) {
		 VenueRoute domain = getById(id);
		 domain.setVersionEndDate(new Date());
		 update(domain);
		 count++;
	  }
	  return count;
   }

   /**
	* Удалить маршруты у районов, при удалении района у места встречи.
	*
	* @param ids - ИД связки места встречи и района.
	*/
   public void unlinkRoutes(List<Long> ids) {
	  for (VenueDistrict domain : venueMapper.getVenueDistrictByIds(ids)) {
		 initUpdaterInfo(domain);
		 domain.setVersionEndDate(new Date());
		 mapper.unlinkRoutes(domain);
	  }
   }
}
