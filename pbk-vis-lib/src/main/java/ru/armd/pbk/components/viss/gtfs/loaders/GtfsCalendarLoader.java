package ru.armd.pbk.components.viss.gtfs.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gtfs.GtfsCalendar;
import ru.armd.pbk.repositories.viss.gfts.GtfsCalendarRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Загрузчик бортового оборудования.
 */
@Component
@Scope("prototype")
public class GtfsCalendarLoader
	extends GtfsLoader<GtfsCalendar> {

   private final DateFormat df = new SimpleDateFormat("yyyyMMdd");

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   @Autowired
   public GtfsCalendarLoader(GtfsCalendarRepository domainRepository) {
	  super(domainRepository, true);
   }

   @Override
   protected GtfsCalendar createDomain(String[] fields) {
	  GtfsCalendar domain = new GtfsCalendar();
	  domain.setWorkDate(getWorkDate());
	  domain.setRouteId(getIntegerValue(fields[0]));
	  domain.setServiceId(getIntegerValue(fields[1]));
	  domain.setMonday(getIntegerValue(fields[2]));
	  domain.setTuesday(getIntegerValue(fields[3]));
	  domain.setWednesday(getIntegerValue(fields[4]));
	  domain.setThursday(getIntegerValue(fields[5]));
	  domain.setFriday(getIntegerValue(fields[6]));
	  domain.setSaturday(getIntegerValue(fields[7]));
	  domain.setSunday(getIntegerValue(fields[8]));
	  domain.setStartDate(getDateValue(fields[9], df));
	  domain.setEndDate(getDateValue(fields[10], df));
	  return domain;
   }

}
