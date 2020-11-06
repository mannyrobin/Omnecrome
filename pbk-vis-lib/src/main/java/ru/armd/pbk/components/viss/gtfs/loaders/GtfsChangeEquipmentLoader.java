package ru.armd.pbk.components.viss.gtfs.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gtfs.GtfsChangeEquipment;
import ru.armd.pbk.repositories.viss.gfts.GtfsChangeEquipmentRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Провайдер изменения бортового оборудования на ТС.
 */
@Component
@Scope("prototype")
public class GtfsChangeEquipmentLoader
	extends GtfsLoader<GtfsChangeEquipment> {

   private final DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   @Autowired
   public GtfsChangeEquipmentLoader(GtfsChangeEquipmentRepository domainRepository) {
	  super(domainRepository, false);
   }

   @Override
   protected GtfsChangeEquipment createDomain(String[] fields) {
	  if (!filter(fields[3], df)) {
		 return null;
	  }
	  GtfsChangeEquipment domain = new GtfsChangeEquipment();
	  domain.setWorkDate(getWorkDate());
	  domain.setEquipmentId(getIntegerValue(fields[0]));
	  domain.setIdentificator(getIntegerValue(fields[1]));
	  domain.setVehicleId(getIntegerValue(fields[2]));
	  domain.setTimeChange(getDateValue(fields[3], df));

	  return domain;
   }

}
