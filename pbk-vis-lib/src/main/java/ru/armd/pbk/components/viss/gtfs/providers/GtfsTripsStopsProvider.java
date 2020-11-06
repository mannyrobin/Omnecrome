package ru.armd.pbk.components.viss.gtfs.providers;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsTripsStopsLoader;
import ru.armd.pbk.domain.viss.gtfs.GtfsTripsStops;
import ru.armd.pbk.enums.core.VisAuditType;

/**
 * Провайдер "Справочник рейсов с последовательностями остановок из ЭРМ".
 */
@Component
public class GtfsTripsStopsProvider
	extends GtfsBaseProvider<GtfsTripsStops> {

   @Autowired
   GtfsTripsStopsProvider(ObjectFactory<GtfsTripsStopsLoader> loader) {
	  super(loader, VisAuditType.VIS_GTFS_TRIPS_STOPS);
   }

}
