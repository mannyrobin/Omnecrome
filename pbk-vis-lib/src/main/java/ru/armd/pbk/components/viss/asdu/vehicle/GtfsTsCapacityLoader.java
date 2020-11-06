package ru.armd.pbk.components.viss.asdu.vehicle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseArrayDomainLoader;
import ru.armd.pbk.domain.nsi.TsCapacity;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.repositories.nsi.TsCapacityRepository;

/**
 * Лоадер для ТС.
 */
@Component
public class GtfsTsCapacityLoader
	extends BaseArrayDomainLoader<TsCapacity> {

   private static final Logger LOGGER = Logger.getLogger(GtfsTsCapacityLoader.class);

   private TsCapacityRepository tsCapacityRepository;

   @Autowired
   GtfsTsCapacityLoader(TsCapacityRepository domainRepository) {
	  super(domainRepository);
	  tsCapacityRepository = domainRepository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public AuditType getAuditType() {
	  return VisAuditType.VIS_ASDU_VENICLE;
   }

   @Override
   protected TsCapacity createDomain(String[] fields) {
	  // VEHICLE_ID;DEPOT_ID;DEPOT_NUMBER;BRAND;MODEL;CAPACITY;SEATS;SQUARE;MASS;LENGTH;WIDTH;HEIGHT;SPEED_MAX;
	  TsCapacity tsCapacity = new TsCapacity();
	  tsCapacity.setCod(getStringValue(fields[5]));
	  tsCapacity.setName(getStringValue(fields[5]));

	  initCreaterInfo(tsCapacity);
	  initUpdaterInfo(tsCapacity);
	  tsCapacity.setDescription("Вместимость ТС, созданная автоматически при импорте справочника ТС");

	  return tsCapacity;
   }

   @Override
   protected TsCapacity getExistedDomain(TsCapacity newDomain) {
	  return tsCapacityRepository.getByCode(newDomain.getCod());
   }

   @Override
   protected void updateFKeys(TsCapacity newDomain, Long[] fks) {
	  // TS_TYPE_ID;
	  newDomain.setTsTypeId(fks[0]);
   }
}
