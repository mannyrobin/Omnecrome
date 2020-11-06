package ru.armd.pbk.components.viss.gtfs.providers;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsTripsLoader;
import ru.armd.pbk.domain.viss.gtfs.GtfsTrips;
import ru.armd.pbk.enums.core.VisAuditType;

/**
 * Провайдер "Рейсов".
 */
@Component
public class GtfsTripsProvider
	extends GtfsBaseProvider<GtfsTrips> {

   @Autowired
   GtfsTripsProvider(ObjectFactory<GtfsTripsLoader> loader) {
	  super(loader, VisAuditType.VIS_GTFS_TRIPS);
   }

}
