package ru.armd.pbk.components.viss.asdu.vehicle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseCsvDomainLoader;
import ru.armd.pbk.domain.nsi.TsCapacity;
import ru.armd.pbk.domain.nsi.TsModel;
import ru.armd.pbk.domain.nsi.TsType;
import ru.armd.pbk.domain.nsi.Venicle;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.repositories.nsi.VenicleRepository;

/**
 * Лоадер для ТС.
 */
@Component
public class GtfsVehicleLoader
	extends BaseCsvDomainLoader<Venicle> {

   private static final Logger LOGGER = Logger.getLogger(GtfsVehicleLoader.class);

   @Autowired
   private GtfsTsTypeBusLoader gtfsTsTypeBusLoader;

   @Autowired
   private GtfsTsCapacityLoader gtfsTsCapacityLoader;

   @Autowired
   private GtfsTsModelLoader gtfsTsModelLoader;

   @Autowired
   private VenicleRepository venicleRepository;

   @Autowired
   GtfsVehicleLoader(VenicleRepository domainRepository) {
	  super(domainRepository);
	  venicleRepository = domainRepository;
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
   protected Venicle createDomain(String[] fields) {
	  // VEHICLE_ID;DEPOT_ID;DEPOT_NUMBER;BRAND;MODEL;CAPACITY;SEATS;SQUARE;MASS;LENGTH;WIDTH;HEIGHT;SPEED_MAX;
	  Venicle venicle = new Venicle();
	  venicle.setAsduVenicleId(getStringValue(fields[0]));
	  // venicle.setDepoNumber(getStringValue(fields[1]));
	  venicle.setDepoNumber(getStringValue(fields[2]));

	  initCreaterInfo(venicle);
	  initUpdaterInfo(venicle);
	  return venicle;
   }

   @Override
   protected void doProcessFields(String[] fields) {
	  TsType tsType = gtfsTsTypeBusLoader.importDomain();
	  TsCapacity tsCapacity = gtfsTsCapacityLoader.importArray(fields, new Long[] {tsType.getId()});
	  TsModel tsModel = gtfsTsModelLoader.importArray(fields, new Long[] {tsCapacity.getId()});

	  Venicle vehicle = createDomain(fields);
	  vehicle.setTsModelId(tsModel.getId());
	  importDomain(vehicle);
   }

   @Override
   protected Venicle getExistedDomain(Venicle newDomain) {
	  return venicleRepository.getByAsduVenicleId(newDomain.getAsduVenicleId());
   }
}
