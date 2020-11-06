package ru.armd.pbk.components.viss.gtfs.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gtfs.GtfsReplaceVehicle;
import ru.armd.pbk.repositories.viss.gfts.GtfsReplaceVehicleRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Загрузчик "Замена ТС".
 */
@Component
@Scope("prototype")
public class GtfsReplaceVehicleLoader
	extends GtfsLoader<GtfsReplaceVehicle> {

   private final DateFormat df = new SimpleDateFormat("yyyyMMdd");

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   @Autowired
   public GtfsReplaceVehicleLoader(GtfsReplaceVehicleRepository domainRepository) {
	  super(domainRepository, false);
   }

   @Override
   protected GtfsReplaceVehicle createDomain(String[] fields) {
	  if (!filter(fields[1], df)) {
		 return null;
	  }
	  GtfsReplaceVehicle domain = new GtfsReplaceVehicle();
	  domain.setWorkDate(getWorkDate());
	  domain.setGrafic(getIntegerValue(fields[0]));
	  domain.setTime(getIntegerValueOfHourAndMinute(getWorkDate(), fields[2], df));
	  domain.setVehicleId1(getIntegerValue(fields[3]));
	  domain.setVehicleId2(getIntegerValue(fields[4]));
	  domain.setShiftNum(getIntegerValue(fields[6]));
	  return domain;
   }

}
