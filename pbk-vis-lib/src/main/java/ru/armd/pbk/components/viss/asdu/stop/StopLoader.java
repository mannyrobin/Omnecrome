package ru.armd.pbk.components.viss.asdu.stop;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseArrayDomainLoader;
import ru.armd.pbk.domain.nsi.stop.Stop;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.repositories.nsi.stop.StopRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Лоадер для ТС.
 */
@Component
public class StopLoader
	extends BaseArrayDomainLoader<Stop> {

   private static final Logger LOGGER = Logger.getLogger(StopLoader.class);

   private StopRepository stopRepository;

   @Autowired
   StopLoader(StopRepository domainRepository) {
	  super(domainRepository);
	  stopRepository = domainRepository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public AuditType getAuditType() {
	  return VisAuditType.VIS_ASDU_STOP;
   }

   @Override
   protected Stop createDomain(String[] fields) {
	  Stop stop = new Stop();
	  initCreaterInfo(stop);
	  initUpdaterInfo(stop);
	  return stop;
   }

   @Override
   protected Stop getExistedDomain(Stop newDomain) {
	  Map<String, Object> params = new HashMap<String, Object>();
	  params.put("asduStopId", newDomain.getAsduStopId());
	  return stopRepository.getDomain(params);
   }

   @Override
   protected void updateFKeys(Stop newDomain, Long[] fks) {
	  newDomain.setAsduStopId(fks[0]);
   }

   @Override
   protected void updateDomain(Stop newDomain, Stop existedDomain) {
	  newDomain.setHeadId(existedDomain.getHeadId());
   }
}
