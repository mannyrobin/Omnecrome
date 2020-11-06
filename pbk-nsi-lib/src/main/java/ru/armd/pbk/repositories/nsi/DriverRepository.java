package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;
import ru.armd.pbk.domain.nsi.Driver;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.DriverMapper;

/**
 * Репозиторий водителей.
 */
@Repository
public class DriverRepository
	extends BaseVersionDomainRepository<Driver> {

   public static final Logger LOGGER = Logger.getLogger(DriverRepository.class);

   private DriverMapper driverMapper;

   @Autowired
   DriverRepository(DriverMapper mapper) {
	  super(NsiAuditType.NSI_DRIVER, mapper);
	  driverMapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Возвращает домен водителя по id из системы асду.
	*
	* @param asduDriverId id водителя из системы асду.
	* @return Домен водителя.
	*/
   public Driver getByAsduDriverId(String asduDriverId) {
	  return driverMapper.getByAsduDriverId(asduDriverId);
   }
}