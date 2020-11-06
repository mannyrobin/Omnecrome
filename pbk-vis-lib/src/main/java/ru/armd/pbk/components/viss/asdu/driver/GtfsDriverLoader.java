package ru.armd.pbk.components.viss.asdu.driver;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseCsvDomainLoader;
import ru.armd.pbk.domain.nsi.Driver;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.repositories.nsi.DriverRepository;

/**
 * Лоадер для водителей.
 */
@Component
public class GtfsDriverLoader
	extends BaseCsvDomainLoader<Driver> {

   private static final Logger LOGGER = Logger.getLogger(GtfsDriverLoader.class);

   private DriverRepository driverRepository;

   @Autowired
   GtfsDriverLoader(DriverRepository domainRepository) {
	  super(domainRepository);
	  driverRepository = domainRepository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public AuditType getAuditType() {
	  return VisAuditType.VIS_ASDU_DRIVER;
   }

   @Override
   protected Driver createDomain(String[] fields) {
	  // LAST_NAME;FIRST_NAME;MIDDLE_NAME;DEPOT_ID;DRIVER_ID;
	  Driver driver = new Driver();
	  driver.setSurname(getStringValue(fields[0]));
	  driver.setName(getStringValue(fields[1]));
	  driver.setPatronumic(getStringValue(fields[2]));
	  driver.setPersonalNumber(getStringValue(fields[4]));
	  driver.setAsduDriverId(getStringValue(fields[4]));
	  driver.setAsduDepotId(getStringValue(fields[3]));
	  return driver;
   }

   @Override
   protected Driver getExistedDomain(Driver newDomain) {
	  return driverRepository.getByAsduDriverId(newDomain.getAsduDriverId());
   }

   @Override
   protected void updateDomain(Driver newDomain, Driver existedDomain) {
	  newDomain.setHeadId(existedDomain.getHeadId());
   }
}
