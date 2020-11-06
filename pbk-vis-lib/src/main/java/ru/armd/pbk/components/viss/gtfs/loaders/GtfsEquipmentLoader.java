package ru.armd.pbk.components.viss.gtfs.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gtfs.GtfsEquipment;
import ru.armd.pbk.repositories.viss.gfts.GtfsEquipmentRepository;

/**
 * Загрузчик бортового оборудования.
 */
@Component
@Scope("prototype")
public class GtfsEquipmentLoader
	extends GtfsLoader<GtfsEquipment> {

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   @Autowired
   public GtfsEquipmentLoader(GtfsEquipmentRepository domainRepository) {
	  super(domainRepository, true);
   }

   @Override
   protected GtfsEquipment createDomain(String[] fields) {
	  GtfsEquipment domain = new GtfsEquipment();
	  domain.setWorkDate(getWorkDate());
	  domain.setEquipmentId(getIntegerValue(fields[0]));
	  domain.setIdentificator(getIntegerValue(fields[1]));
	  domain.setVehicleId(getIntegerValue(fields[7]));
	  domain.setIsDeleted(getIntegerValue(fields[8]));
	  return domain;
   }

}
