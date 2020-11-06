package ru.armd.pbk.repositories.nsi.district;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;
import ru.armd.pbk.domain.nsi.district.DistrictRoute;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.district.DistrictRouteMapper;
import ru.armd.pbk.utils.date.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * Репозиторий маршрутов района.
 */
@Repository
public class DistrictRouteRepository
	extends BaseVersionDomainRepository<DistrictRoute> {

   public static final Logger LOGGER = Logger.getLogger(DistrictRouteRepository.class);

   @Autowired
   DistrictRouteRepository(DistrictRouteMapper mapper) {
	  super(NsiAuditType.NSI_DISTRICT_ROUTE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public DistrictRoute save(DistrictRoute domain) {
	  DistrictRoute actualVersionDomain = getActual(domain.getId());
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
		 DistrictRoute domain = getById(id);
		 domain.setVersionEndDate(new Date());
		 update(domain);
		 count++;
	  }
	  return count;
   }
}
