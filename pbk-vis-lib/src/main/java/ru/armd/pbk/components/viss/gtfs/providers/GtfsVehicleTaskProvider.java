package ru.armd.pbk.components.viss.gtfs.providers;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsVehicleTaskLoader;
import ru.armd.pbk.domain.viss.gtfs.GtfsVehicleTask;
import ru.armd.pbk.enums.core.VisAuditType;

/**
 * Провайдер "Справочник назначений ТС на маршрут".
 */
@Component
public class GtfsVehicleTaskProvider
	extends GtfsBaseProvider<GtfsVehicleTask> {

   @Autowired
   GtfsVehicleTaskProvider(ObjectFactory<GtfsVehicleTaskLoader> loader) {
	  super(loader, VisAuditType.VIS_GTFS_VEHICLE_TASK);
   }

}
