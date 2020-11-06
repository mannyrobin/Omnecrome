package ru.armd.pbk.components.viss.gtfs.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gtfs.GtfsVehicleTask;
import ru.armd.pbk.repositories.viss.gfts.GtfsVehicleTaskRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Загрузчик "Справочник назначений ТС на маршрут".
 */
@Component
@Scope("prototype")
public class GtfsVehicleTaskLoader
	extends GtfsLoader<GtfsVehicleTask> {

   private final DateFormat df = new SimpleDateFormat("yyyyMMdd");

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   @Autowired
   public GtfsVehicleTaskLoader(GtfsVehicleTaskRepository domainRepository) {
	  super(domainRepository, true);
   }

   @Override
   protected GtfsVehicleTask createDomain(String[] fields) {
	  if (!filter(fields[0], df)) {
		 return null;
	  }
	  GtfsVehicleTask domain = new GtfsVehicleTask();
	  domain.setWorkDate(getWorkDate());
	  domain.setRouteId(getIntegerValue(fields[1]));
	  domain.setVehicleId(getIntegerValue(fields[2]));
	  domain.setGrafic(getIntegerValue(fields[3]));
	  domain.setShiftNum(getIntegerValue(fields[4]));
	  domain.setStartTime(getIntegerValueOfHourAndMinute(getWorkDate(), fields[5], df));
	  domain.setEndTime(getIntegerValueOfHourAndMinute(getWorkDate(), fields[6], df));

	  return domain;
   }

}
