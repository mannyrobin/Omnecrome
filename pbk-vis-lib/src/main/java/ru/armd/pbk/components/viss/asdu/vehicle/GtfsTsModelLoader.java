package ru.armd.pbk.components.viss.asdu.vehicle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseArrayDomainLoader;
import ru.armd.pbk.domain.nsi.TsModel;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.repositories.nsi.TsModelRepository;

/**
 * Лоадер для ТС.
 */
@Component
public class GtfsTsModelLoader
	extends BaseArrayDomainLoader<TsModel> {

   private static final Logger LOGGER = Logger.getLogger(GtfsTsModelLoader.class);

   private TsModelRepository tsModelRepository;

   @Autowired
   GtfsTsModelLoader(TsModelRepository domainRepository) {
	  super(domainRepository);
	  tsModelRepository = domainRepository;
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
   protected TsModel createDomain(String[] fields) {
	  // VEHICLE_ID;DEPOT_ID;DEPOT_NUMBER;BRAND;MODEL;CAPACITY;SEATS;SQUARE;MASS;LENGTH;WIDTH;HEIGHT;SPEED_MAX;
	  TsModel tsModel = new TsModel();
	  tsModel.setMake(getStringValue(fields[3]));
	  tsModel.setModel(getStringValue(fields[4]));
	  tsModel.setSeatCount(getIntegerValue(fields[6]));
	  tsModel.setSquare(getDoubleValue(fields[7]));
	  tsModel.setMass(getDoubleValue(fields[8]));
	  tsModel.setLength(getDoubleValue(fields[9]));
	  tsModel.setWidth(getDoubleValue(fields[10]));
	  tsModel.setHeight(getDoubleValue(fields[11]));
	  tsModel.setSpeedMax(getDoubleValue(fields[12]));

	  initCreaterInfo(tsModel);
	  initUpdaterInfo(tsModel);
	  return tsModel;
   }

   @Override
   protected TsModel getExistedDomain(TsModel newDomain) {
	  return tsModelRepository.getByModel(newDomain.getModel());
   }

   @Override
   protected void updateFKeys(TsModel newDomain, Long[] fks) {
	  // TS_CAPACITY_ID;
	  newDomain.setTsCapacityId(fks[0]);
   }
}
