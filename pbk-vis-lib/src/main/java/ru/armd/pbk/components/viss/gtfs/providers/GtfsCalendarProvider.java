package ru.armd.pbk.components.viss.gtfs.providers;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsCalendarLoader;
import ru.armd.pbk.domain.viss.gtfs.GtfsCalendar;
import ru.armd.pbk.enums.core.VisAuditType;

/**
 * Провайдер "Справочник автотранспортных предприятий".
 */
@Component
public class GtfsCalendarProvider
	extends GtfsBaseProvider<GtfsCalendar> {

   @Autowired
   GtfsCalendarProvider(ObjectFactory<GtfsCalendarLoader> loader) {
	  super(loader, VisAuditType.VIS_GTFS_DEPOTS);
   }

}
