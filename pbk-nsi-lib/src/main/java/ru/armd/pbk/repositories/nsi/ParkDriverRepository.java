package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;
import ru.armd.pbk.domain.nsi.ParkDriver;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.ParkDriverMapper;
import ru.armd.pbk.utils.date.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * Репозиторий водителей парка.
 */
@Repository
public class ParkDriverRepository
	extends BaseVersionDomainRepository<ParkDriver> {

   public static final Logger LOGGER = Logger.getLogger(ParkDriverRepository.class);

   @Autowired
   ParkDriverRepository(ParkDriverMapper mapper) {
	  super(NsiAuditType.NSI_PARK_DRIVER, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public ParkDriver save(ParkDriver domain) {
	  ParkDriver actualVersionDomain = getActual(domain.getId());
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
		 ParkDriver domain = getById(id);
		 domain.setVersionEndDate(new Date());
		 update(domain);
		 count++;
	  }
	  return count;
   }
}
