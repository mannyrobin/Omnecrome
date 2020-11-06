package ru.armd.pbk.components.viss.gtfs.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gtfs.GtfsChangeStopTimes;
import ru.armd.pbk.repositories.viss.gfts.GtfsChangeStopTimesRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Загрузчик "Оперативные изменения поостановочных расписаний".
 */
@Component
@Scope("prototype")
public class GtfsChangeStopTimesLoader
	extends GtfsLoader<GtfsChangeStopTimes> {

   private final DateFormat df = new SimpleDateFormat("yyyyMMdd");

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   @Autowired
   public GtfsChangeStopTimesLoader(
	   GtfsChangeStopTimesRepository domainRepository) {
	  super(domainRepository, false);
   }

   @Override
   protected GtfsChangeStopTimes createDomain(String[] fields) {
	  if (!filter(fields[1], df)) {
		 return null;
	  }
	  GtfsChangeStopTimes domain = new GtfsChangeStopTimes();
	  domain.setWorkDate(getWorkDate());
	  domain.setTripId(getIntegerValue(fields[0]));
	  domain.setArrivalTime(getIntegerValueOfHourAndMinute(fields[2]));
	  domain.setDepartureTime(getIntegerValueOfHourAndMinute(fields[3]));
	  domain.setStopId(getIntegerValue(fields[4]));
	  domain.setStopSequence(getIntegerValue(fields[5]));
	  domain.setTripNum(getIntegerValue(fields[10]));
	  domain.setShiftNum(getIntegerValue(fields[11]));
	  domain.setGrafic(getIntegerValue(fields[12]));
	  return domain;
   }

}
