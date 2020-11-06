package ru.armd.pbk.components.viss.gtfs.providers;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsVehiclesLoader;
import ru.armd.pbk.domain.viss.gtfs.GtfsVehicle;
import ru.armd.pbk.enums.core.VisAuditType;

/**
 * Провайдер "ТС".
 */
@Component
public class GtfsVehicleProvider
	extends GtfsBaseProvider<GtfsVehicle> {

   @Autowired
   GtfsVehicleProvider(ObjectFactory<GtfsVehiclesLoader> loader) {
	  super(loader, VisAuditType.VIS_GTFS_DEPOTS);
   }

}
